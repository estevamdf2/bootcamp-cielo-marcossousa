package com.adatech.cielo.prospect.domain.cliente.pessoajuridica;

import com.adatech.cielo.prospect.domain.cliente.PessoaJuridica;

import java.util.UUID;

public record ListagemPessoaJuridica(UUID uuid, String mcc, String razaoSocial, String cnpj, String nomeContato, String emailContato) {
    public ListagemPessoaJuridica(PessoaJuridica pj){
        this(pj.getUuid(),pj.getMcc(), pj.getRazaoSocial(), pj.getCnpj(), pj.getNomeContato(), pj.getEmailContato());
    }
}
