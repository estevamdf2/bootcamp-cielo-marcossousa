package com.adatech.cielo.prospect.domain.cliente.pessoafisica;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

class CadastroPessoaFisicaTest {

    private  Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();;
    }

    @Test
    @DisplayName("Deve retornar erro para o campo mcc. Campo com menos de 4 caracteres")
    public void testCadastroPessoaFisica_cenario1(){
        CadastroPessoaFisica cadastroInvalido = new CadastroPessoaFisica(
                "012",
                "84122878004",
                "Alan Moises",
                "alan.moises@hotmail.com"
        );

        Set<jakarta.validation.ConstraintViolation<CadastroPessoaFisica>> violations = validator.validate(cadastroInvalido);

        for (ConstraintViolation<CadastroPessoaFisica> violation : violations) {
            if ("mcc".equals(violation.getPropertyPath().toString())) {
                Assertions.assertEquals("Campo mcc deve ter 4 caracteres", violation.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Deve retornar erro para o campo cpf. Campo com menos de 11 caracteres")
    public void testCadastroPessoaFisica_cenario2(){
        CadastroPessoaFisica cadastroInvalido = new CadastroPessoaFisica(
                "0123",
                "8412287800",
                "Alan Moises",
                "alan.moises@hotmail.com"
        );

        Set<jakarta.validation.ConstraintViolation<CadastroPessoaFisica>> violations = validator.validate(cadastroInvalido);

        for (ConstraintViolation<CadastroPessoaFisica> violation : violations) {
            if ("cpf".equals(violation.getPropertyPath().toString())) {
                Assertions.assertEquals("número do registro de contribuinte individual brasileiro (CPF) inválido", violation.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Deve retornar erro para o campo nome. Campo não informado.")
    public void testCadastroPessoaFisica_cenario3(){
        CadastroPessoaFisica cadastroInvalido = new CadastroPessoaFisica(
                "0123",
                "84122878004",
                "",
                "alan.moises@hotmail.com"
        );

        Set<jakarta.validation.ConstraintViolation<CadastroPessoaFisica>> violations = validator.validate(cadastroInvalido);

        for (ConstraintViolation<CadastroPessoaFisica> violation : violations) {
            if ("nomePessoa".equals(violation.getPropertyPath().toString())) {
                Assertions.assertEquals("Campo Nome da Pessoa é obrigatório.", violation.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Campo nome pessoa deve ter menos de 50 caracteres")
    public void testCadastroPessoaFisica_cenario4(){
        CadastroPessoaFisica cadastroInvalido = new CadastroPessoaFisica(
                "0123",
                "84122878004",
                "Alexandra Elizabeth Catherine Albuquerque Mary Windsor Silva",
                "alan.moises@hotmail.com"
        );

        Set<jakarta.validation.ConstraintViolation<CadastroPessoaFisica>> violations = validator.validate(cadastroInvalido);

        for (ConstraintViolation<CadastroPessoaFisica> violation : violations) {
            if ("nomePessoa".equals(violation.getPropertyPath().toString())) {
                Assertions.assertEquals("tamanho deve ser entre 0 e 50", violation.getMessage());
            }
        }
    }

}