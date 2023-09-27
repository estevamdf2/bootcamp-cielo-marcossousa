package com.adatech.cielo.prospect.queue;

import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.adatech.cielo.prospect.domain.cliente.PessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.PessoaJuridica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.CadastroPessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.pessoajuridica.PessoaJuridicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DadosCadastroClienteQueueTest {

    private DadosCadastroClienteQueue queue;

    @BeforeEach
    void setUp() {
        queue = new DadosCadastroClienteQueue();
    }

    @Test
    @DisplayName("Deve incluir um cliente na fila de dados cadastro cliente queue")
    void enqueue() {
        DadosCadastroCliente dados = preencheDadosCadastroCliente("1520", "86748886074", "Hélio das Neves", "helio.neves@ada.tech");

        var result = queue.enqueue(dados);

        assertEquals(1, queue.size());
        assertEquals(dados, result);
        assertEquals(dados.cpf(), result.cpf());

    }

    @Test
    @DisplayName("Deve retirar um cliente da lista de Dados cadastro cliente")
    void dequeue() {
        DadosCadastroCliente dados = preencheDadosCadastroCliente("1520", "86748886074", "Hélio das Neves", "helio.neves@ada.tech");
        queue.enqueue(dados);

        DadosCadastroCliente dados2 = preencheDadosCadastroCliente("1520", "42668460026", "Jorge Nascimento", "jorge.nas@ada.tech");
        queue.enqueue(dados2);
        assertEquals(2,queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

    }

    @Test
    @DisplayName("Deve retornar os dados cadastro de cliente que está em primeiro na fila")
    void front() {

        DadosCadastroCliente dados = preencheDadosCadastroCliente("1520", "86748886074", "Hélio das Neves", "helio.neves@ada.tech");
        DadosCadastroCliente dados2 = preencheDadosCadastroCliente("1520", "42668460026", "Jorge Nascimento", "jorge.nas@ada.tech");
        queue.enqueue(dados);
        queue.enqueue(dados2);

        DadosCadastroCliente resultado = queue.front();
        assertEquals(dados, resultado);
        //Validando alguns campos
        assertEquals(dados.cpf(), resultado.cpf());
        assertEquals(dados.nomePessoa(), resultado.nomePessoa());
    }

    @Test
    @DisplayName("Deve retornar os dados cadastro de cliente que está em último na fila")
    void rear() {
        DadosCadastroCliente dados = preencheDadosCadastroCliente("1520", "86748886074", "Hélio das Neves", "helio.neves@ada.tech");
        DadosCadastroCliente dados2 = preencheDadosCadastroCliente("1520", "42668460026", "Jorge Nascimento", "jorge.nas@ada.tech");
        queue.enqueue(dados);
        queue.enqueue(dados2);

        DadosCadastroCliente resultado = queue.rear();
        assertEquals(dados2, resultado);
        //Validando alguns campos
        assertEquals(dados2.cpf(), resultado.cpf());
        assertEquals(dados2.nomePessoa(), resultado.nomePessoa());
    }

    @Test
    @DisplayName("Deve retornar verdadeiro sinalizando que a fila está vazia.")
    void isEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar falso sinalizando que a lista não está cheia.")
    void isFull() {
        assertFalse(queue.isFull());
    }

    @Test
    @DisplayName("Deve retornar que a lista está igual a zero.")
    void size() {
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Deve atualizar o primeiro registro da fila e coloca-lo na última posição da fila.")
    void atualizar() {
        DadosCadastroCliente dados = preencheDadosCadastroCliente("1520", "86748886074", "Hélio das Neves", "helio.neves@ada.tech");
        DadosCadastroCliente dados2 = preencheDadosCadastroCliente("1520", "42668460026", "Jorge Nascimento", "jorge.nas@ada.tech");
        queue.enqueue(dados);
        queue.enqueue(dados2);

        queue.atualizar(dados);
        var resultado = queue.rear();
        assertEquals(dados, resultado);
        //Validando alguns campos
        assertEquals(dados.cpf(), resultado.cpf());
        assertEquals(dados.nomePessoa(), resultado.nomePessoa());

    }

    private static DadosCadastroCliente preencheDadosCadastroCliente(String mcc, String cpf, String nomePessoa, String emailPessoa) {
        CadastroPessoaFisica cadastro = new CadastroPessoaFisica(mcc, cpf, nomePessoa, emailPessoa);
        var pessoa = new PessoaFisica(cadastro);

        DadosCadastroCliente dados = new DadosCadastroCliente(pessoa);
        return dados;
    }
    

}