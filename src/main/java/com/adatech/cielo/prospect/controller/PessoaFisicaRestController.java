package com.adatech.cielo.prospect.controller;

import com.adatech.cielo.prospect.domain.cliente.pessoafisica.CadastroPessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.ListagemPessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.PessoaFisicaService;
import com.adatech.cielo.prospect.queue.DadosCadastroClienteQueue;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes/pf")
public class PessoaFisicaRestController {

    private final PessoaFisicaService service;

    public PessoaFisicaRestController(PessoaFisicaService service) {
        this.service = service;
    }
    @Operation(summary = "Realiza o cadastro de uma pessoa física", tags = {"clientes"})
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroPessoaFisica dados, UriComponentsBuilder uriBuilder){
        var pessoaFisica = this.service.cadastrar(dados);
        var uri = uriBuilder.path("/clientes/pf/{id}").buildAndExpand(pessoaFisica.getId()).toUri();
        return ResponseEntity.created(uri).body(new ListagemPessoaFisica(pessoaFisica));
    }

    @Operation(summary = "Lista todas as pessoas físicas cadastradas", tags = {"clientes"})
    @GetMapping
    public ResponseEntity listarPessoas(){
        var pessoas = this.service.listar();
        if(pessoas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pessoas);
    }

    @Operation(summary = "Atualiza os dados de uma pessoa física", tags = {"clientes"})
    @PutMapping
    public ListagemPessoaFisica atualizar(@RequestBody @Valid CadastroPessoaFisica dados){
        var pessoaFisica = this.service.atualizar(dados);
        return new ListagemPessoaFisica(pessoaFisica);
    }

    @Operation(summary = "Detalha os dados de uma pessoa física cadastrada", tags = {"clientes"})
    @GetMapping("/{uuid}")
    public ResponseEntity detalharPessoa(@PathVariable UUID uuid){
        var pessoa = this.service.detalharPessoa(uuid);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Realiza a exclusão de uma pessoa física cadastrada", tags = {"clientes"})
    @DeleteMapping("{uuid}")
    @Transactional
    public ResponseEntity excluir(@PathVariable UUID uuid){
        this.service.excluir(uuid);
        return ResponseEntity.noContent().build();
    }
}
