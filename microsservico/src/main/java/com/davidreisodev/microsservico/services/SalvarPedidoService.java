package com.davidreisodev.microsservico.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.davidreisodev.microsservico.kafkadata.PedidoData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SalvarPedidoService {
    
    @KafkaListener(topics = "salvarpedido", groupId = "MicrosservicoSalvaPedido")
    private void executar(ConsumerRecord<String,String> record){
       log.info("Key = {}" , record.key());
       log.info("Cabeçalho = {}" , record.headers());
       log.info("Partição  = {}" , record.partition());

       String data = record.value();

       ObjectMapper mapper = new ObjectMapper();
       PedidoData pedido = null;

       try {
        pedido = mapper.readValue(data, PedidoData.class);
        } catch (JsonMappingException e) {
            log.error("Falha ao mapear evento [dado={}].", data, e);
            e.printStackTrace();
            return;
        } catch (JsonProcessingException e) {
            log.error("Falha ao converter evento [dado={}].", data, e);
            e.printStackTrace();
            return;
        }
        log.info("Evento recebido = {}", pedido);
        }
}
