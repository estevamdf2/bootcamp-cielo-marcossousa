package com.adatech.cielo.prospect.domain.cliente.pessoafisica;

import com.adatech.cielo.prospect.domain.cliente.PessoaFisica;
import com.adatech.cielo.prospect.infra.exception.PessoaFisicaException;
import com.adatech.cielo.prospect.queue.DadosCadastroClienteQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PessoaFisicaServiceTest {

    @Mock
    private PessoaFisicaRepository repository;

    private PessoaFisicaService service;

    private DadosCadastroClienteQueue dadosQueue;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PessoaFisicaService(repository, dadosQueue);
    }

    @Test
    @DisplayName("Deve cadastrar uma pessoa física com sucesso.")
    void cadastrar(){
        CadastroPessoaFisica cadastro = new CadastroPessoaFisica("0171", "84122878004", "Antonia Lemos", "antonia.lemos@gmail.com");
        when(repository.findByCpf(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(new PessoaFisica(cadastro));
        PessoaFisica pessoaFisica = service.cadastrar(cadastro);
        assertNotNull(pessoaFisica);
        verify(repository, times(1)).save(any(PessoaFisica.class));

    }

    @Test
    @DisplayName("Deve retornar erro ao tentar cadastrar uma pessoa física já cadastrada.")
    void validaSePessoaFisicaEhCadastrada() {
        CadastroPessoaFisica cadastro = new CadastroPessoaFisica("0171", "84122878004", "Antonia Lemos", "antonia.lemos@gmail.com");
        when(repository.findByCpf(anyString())).thenReturn(Optional.of(new PessoaFisica()));

        assertThrows(PessoaFisicaException.class, () -> service.cadastrar(cadastro));
    }

    @Test
    @DisplayName("Deve listar pessoas físicas cadastradas")
    void listar(){
        var pessoa = new CadastroPessoaFisica("0171", "84122878004", "Antonia Lemos", "antonia.lemos@gmail.com");
        when(repository.findAll()).thenReturn(List.of(new PessoaFisica(pessoa)));
        List<ListagemPessoaFisica> lista = service.listar();

        Assertions.assertFalse(lista.isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Não deve atualizar uma pessoa física já cadastrada.")
    void atualizar() {
        CadastroPessoaFisica atualiza = new CadastroPessoaFisica("0171", "84122878004", "Antonia Lemos", "antonia.lemos@gmail.com");
        when(repository.getReferenceByCpf(anyString())).thenReturn(new PessoaFisica());
        when(repository.save(any())).thenReturn(new PessoaFisica(atualiza));

        assertThrows(PessoaFisicaException.class, () -> service.atualizar(atualiza));

    }

    @Test
    @DisplayName("Não deve detalhar uma pessoa fisica cadastrada.")
    void detalharPessoa() {
        UUID uuid = UUID.randomUUID();
        when(repository.getReferenceByUuid(uuid)).thenReturn(null);

        assertThrows(PessoaFisicaException.class, () -> service.detalharPessoa(uuid));
    }

    @Test
    @DisplayName("Deve detalhar uma pessoa fisica cadastrada.")
    void detalharPessoa_cenario2() {
        CadastroPessoaFisica cadastro = new CadastroPessoaFisica("0171", "84122878004", "Antonia Lemos", "antonia.lemos@gmail.com");
        when(repository.findByCpf(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(new PessoaFisica(cadastro));
        PessoaFisica pessoaFisica = service.cadastrar(cadastro);

        UUID uuid = pessoaFisica.getUuid();
        when(repository.getReferenceByUuid(uuid)).thenReturn(pessoaFisica);
        ListagemPessoaFisica detalhe = service.detalharPessoa(uuid);

        assertNotNull(detalhe);
        assertEquals(uuid, detalhe.uuid());
    }

    @Test
    @DisplayName("Deve excluir uma pessoa física.")
    void excluir() {
        UUID uuid = UUID.randomUUID();
        when(repository.getReferenceByUuid(uuid)).thenReturn(null);

        assertThrows(PessoaFisicaException.class, () -> service.excluir(uuid));
    }

}