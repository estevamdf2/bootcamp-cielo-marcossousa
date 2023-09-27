package com.adatech.cielo.prospect.domain.cliente;

import lombok.ToString;

import java.util.UUID;


public record DadosCadastroCliente(
        String mcc,
        UUID uuid,
        String cpf,
        String cnpj,
        String razaoSocial,
        String nomeContato,
        String emailContato,
        String nomePessoa,
        String emailPessoa) {

    public DadosCadastroCliente(PessoaJuridica pessoa){
        this(pessoa.getMcc(), pessoa.getUuid(), pessoa.getCpf(), pessoa.getCnpj(), pessoa.getRazaoSocial(), pessoa.getNomeContato(), pessoa.getEmailContato(), null, null);
    }

    public DadosCadastroCliente(PessoaFisica pessoa){
        this(pessoa.getMcc(), pessoa.getUuid(), pessoa.getCpf(), null, null, null, null, pessoa.getNomePessoa(), pessoa.getEmailPessoa());
    }

    @Override
    public String toString() {
        return "DadosCadastroCliente{" +
                "mcc='" + mcc + '\'' +
                ", uuid=" + uuid +
                ", cpf='" + cpf + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", razão social='" + razaoSocial + '\'' +
                ", nome contato='" + nomeContato + '\'' +
                ", e-mail contato='" + emailContato + '\'' +
                ", nome pessoa='" + nomePessoa + '\'' +
                ", e-mail pessoa='" + emailPessoa + '\'' +
                '}';
    }
}
