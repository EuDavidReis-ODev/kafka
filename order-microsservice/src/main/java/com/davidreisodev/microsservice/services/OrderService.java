package com.davidreisodev.microsservice.services;

import java.util.ArrayList;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.davidreisodev.microsservice.kafkadata.OrderData;
import com.davidreisodev.microsservice.kafkadata.ProductData;
import com.davidreisodev.microsservice.models.OrderModel;
import com.davidreisodev.microsservice.models.ProductModel;
import com.davidreisodev.microsservice.repositories.OrderRepository;
import com.davidreisodev.microsservice.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     *Representa o nome do tópico de pedido.
     */
    private static final String SAVE_ORDER = "SALVAR_PEDIDO";

    @KafkaListener(topics = SAVE_ORDER, groupId = "microsserviceSalvaPedido")
    private void saveOrder(ConsumerRecord<String,String> record){
       log.info("Key = {}" , record.key());
       log.info("Cabeçalho = {}" , record.headers());
       log.info("Partição  = {}" , record.partition());

       String data = record.value();

       ObjectMapper mapper = new ObjectMapper();
       OrderData orderData = null;

       try {
        orderData = mapper.readValue(data, OrderData.class);
        } catch (JsonMappingException e) {
            log.error("Falha ao mapear evento [dado={}].", data, e);
            e.printStackTrace();
            return;
        } catch (JsonProcessingException e) {
            log.error("Falha ao converter evento [dado={}].", data, e);
            e.printStackTrace();
            return;
        }
        log.info("Evento recebido = {}", orderData);

        log.info("Salvando pedido...");

        OrderModel orderModel = new OrderModel();
        orderModel.setProducts(new ArrayList<ProductModel>());
        

                 
            for(ProductData pd : orderData.getProducts()){
                
                ProductModel pModel = new ProductModel();
                BeanUtils.copyProperties(pd, pModel);
                orderModel.getProducts().add(pModel);

            }   



        OrderModel saveResult = null;
        try {
            saveResult = orderRepository.save(orderModel);
            log.info("Resultado do salvamento = {}",saveResult, orderModel);

        } catch (Exception e) {    
            log.error("Erro ao salvar pedido {}",orderModel , e );
        }
    }
}
