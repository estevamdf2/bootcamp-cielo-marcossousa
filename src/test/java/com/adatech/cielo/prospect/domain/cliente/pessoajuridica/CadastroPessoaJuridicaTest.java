package com.adatech.cielo.prospect.domain.cliente.pessoajuridica;

import com.adatech.cielo.prospect.domain.cliente.pessoafisica.CadastroPessoaFisica;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;


class CadastroPessoaJuridicaTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();;
    }

    @Test
    @DisplayName("Deve retornar erro para o campo cnpj. Campo com menos de 14 caracteres")
    public void testCadastroPessoaFisica_cenario1(){
        CadastroPessoaJuridica cadastroInvalido = new CadastroPessoaJuridica(
                "0123",
                "84122878004",
                "863713790",
                "Bolos fofos - Eirelli",
                "Alan Moises",
                "alan.moises@hotmail.com"
        );

        Set<ConstraintViolation<CadastroPessoaJuridica>> violations = validator.validate(cadastroInvalido);

        for (ConstraintViolation<CadastroPessoaJuridica> violation : violations) {
            if ("cnpj".equals(violation.getPropertyPath().toString())) {
                if(violation.getMessage().contains("deve ter")){
                    Assertions.assertEquals("Campo cnpj deve ter 14 caracteres", violation.getMessage());
                } else{
                    Assertions.assertEquals("número do registro de contribuinte corporativo brasileiro (CNPJ) inválido", violation.getMessage());
                }


            }
        }
    }
}