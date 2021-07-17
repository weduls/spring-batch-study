package com.wedul.batch.study.tasklet;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonTaskletConfig {

    @Bean
    public Step commonTasklet(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("commonTasklet")
            .tasklet((stepContribution, chunkContext) -> {
                System.out.println("common tasklet");

                return RepeatStatus.FINISHED;
                /**
                 * 특정 상태가 될 때 까지 테스크릿을 계속 실행시키고자 할 때 사용
                 */
//                return RepeatStatus.CONTINUABLE;
            }).build();
    }

}
