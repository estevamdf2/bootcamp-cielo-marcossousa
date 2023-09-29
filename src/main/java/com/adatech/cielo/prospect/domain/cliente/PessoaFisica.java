package com.adatech.cielo.prospect.domain.cliente;

import com.adatech.cielo.prospect.domain.cliente.pessoafisica.CadastroPessoaFisica;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public non-sealed class PessoaFisica extends Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 50,message = "tamanho deve ser entre 0 e 50")
    private String nomePessoa;

    @NotNull
    @Email
    private String emailPessoa;

    public PessoaFisica(CadastroPessoaFisica dados){
        this.setUuid(UUID.randomUUID());
        this.setMcc(dados.mcc());
        this.setCpf(dados.cpf());
        this.nomePessoa = dados.nomePessoa();
        this.emailPessoa = dados.emailPessoa();
    }

    public void atualizarPessoaFisica(CadastroPessoaFisica dados, PessoaFisica pessoa) {
        this.setId(pessoa.getId());
        this.setUuid(pessoa.getUuid());
        this.setMcc(dados.mcc());
        this.setCpf(dados.cpf());
        this.nomePessoa = dados.nomePessoa();
        this.emailPessoa = dados.emailPessoa();
    }

}
