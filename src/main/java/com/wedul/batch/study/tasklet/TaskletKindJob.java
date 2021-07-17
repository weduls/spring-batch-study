package com.wedul.batch.study.tasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskletKindJob {

    @Bean
    public Job taskletKindTestJobs(
        JobBuilderFactory jobBuilderFactory,
        Step commonTasklet,
        Step callableTaskletAdapterStep,
        Step methodInvokingTaskletStep,
        Step systemCommandTaskletStep) {
        return jobBuilderFactory.get("taskletKindJobs")
            .incrementer(new RunIdIncrementer())
            .start(commonTasklet)
            .next(callableTaskletAdapterStep)
            .next(methodInvokingTaskletStep)
            .next(systemCommandTaskletStep)
            .build();
    }

}
