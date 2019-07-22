package com.example.quartztest.quartz.test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class SchedulerListener implements JobListener {

    public static final String LISTENER_NAME = "QuartSchedulerListener";

    @Override
    public String getName() {
        return LISTENER_NAME;
    }

    // 任务被调度前
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("jobToBeExecuted");
        System.out.println("Job: " + jobName + " is going to start...");
    }

    // 任务调度被拒绝了
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("job execution vetoed.");
    }

    // 任务被调度后
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("job was executed");

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job: " + jobName + " is finished...");

        if(jobException != null && !jobException.getMessage().equals("")) {
            System.out.println("Exception thrown by: " + jobName + " Exception: " + jobException.getMessage());
        }
    }
}