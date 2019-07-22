package com.example.quartztest.quartz.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自定义定时任务
 */
public class ScheduledJob implements Job {
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 执行任务逻辑。。。
        System.out.println("执行任务");
    }
}