package com.wedul.batch.study.parameter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class JobLoggerListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("start!!!");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("end!!!");
    }
}
