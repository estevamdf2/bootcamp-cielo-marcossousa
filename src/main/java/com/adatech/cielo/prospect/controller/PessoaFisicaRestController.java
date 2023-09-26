package com.adatech.cielo.prospect.controller;

import com.adatech.cielo.prospect.domain.cliente.pessoafisica.CadastroPessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.ListagemPessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.PessoaFisicaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes/pessoasfisicas")
public class PessoaFisicaRestController {

    private final PessoaFisicaService service;

    public PessoaFisicaRestController(PessoaFisicaService service) {
        this.service = service;
    }
    @Operation(summary = "Realiza o cadastro de uma pessoa física", tags = {"/clientes/pessoasfisicas"})
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroPessoaFisica dados, UriComponentsBuilder uriBuilder){
        var pessoaFisica = this.service.cadastrar(dados);
        var uri = uriBuilder.path("/clientes/pessoasjuridicas/{id}").buildAndExpand(pessoaFisica.getId()).toUri();
        return ResponseEntity.created(uri).body(new ListagemPessoaFisica(pessoaFisica));
    }

    @Operation(summary = "Lista todas as pessoas físicas cadastradas", tags = {"/clientes/pessoasfisicas"})
    @GetMapping
    public List<ListagemPessoaFisica> listarPessoas(){
        var pessoas = this.service.listar();
        return ResponseEntity.ok(pessoas).getBody();
    }

    @Operation(summary = "Atualiza os dados de uma pessoa física", tags = {"/clientes/pessoasfisicas"})
    @PutMapping
    public ListagemPessoaFisica atualizar(@RequestBody @Valid CadastroPessoaFisica dados){
        var pessoaFisica = this.service.atualizar(dados);
        return new ListagemPessoaFisica(pessoaFisica);
    }

    @Operation(summary = "Detalha os dados de uma pessoa física cadastrada", tags = {"/clientes/pessoasfisicas"})
    @GetMapping("/{uuid}")
    public ResponseEntity detalharPessoa(@PathVariable UUID uuid){
        var pessoa = this.service.detalharPessoa(uuid);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Realiza a exclusão de uma pessoa física cadastrada", tags = {"/clientes/pessoasfisicas"})
    @DeleteMapping("{uuid}")
    @Transactional
    public ResponseEntity excluir(@PathVariable UUID uuid){
        this.service.excluir(uuid);
        return ResponseEntity.noContent().build();
    }
}
