package com.wedul.batch.study.tasklet;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MethodInvokeConfig {

    @Bean
    public Step methodInvokingTaskletStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("methodInvokingTaskletStep")
            .tasklet(methodInvokingTaskletAdapterTasklet())
            .build();
    }

    @Bean
    public MethodInvokingTaskletAdapter methodInvokingTaskletAdapterTasklet() {
        MethodInvokingTaskletAdapter methodInvokingTaskletAdapter = new MethodInvokingTaskletAdapter();

        methodInvokingTaskletAdapter.setTargetObject(simpleClass());
        methodInvokingTaskletAdapter.setTargetMethod("run");
        methodInvokingTaskletAdapter.setArguments(new String[] {"wedul"});
        return methodInvokingTaskletAdapter;
    }

    public SimpleClass simpleClass() {
        return new SimpleClass();
    }

    private static class SimpleClass {
        public void run(String runManName) {
            System.out.println("run method invoke tasklet from " + runManName);
        }
    }

}
