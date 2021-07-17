package com.wedul.batch.study.parameter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ParameterJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(@Qualifier("parameterStep") Step step) {
        return this.jobBuilderFactory.get("parameterJob")
//            .incrementer(new RunIdIncrementer())
            .incrementer(new DailyJobTimestamper())
            .start(step)
            .validator(validator())
            .listener(new JobLoggerListener())
            .build();
    }

    @JobScope
    @Bean("parameterStep")
    public Step step() {
        return this.stepBuilderFactory.get("parameterStep")
            .tasklet(tasklet(null))
            .build();
    }

    @Bean("parameterJobValidator")
    public CompositeJobParametersValidator validator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        validator.setValidators(Arrays.asList(
            new JobParameterValidator(),
            new ParameterValidator()
        ));

        return validator;
    }

    @StepScope
    @Bean("parameterTasklet")
    public Tasklet tasklet(/* Lazy binding */ @Value("#{jobParameters['size']}") String size) {
        return (contribution, chunkContext) -> {
            System.out.println("ddd");

            // chunkContext에서 파라미터 가져오기
            String wedul = String.valueOf(chunkContext.getStepContext().getJobParameters().get("wedul"));

            log.info("[spring batch][parameter] size : " + size + ", wedul : " + wedul);
            return RepeatStatus.FINISHED;
        };
    }

}
