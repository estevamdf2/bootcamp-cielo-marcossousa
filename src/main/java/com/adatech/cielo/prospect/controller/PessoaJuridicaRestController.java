package com.adatech.cielo.prospect.controller;

import com.adatech.cielo.prospect.domain.cliente.pessoajuridica.CadastroPessoaJuridica;
import com.adatech.cielo.prospect.domain.cliente.pessoajuridica.ListagemPessoaJuridica;
import com.adatech.cielo.prospect.domain.cliente.pessoajuridica.PessoaJuridicaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/clientes/pj")
public class PessoaJuridicaRestController {

    private final PessoaJuridicaService service;

    public PessoaJuridicaRestController(PessoaJuridicaService service) {
        this.service = service;
    }

    @Operation(summary = "Realiza o cadastro de uma pessoa jurídica", tags = {"clientes"})
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroPessoaJuridica dados, UriComponentsBuilder uriBuilder){
        var pessoaJuridica = this.service.cadastrar(dados);
        var uri = uriBuilder.path("/clientes/pj/{id}").buildAndExpand(pessoaJuridica.getId()).toUri();
        return ResponseEntity.created(uri).body(new ListagemPessoaJuridica(pessoaJuridica));
    }

    @Operation(summary = "Lista todas as pessoas jurídicas cadastradas", tags = {"clientes"})
    @GetMapping
    public ResponseEntity listarPessoas(){
        var pessoas = this.service.listar();
        if(pessoas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pessoas);
    }

    @Operation(summary = "Atualiza os dados de uma pessoa jurídica", tags = {"clientes"})
    @PutMapping
    public ListagemPessoaJuridica atualizar(@RequestBody @Valid CadastroPessoaJuridica dados){
        var pessoaJuridica = this.service.atualizar(dados);
        return new ListagemPessoaJuridica(pessoaJuridica);
    }

    @Operation(summary = "Detalha os dados de uma pessoa jurídica cadastrada", tags = {"clientes"})
    @GetMapping("/{uuid}")
    public ResponseEntity detalharPessoa(@PathVariable UUID uuid){
        var pessoa = this.service.detalharPessoa(uuid);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Realiza a exclusão de uma pessoa jurídica cadastrada", tags = {"clientes"})
    @DeleteMapping("{uuid}")
    @Transactional
    public ResponseEntity excluir(@PathVariable UUID uuid){
        this.service.excluir(uuid);
        return ResponseEntity.noContent().build();
    }
}
