package com.davidreisodev.apirestkafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.davidreisodev.apirestkafka.kafkadata.OrderData;
import com.davidreisodev.apirestkafka.services.EventRegisterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PedidoController {

    /**
     *Representa o nome do tópico de pedido.
     */
    private static final String SAVE_ORDER = "SALVAR_PEDIDO";

    /**
     * Mensagem de resposta de Pedido Salvo.
     */
    private static final String ORDER_SAVED = "Pedido salvo!";

    @Autowired
    private final EventRegisterService registraEventoService;

    @PostMapping(path = "/api/salva-pedido")
    public ResponseEntity<String> saveOrder(@RequestBody OrderData pedido){
        registraEventoService.addEvent(SAVE_ORDER,pedido);
        return ResponseEntity.ok(ORDER_SAVED);
    }
}
