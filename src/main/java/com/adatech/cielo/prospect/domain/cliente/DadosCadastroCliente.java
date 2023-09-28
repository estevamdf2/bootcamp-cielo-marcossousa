package com.adatech.cielo.prospect.domain.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DadosCadastroCliente {
    String mcc;
    UUID uuid;
    String cpf;
    String cnpj;
    String razaoSocial;
    String nomeContato;
    String emailContato;
    String nomePessoa;
    String emailPessoa;

    public DadosCadastroCliente(){

    }
    public DadosCadastroCliente(PessoaJuridica pessoa){
        this.mcc = pessoa.getMcc();
        this.uuid = pessoa.getUuid();
        this.cpf = pessoa.getCpf();
        this.cnpj = pessoa.getCnpj();
        this.razaoSocial = pessoa.getRazaoSocial();
        this.nomeContato = pessoa.getNomeContato();
        this.emailContato = pessoa.getEmailContato();
        this.nomePessoa = null;
        this.emailPessoa = null;
        }

    public DadosCadastroCliente(PessoaFisica pessoa){
        this.mcc = pessoa.getMcc();
        this.uuid = pessoa.getUuid();
        this.cpf = pessoa.getCpf();
        this.cnpj = null;
        this.razaoSocial = null;
        this.nomeContato = null;
        this.emailContato = null;
        this.nomePessoa = pessoa.getNomePessoa();
        this.emailPessoa = pessoa.getEmailPessoa();
    }

        @Override
        public String toString() {
            return "DadosCadastroCliente{" +
                    "'mcc='" + mcc + '\'' +
                    ", uuid=" + uuid +
                    ", cpf='" + cpf + '\'' +
                    ", cnpj='" + cnpj + '\'' +
                    ", razãoSocial='" + razaoSocial + '\'' +
                    ", nomeContato='" + nomeContato + '\'' +
                    ", emailContato='" + emailContato + '\'' +
                    ", nomePessoa='" + nomePessoa + '\'' +
                    ", emailPessoa='" + emailPessoa + '\'' +
                    '}';
        }
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
