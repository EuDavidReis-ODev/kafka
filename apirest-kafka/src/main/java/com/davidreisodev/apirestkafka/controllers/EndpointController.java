package com.davidreisodev.apirestkafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.davidreisodev.apirestkafka.kafkadata.OrderData;
import com.davidreisodev.apirestkafka.kafkadata.ProductData;
import com.davidreisodev.apirestkafka.services.EventRegisterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EndpointController {

    /**
     *Representa o nome do tópico de pedido.
     */
    private static final String SAVE_ORDER = "SALVAR_PEDIDO";

    /**
     *Representa o nome do tópico de pedido.
     */
    private static final String SAVE_PRODUCT = "SALVAR_PRODUTO";

    /**
     * Mensagem de resposta de Pedido salvo.
     */
    private static final String ORDER_SAVED = "Pedido salvo!";

    /**
     * Mensagem de resposta de Produto salvo.
     */
    private static final String PRODUCT_SAVED = "Produto salvo!";

    @Autowired
    private final EventRegisterService registraEventoService;

    @PostMapping(path = "/api/salva-pedido")
    public ResponseEntity<String> saveOrder(@RequestBody OrderData pedido){
        registraEventoService.addSaveOrderEvent(SAVE_ORDER,pedido);
        return ResponseEntity.ok(ORDER_SAVED);
    }

    @PostMapping(path = "/api/salva-produto")
    public ResponseEntity<String> saveProduct(@RequestBody ProductData product){
        registraEventoService.addSaveProductEvent(SAVE_PRODUCT,product);
        return ResponseEntity.ok(PRODUCT_SAVED);
    }
}
