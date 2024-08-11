package com.seven.mybatispluseasyexcel.service;

import com.alibaba.excel.EasyExcel;
import com.seven.mybatispluseasyexcel.domain.Salaries;
import com.seven.mybatispluseasyexcel.listener.SalariesListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 作者：周瑜大都督
 */
@Service
public class ImportService {

    @Resource
    private SalariesListener salariesListener;

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    //方案一，方案二：单线程逐条解析
    public void importExcel(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Salaries.class, salariesListener).doReadAll();
    }

    //方案三，方案四：多线程解析，一个sheet对应一个线程
    public void importExcelAsync(MultipartFile file) {
        // 开20个线程分别处理20个sheet
        List<Callable<Object>> tasks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int num = i;
            tasks.add(() -> {
                EasyExcel.read(file.getInputStream(), Salaries.class, salariesListener)
                        .sheet(num)
                        .doRead();
                return null;
            });
        }

        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
