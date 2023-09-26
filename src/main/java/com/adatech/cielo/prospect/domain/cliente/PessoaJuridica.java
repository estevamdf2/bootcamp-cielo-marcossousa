package com.adatech.cielo.prospect.domain.cliente;

import com.adatech.cielo.prospect.domain.cliente.pessoajuridica.CadastroPessoaJuridica;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public non-sealed class PessoaJuridica extends Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 14)
    private String cnpj;

    @NotNull
    @Size(max = 50)
    private String razaoSocial;

    @NotNull
    @Size(max = 50)
    private String nomeContato;

    @NotNull
    @Email
    private String emailContato;


    public PessoaJuridica(CadastroPessoaJuridica dados) {
        this.setUuid(UUID.randomUUID());
        this.setMcc(dados.mcc());
        this.setCpf(dados.cpf());
        this.cnpj = dados.cnpj();
        this.razaoSocial = dados.razaoSocial();
        this.nomeContato = dados.nomeContato();
        this.emailContato = dados.emailContato();
    }

    public void atualizarPessoaJuridica(CadastroPessoaJuridica dados, PessoaJuridica pessoa) {
        this.setId(pessoa.getId());
        this.setUuid(pessoa.getUuid());
        this.setMcc(dados.mcc());
        this.setCpf(dados.cpf());
        this.cnpj = dados.cnpj();
        this.razaoSocial = dados.razaoSocial();
        this.nomeContato = dados.nomeContato();
        this.emailContato = dados.emailContato();
    }
}
