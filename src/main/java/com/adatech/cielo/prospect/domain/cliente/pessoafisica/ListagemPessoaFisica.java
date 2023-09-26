package com.adatech.cielo.prospect.domain.cliente.pessoafisica;

import com.adatech.cielo.prospect.domain.cliente.PessoaFisica;

import java.util.UUID;

public record ListagemPessoaFisica(UUID uuid, String mcc, String cpf, String nomePessoa, String emailPessoa) {

    public ListagemPessoaFisica(PessoaFisica pessoa){
        this(pessoa.getUuid(), pessoa.getMcc(), pessoa.getCpf(), pessoa.getNomePessoa(), pessoa.getEmailPessoa());
    }
}
