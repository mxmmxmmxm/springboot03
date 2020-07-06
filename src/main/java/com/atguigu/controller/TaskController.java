package com.atguigu.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskController {

    /**
     *  @Scheduled:定时规则
     *       cron：项目启动后每5秒执行一次
     *
     *       fixedDelay：距离上一次定时任务执行完毕后N毫秒再执行，
     *       执行A任务花了5秒，比如参数是3000，A任务执行完成之后，再过3秒执行
     *
     *       fixedRate：执行周期，执行频率
     *       定时任务执行开始，再过N毫秒后执行，
     *       执行A任务花了2秒，比如参数是3000，A任务执行完成之后，再过1秒后执行，
     *       执行A任务花了15秒，比如参数是3000，A任务执行完成之后，立即执行。
     */
    //@Scheduled(fixedDelay = 3000)   //距离上一次定时任务执行完毕后N毫秒再执行
    //@Scheduled(fixedRate = 3000)    //定时任务执行开始，再过N毫秒后执行
    @Scheduled(cron = "0/10 * * * * *")
    public void myTask() throws InterruptedException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
        //Thread.sleep(5000);
    }
}
