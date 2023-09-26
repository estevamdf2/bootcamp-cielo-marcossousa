package com.adatech.cielo.prospect.domain.cliente.pessoajuridica;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record CadastroPessoaJuridica(
        @NotBlank(message = "Campo mcc não pode estar em branco.")
        @Pattern(regexp = "\\d{4}", message = "Campo mcc deve ter 4 caracteres")
        String mcc,
        @NotBlank(message = "Campo CPF é obrigatório.")
        @Size(max = 11)
        @CPF
        String cpf,
        @NotBlank(message = "Campo CNPJ é obrigatório")
        @CNPJ
        @Size(max = 14)
        String cnpj,

        @NotBlank(message = "Campo Razão Social é obrigatório")
        @Size(max = 50)
        String razaoSocial,

        @NotBlank(message = "Campo Nome Contato é obrigatório")
        @Size(max = 50)
        String nomeContato,

        @NotBlank(message = "Campo E-mail é obrigatório")
        @Email
        String emailContato

        ) {
}
