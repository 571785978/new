package com.rep.quartz.job;
//
import com.rep.reptile.main.Scanning;
import com.rep.common.Util;
import org.slf4j.Logger;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//
import java.time.LocalDateTime;
//
//@Component
//public class ReJob {
//
//    Logger logger = Util.getLogger(getClass());
//
//    @Scheduled(cron = "0 0/30 * * * ?") // 每分钟执行一次
//    public void reptileScanning() {
//        logger.info("开始扫描爬虫任务->{}",LocalDateTime.now().toString());
//        try {
//            Scanning.scanning();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
@Component
public class  ReJob implements ApplicationRunner {
    Logger logger = Util.getLogger(getClass());
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("开始扫描爬虫任务->{}",LocalDateTime.now().toString());
        try {
            Scanning.scanning();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}