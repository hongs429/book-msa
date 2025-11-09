package com.msa.user.framework.kafkaadapter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.user.application.usecase.SavePointUseCase;
import com.msa.user.application.usecase.UsePointUseCase;
import com.msa.user.domain.event.ItemRented;
import com.msa.user.domain.event.ItemReturned;
import com.msa.user.domain.event.OverdueCleared;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SavePointUseCase savePointUsecase;
    private final UsePointUseCase usePointUsecase;

    @KafkaListener(topics="${consumer.topic1.name}",groupId = "${consumer.groupid.name}")
    public void consumeRent(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("rental_rent: " + record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        savePointUsecase.savePoint(itemRented.getIdName(),itemRented.getPoint());
    }

    @KafkaListener(topics="${consumer.topic2.name}",groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_return:"+ record.value());
        ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);
        savePointUsecase.savePoint(itemReturned.getIdName(),itemReturned.getPoint());
    }

    @KafkaListener(topics="${consumer.topic3.name}",groupId = "${consumer.groupid.name}")
    public void consumeClear(ConsumerRecord<String, String> record) throws Exception {
        System.out.printf(record.value());
        OverdueCleared overdueCleared = objectMapper.readValue(record.value(), OverdueCleared.class);
        usePointUsecase.userPoint(overdueCleared.getIdName(),overdueCleared.getPoint());
    }

}
