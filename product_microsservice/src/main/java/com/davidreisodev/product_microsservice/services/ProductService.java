package com.davidreisodev.product_microsservice.services;

import java.util.ArrayList;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.davidreisodev.product_microsservice.kafkadata.ProductData;
import com.davidreisodev.product_microsservice.models.ProductModel;
import com.davidreisodev.product_microsservice.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;

@Service
@Slf4j
public class ProductService {
    
    /**
     * String que referencia o tópico de produto.
     */
    private static final String PRODUCT_SAVE = "SALVAR_PRODUTO";

    @Autowired
    ProductRepository productRepository;

    @KafkaListener(topics = PRODUCT_SAVE, groupId = "microsserviceSalvaProduto")
        private void saveProduct(ConsumerRecord<String,String> record){
        
        String data = record.value();

        ObjectMapper mapper = new ObjectMapper();
        ProductData productData = null;

        try {
            productData = mapper.readValue(data, ProductData.class);
        } catch (JsonMappingException e) {
            log.error("Falha ao mapear evento [dado={}].", data, e);
            e.printStackTrace();
            return;
        } catch (JsonProcessingException e) {
            log.error("Falha ao converter evento [dado={}].", data, e);
            e.printStackTrace();
            return;
        }
        log.info("Evento recebido = {}", productData);


        ProductModel productModel = new ProductModel();

        BeanUtils.copyProperties(productData, productModel);
        log.info("Salvando produto...");
        ProductModel saveProductResult = new ProductModel();
        try {
            saveProductResult = productRepository.save(productModel);
            log.info("Resultado do salvamento = {}",saveProductResult, productModel);

        } catch (Exception e) {
            log.error("Erro ao salvar produto {}", productModel, e );
        }

    }
}
