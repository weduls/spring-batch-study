package com.wedul.batch.study.chunkstep.common;

import com.wedul.batch.study.chunkstep.listener.StepStartStopListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class CommonChunkStepConfig {

    @Bean
    public Step commonChunkStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("commonChunkStep")
            .<String, String>chunk(1000)
            .reader(itemReader())
            .writer(itemWriter())
            .listener(new StepStartStopListener())
            .build();
    }

    // 한번에 읽고
    public ListItemReader<String> itemReader() {
        List<String> items = new ArrayList<>(100000);

        for (int i = 0; i< 10000; i++) {
            items.add(UUID.randomUUID().toString());
            System.out.println(">> current read item idx = " + i);
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

}
