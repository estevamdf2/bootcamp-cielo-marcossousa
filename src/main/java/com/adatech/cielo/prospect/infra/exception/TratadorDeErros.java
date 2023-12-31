package com.adatech.cielo.prospect.infra.exception;

import com.adatech.cielo.prospect.domain.cliente.ValidacaoException;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegrasNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(PessoaJuridicaException.class)
    public ResponseEntity<String> handlePessoaJuridicaException(PessoaJuridicaException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PessoaFisicaException.class)
    public ResponseEntity<String> handlePessoaFisicaException(PessoaFisicaException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleRetirarClienteFilaException(NoSuchElementException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AmazonSQSException.class)
    public ResponseEntity<String> handleAmazonSQSException(AmazonSQSException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
