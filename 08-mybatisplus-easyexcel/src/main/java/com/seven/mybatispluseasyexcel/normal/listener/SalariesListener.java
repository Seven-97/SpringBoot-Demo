package com.seven.mybatispluseasyexcel.normal.listener;

import com.alibaba.excel.context.AnalysisContext;

import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seven.mybatispluseasyexcel.normal.domain.Salaries;
import com.seven.mybatispluseasyexcel.normal.mapper.SalariesMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class SalariesListener extends ServiceImpl<SalariesMapper, Salaries> implements ReadListener<Salaries>, IService<Salaries> {

    private static final Log logger = LogFactory.getLog(SalariesListener.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(20);
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private final ThreadLocal<ArrayList<Salaries>> salariesList = ThreadLocal.withInitial(ArrayList::new);
    private static AtomicInteger count = new AtomicInteger(1);
    private static final int batchSize = 10000;

    @Resource
    private SalariesListener salariesListener;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoke(Salaries data, AnalysisContext context) {
        //方案一：单线程逐行插入
//        saveOne(data);

        //方案二、三、四、五
        salariesList.get().add(data);
        if (salariesList.get().size() >= batchSize) {
//            saveData();//方案二、三：单线程批量插入
//            asyncSaveData1();//方案四：异步线程批量插入
            asyncSaveData();//方案五：多线程批量插入
        }
    }



    public void saveOne(Salaries data){
        save(data);
        logger.info("第" + count.getAndAdd(1) + "次插入1条数据");
    }

    public void saveData() {
        if (!salariesList.get().isEmpty()) {
            saveBatch(salariesList.get(), salariesList.get().size());
            logger.info("第" + count.getAndAdd(1) + "次插入" + salariesList.get().size() + "条数据");
            salariesList.get().clear();
        }
    }

    private void asyncSaveData1() {
        if (!salariesList.get().isEmpty()) {
            ArrayList<Salaries> salaries = (ArrayList<Salaries>) salariesList.get().clone();
            singleThreadExecutor.execute(new SaveTask(salaries, salariesListener));
            salariesList.get().clear();
        }
    }

    public void asyncSaveData() {
        if (!salariesList.get().isEmpty()) {
            ArrayList<Salaries> salaries = (ArrayList<Salaries>) salariesList.get().clone();
            executorService.execute(new SaveTask(salaries, salariesListener));
            salariesList.get().clear();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAfterAllAnalysed(AnalysisContext context) {
        logger.info("一个Sheet全部处理完");
        if (salariesList.get().size() >= batchSize) {
            saveData();
        }
    }

    static class SaveTask implements Runnable {

        private List<Salaries> salariesList;
        private SalariesListener salariesListener;

        public SaveTask(List<Salaries> salariesList, SalariesListener salariesListener) {
            this.salariesList = salariesList;
            this.salariesListener = salariesListener;
        }

        @Override
        public void run() {
            salariesListener.saveBatch(salariesList);
            logger.info("第" + count.getAndAdd(1) + "次插入" + salariesList.size() + "条数据");
        }
    }
}
