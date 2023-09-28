package com.adatech.cielo.prospect.servico;

import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DadosCadastroClienteDeserializer {

    public List<DadosCadastroCliente> deserealizaObjeto(List<Message> messages){
        List<DadosCadastroCliente> clientes = new ArrayList<>();
        try {
             clientes = messages.stream().map(m -> criaObjeto(m)).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    private DadosCadastroCliente criaObjeto(Message m) {
        DadosCadastroCliente cliente = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            cliente = objectMapper.readValue(m.getBody(), DadosCadastroCliente.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
