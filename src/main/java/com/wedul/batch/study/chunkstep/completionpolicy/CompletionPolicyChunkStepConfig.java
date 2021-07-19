package com.wedul.batch.study.chunkstep.completionpolicy;

import com.wedul.batch.study.chunkstep.listener.StepStartStopListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class CompletionPolicyChunkStepConfig {

    @Bean
    public Step completionPolicyChunkStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("completionPolicyChunkStep")
            .<String, String>chunk(completionPolicy())
            .reader(itemReader())
            .writer(itemWriter())
            .listener(new StepStartStopListener())
            .build();
    }

    public ListItemReader<String> itemReader() {
        List<String> items = new ArrayList<>(100000);

        for (int i = 0; i< 10000; i++) {
            items.add(UUID.randomUUID().toString());
        }

        return new ListItemReader<>(items);
    }

    // 나눠서 쓰기 (chunk 단위)
    public ItemWriter<String> itemWriter() {
        return items -> {
            for (String item : items) {
                System.out.println(">> current write item = " + item);
            }
        };
    }

    // CompletionPolicy 구현체를 구현해서 만들기 쉬움
    public CompletionPolicy completionPolicy() {
        CompositeCompletionPolicy policy = new CompositeCompletionPolicy();

        policy.setPolicies(
            new CompletionPolicy[] {
                new TimeoutTerminationPolicy(3),
                new SimpleCompletionPolicy(1000)
            }
        );
        return policy;
    }

}
