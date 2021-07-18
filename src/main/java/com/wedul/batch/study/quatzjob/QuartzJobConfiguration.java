package com.wedul.batch.study.quatzjob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class QuartzJobConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job quartzJob() {
        return jobBuilderFactory.get("quartzJob")
            .incrementer(new RunIdIncrementer())
            .start(quartzStep())
            .build();
    }

    @Bean
    public Step quartzStep() {
        return stepBuilderFactory.get("quartzStep")
            .tasklet((stepContribution, chunkContext) -> {
                log.info("quartz step run");
               return RepeatStatus.FINISHED;
            }).build();
    }

    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder.newJob(BatchScheduledJob.class)
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger jobTrigger(JobDetail quartzJobDetail) {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(5).withRepeatCount(4);

        return TriggerBuilder.newTrigger()
            .forJob(quartzJobDetail)
            .withSchedule(simpleScheduleBuilder)
            .build();
    }

}
