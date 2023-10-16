package com.davidreisodev.apirestkafka.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventRegisterService {
    
    private final KafkaTemplate<Object,Object> template;
    
    public <T> void addEvent(String topic, T data){
        template.send(topic, data);
    }
}
