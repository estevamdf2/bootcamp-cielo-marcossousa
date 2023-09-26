package com.adatech.cielo.prospect.domain.cliente.pessoafisica;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CadastroPessoaFisica(
        @NotBlank(message = "Campo mcc não pode estar em branco.")
        @Pattern(regexp = "\\d{4}", message = "Campo mcc deve ter 4 caracteres")
        String mcc,
        @NotBlank(message = "Campo CPF é obrigatório.")
        @Size(max = 11)
        @CPF
        String cpf,

        @NotBlank(message = "Campo Nome da Pessoa é obrigatório.")
        @Size(max = 50)
        String nomePessoa,

        @NotBlank(message = "Campo E-mail é obrigatório.")
        @Email
        String emailPessoa
        ) {
}
