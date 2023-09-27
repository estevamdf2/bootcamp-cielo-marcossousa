package com.adatech.cielo.prospect.domain.cliente;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public sealed abstract class Cliente permits PessoaFisica, PessoaJuridica {

    private UUID uuid;

    @Column
    @NotNull
    @Size(max=4)
    private String mcc;

    @NotNull
    @Size(max = 11)
    private String cpf;

}
