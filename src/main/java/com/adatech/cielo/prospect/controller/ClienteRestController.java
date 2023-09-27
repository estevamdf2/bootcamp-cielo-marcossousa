package com.adatech.cielo.prospect.controller;

import com.adatech.cielo.prospect.domain.cliente.ClienteService;
import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.adatech.cielo.prospect.domain.cliente.pessoajuridica.PessoaJuridicaService;
import com.adatech.cielo.prospect.queue.DadosCadastroClienteQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    private final ClienteService service;

    public ClienteRestController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<DadosCadastroCliente> listar(){
        return this.service.listarClientes();
    }

    @GetMapping("/retirar")
    public DadosCadastroCliente retirarCliente(){
        return this.service.retirarCliente();
    }
}
