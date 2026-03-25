package ru.home.tasks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.tasks.dto.TaskUpdEventDto;
import ru.home.tasks.services.TaskUpdEventProducer;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@RequiredArgsConstructor
public class TaskUpdEventProducerImpl implements TaskUpdEventProducer {

    private final KafkaTemplate<String, TaskUpdEventDto> kafkaTemplate;

    @Override
    public void sendTaskUpdEvent(TaskUpdEventDto event) {
        kafkaTemplate.send("task-upd", event);
    }
}
