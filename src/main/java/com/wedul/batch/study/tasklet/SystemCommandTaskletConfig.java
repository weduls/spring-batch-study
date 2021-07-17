package com.wedul.batch.study.tasklet;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class SystemCommandTaskletConfig {

    @Bean
    public Step systemCommandTaskletStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("systemCommandTaskletStep")
            .tasklet(systemCommandTasklet())
            .build();
    }

    @Bean
    public SystemCommandTasklet systemCommandTasklet() {
        SystemCommandTasklet systemCommandTasklet = new SystemCommandTasklet();

        systemCommandTasklet.setCommand("ls -al");
        systemCommandTasklet.setTimeout(1000);
        systemCommandTasklet.setInterruptOnCancel(true);

        // 비동기로 호출 하므로 interval 간격대로 체크
        systemCommandTasklet.setTaskExecutor(new SimpleAsyncTaskExecutor());
        systemCommandTasklet.setTerminationCheckInterval(1000);

        return systemCommandTasklet;
    }

}
