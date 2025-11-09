package com.msa.rental.framework.kafkaadapter;


import com.msa.rental.application.outputport.EventOutputPort;
import com.msa.rental.domain.model.event.ItemRented;
import com.msa.rental.domain.model.event.ItemReturned;
import com.msa.rental.domain.model.event.OverdueCleared;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class RentalKafkaProducer implements EventOutputPort {


    @Value(value = "${producers.topic1.name}")
    private String TOPIC_RENT;
    @Value(value = "${producers.topic2.name}")
    private String TOPIC_RETURN;
    @Value(value = "${producers.topic3.name}")
    private String TOPIC_CLEAR;

    private final KafkaTemplate<String, ItemRented> kafkaTemplate1;
    private final KafkaTemplate<String, ItemReturned> kafkaTemplate2;
    private final KafkaTemplate<String, OverdueCleared> kafkaTemplate3;


    @Override
    public void occurRentalEvent(ItemRented rentalItemEvent) {
        CompletableFuture<SendResult<String, ItemRented>> future =
                kafkaTemplate1.send(TOPIC_RENT, rentalItemEvent);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                ItemRented event = result.getProducerRecord().value();
                log.info("Sent message=[{}] with offset=[{}]",
                        event.getItem().getId(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message=[{}] due to : {}",
                        rentalItemEvent.getItem().getId(),
                        ex.getMessage(),
                        ex);
                // TODO: 보상 트랜잭션 필요
            }
        });
    }

    @Override
    public void occurReturnEvent(ItemReturned itemReturnedEvent) {
        CompletableFuture<SendResult<String, ItemReturned>> future =
                kafkaTemplate2.send(TOPIC_RETURN, itemReturnedEvent);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                ItemReturned event = result.getProducerRecord().value();
                log.info("Sent return message=[{}] with offset=[{}]",
                        event.getItem().getId(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send return message=[{}] due to : {}",
                        itemReturnedEvent.getItem().getId(),
                        ex.getMessage(),
                        ex);
                // TODO: 보상 트랜잭션 필요
            }
        });
    }

    @Override
    public void occurOverdueClearedEvent(OverdueCleared overdueCleared) {
        CompletableFuture<SendResult<String, OverdueCleared>> future =
                kafkaTemplate3.send(TOPIC_CLEAR, overdueCleared);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                OverdueCleared event = result.getProducerRecord().value();
                log.info("Sent overdue cleared message=[{}] with offset=[{}]",
                        event.getIdName().getId(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send overdue cleared message=[{}] due to : {}",
                        overdueCleared.getIdName().getId(),
                        ex.getMessage(),
                        ex);
                // TODO: 보상 트랜잭션 필요
            }
        });
    }
}
