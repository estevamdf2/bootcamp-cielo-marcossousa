package com.adatech.cielo.prospect.controller;

import com.adatech.cielo.prospect.domain.cliente.ClienteService;
import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.adatech.cielo.prospect.servico.AmazonSQSService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    private final ClienteService service;
    private final AmazonSQSService amazonService;

    public ClienteRestController(ClienteService service, AmazonSQSService amazonService) {
        this.service = service;
        this.amazonService = amazonService;
    }

    @Operation(summary = "Lista os clientes cadastrados na plataforma aguardando atendimento.", tags = {"clientes fila atendimento"})
    @GetMapping
    public List<DadosCadastroCliente> listar(){
        return this.service.listarClientes();
    }

    @Operation(summary = "Retira o primeiro cliente cadastrado na plataforma para ser atendido.", tags = {"clientes fila atendimento"})
    @GetMapping("/retirar")
    public DadosCadastroCliente retirarCliente(){
        return this.service.retirarCliente();
    }

    @Operation(summary = "Lista os clientes cadastrados na Amazon SQS aguardando atendimento.", tags = {"clientes fila atendimento"})
    @GetMapping("/aws")
    public List<DadosCadastroCliente> listarClientesAWS(){
        return this.amazonService.listarClientes();}

    @Operation(summary = "Retira o primeiro cliente cadastrado na Amazon SQS para ser atendido.", tags = {"clientes fila atendimento"})
    @GetMapping("/aws/retirar")
    public DadosCadastroCliente retirarClienteAWS() { return this.amazonService.retirarCliente();}
}
