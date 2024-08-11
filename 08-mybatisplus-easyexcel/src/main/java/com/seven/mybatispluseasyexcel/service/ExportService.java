package com.seven.mybatispluseasyexcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seven.mybatispluseasyexcel.domain.Salaries;
import com.seven.mybatispluseasyexcel.mapper.SalariesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class ExportService {
    public static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Resource
    private SalariesMapper salariesMapper;

    //方案一
    public void exportExcel1(HttpServletResponse response) throws IOException {
        setExportHeader(response);

        //查出所有数据
        List<Salaries> salaries = salariesMapper.selectList(null);
        EasyExcel.write(response.getOutputStream(), Salaries.class)
                .sheet()//写到一个sheet
                .doWrite(salaries);
    }



    public void exportExcel2(HttpServletResponse response) throws IOException {
        setExportHeader(response);
        //查出所有数据
        List<Salaries> salaries = salariesMapper.selectList(null);

        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), Salaries.class).build()) {
            //创建3个sheet
            WriteSheet writeSheet1 = EasyExcel.writerSheet(1, "模板1").build();
            WriteSheet writeSheet2 = EasyExcel.writerSheet(2, "模板2").build();
            WriteSheet writeSheet3 = EasyExcel.writerSheet(3, "模板3").build();

            //将查出的数据进行分割
            List<Salaries> data1 = salaries.subList(0, salaries.size() / 3);
            List<Salaries> data2 = salaries.subList(salaries.size() / 3, salaries.size() * 2 / 3);
            List<Salaries> data3 = salaries.subList(salaries.size() * 2 / 3, salaries.size());

            //写入3个sheet
            excelWriter.write(data1, writeSheet1);
            excelWriter.write(data2, writeSheet2);
            excelWriter.write(data3, writeSheet3);
        }
    }



    public void exportExcel3(HttpServletResponse response) throws IOException {

        setExportHeader(response);

        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), Salaries.class).build()) {
            //查询表数据条数
            Long count = salariesMapper.selectCount(null);
            Integer pages = 10;//定义分成10页数据
            Long size = count / pages; //每页条数

            for (int i = 0; i < pages; i++) {
                //pages 页条数据，就创建pages页个 sheet
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();

                Page<Salaries> page = new Page<>();
                page.setCurrent(i + 1);
                page.setSize(size);
                Page<Salaries> selectPage = salariesMapper.selectPage(page, null);

                excelWriter.write(selectPage.getRecords(), writeSheet);
            }
        }
    }


    public void exportExcel4(HttpServletResponse response) throws IOException, InterruptedException {

        setExportHeader(response);
        //查询表数据条数
        Long count = salariesMapper.selectCount(null);

        Integer pages = 20;//定义分成10页数据
        Long size = count / pages;//每页条数

        //创建pages个线程
        ExecutorService executorService = Executors.newFixedThreadPool(pages);
        CountDownLatch countDownLatch = new CountDownLatch(pages);

        Map<Integer, Page<Salaries>> pageMap = new HashMap<>();
        for (int i = 0; i < pages; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //多线程分页查询
                    Page<Salaries> page = new Page<>();
                    page.setCurrent(finalI + 1);
                    page.setSize(size);
                    Page<Salaries> selectPage = salariesMapper.selectPage(page, null);

                    pageMap.put(finalI, selectPage);
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), Salaries.class).build()) {
            //easyexcel不支持并发写入多个sheet，只能一个sheet一个sheet的写
            for (Map.Entry<Integer, Page<Salaries>> entry : pageMap.entrySet()) {
                Integer num = entry.getKey();
                Page<Salaries> salariesPage = entry.getValue();
                WriteSheet writeSheet = EasyExcel.writerSheet(num, "模板" + num).build();
                //写入多个sheet
                excelWriter.write(salariesPage.getRecords(), writeSheet);
            }
        }

        // https://github.com/alibaba/easyexcel/issues/1040
    }

    private static void setExportHeader(HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "seven.xlsx");
    }
}
