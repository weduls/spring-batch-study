package com.wedul.batch.study.chunkstep.completionpolicy;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompletionPolicyChunkJob {

    @Bean
    public Job completionPolicyChunkJobs(JobBuilderFactory jobBuilderFactory,
                                         Step completionPolicyChunkStep) {
        return jobBuilderFactory.get("completionPolicyChunkJobs")
            .incrementer(new RunIdIncrementer())
            .start(completionPolicyChunkStep)
            .build();
    }

}
