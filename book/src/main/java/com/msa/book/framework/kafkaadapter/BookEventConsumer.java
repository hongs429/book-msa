package com.msa.book.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.book.application.usecase.MakeAvailableUseCase;
import com.msa.book.application.usecase.MakeUnAvailableUseCase;
import com.msa.book.domain.event.ItemRented;
import com.msa.book.domain.event.ItemReturned;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEventConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MakeAvailableUseCase makeAvailableUsecase;
    private final MakeUnAvailableUseCase makeUnavailable;

    @KafkaListener(
            topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}"
    )
    public void consumeRental(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("rental_rent: " + record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        makeUnavailable.unavailable(itemRented.getItem().getId());
    }

    @KafkaListener(
            topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}"
    )
    public void consumeReturn(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("rental_return: " + record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemReturned.class);
        makeAvailableUsecase.available(Long.valueOf(itemRented.getItem().getId()));
    }
}
