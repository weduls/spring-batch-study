package com.wedul.batch.study.chunkstep.common;

import com.wedul.batch.study.chunkstep.listener.StepStartStopListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonChunkStepJob {

    @Bean
    public Job commonChunkStepJobs(JobBuilderFactory jobBuilderFactory,
                             Step commonChunkStep) {
        return jobBuilderFactory.get("commonChunkStepJobs")
            .incrementer(new RunIdIncrementer())
            .start(commonChunkStep)
            .build();
    }

}
