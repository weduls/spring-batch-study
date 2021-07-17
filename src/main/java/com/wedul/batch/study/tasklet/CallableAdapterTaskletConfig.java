package com.wedul.batch.study.tasklet;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.CallableTaskletAdapter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Callable;

@Configuration
public class CallableAdapterTaskletConfig {

    @Bean
    public Step callableTaskletAdapterStep(StepBuilderFactory stepBuilderFactory,
                                           CallableTaskletAdapter callableTaskletAdapterTasklet) {
        return stepBuilderFactory.get("callableTaskletAdapterStep")
            .tasklet(callableTaskletAdapterTasklet)
            .build();
    }

    @Bean
    public Callable<RepeatStatus> callableObject() {
        return () -> {
            System.out.println("callable tasklet");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public CallableTaskletAdapter callableTaskletAdapterTasklet(Callable<RepeatStatus> callableObject) {
        CallableTaskletAdapter callableTaskletAdapter = new CallableTaskletAdapter();
        callableTaskletAdapter.setCallable(callableObject);
        return callableTaskletAdapter;
    }

}
