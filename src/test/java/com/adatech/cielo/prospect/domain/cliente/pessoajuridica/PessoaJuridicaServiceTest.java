package com.adatech.cielo.prospect.domain.cliente.pessoajuridica;

import com.adatech.cielo.prospect.domain.cliente.PessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.PessoaJuridica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.CadastroPessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.ListagemPessoaFisica;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.PessoaFisicaRepository;
import com.adatech.cielo.prospect.domain.cliente.pessoafisica.PessoaFisicaService;
import com.adatech.cielo.prospect.infra.exception.PessoaFisicaException;
import com.adatech.cielo.prospect.infra.exception.PessoaJuridicaException;
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

class PessoaJuridicaServiceTest {

    @Mock
    private PessoaJuridicaRepository repository;

    private PessoaJuridicaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PessoaJuridicaService(repository);
    }

    @Test
    @DisplayName("Deve cadastrar uma pessoa jurídica com sucesso.")
    void cadastrar() {
        CadastroPessoaJuridica cadastro = new CadastroPessoaJuridica("0171", "84122878004","86371379000161", "Bolos fofos Eirelli", "Joselia Silva", "contato@bolosfofos.com");
        when(repository.findByCnpj(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(new PessoaJuridica(cadastro));
        PessoaJuridica pessoaJuridica = service.cadastrar(cadastro);
        assertNotNull(pessoaJuridica);
        verify(repository, times(1)).save(any(PessoaJuridica.class));
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar cadastar uma pessoa jurídica já cadastrada")
    void validaSePessoaJuridicaEhCadastrada() {
        CadastroPessoaJuridica cadastro = new CadastroPessoaJuridica("0171", "84122878004","86371379000161", "Bolos fofos Eirelli", "Joselia Silva", "contato@bolosfofos.com");
        when(repository.findByCnpj(anyString())).thenReturn(Optional.of(new PessoaJuridica()));

        assertThrows(PessoaJuridicaException.class, () -> service.cadastrar(cadastro));
    }

    @Test
    @DisplayName("Deve listar pessoas jurídicas cadastradas")
    void listar() {
        var pessoa = new CadastroPessoaJuridica("0171", "84122878004","86371379000161", "Bolos fofos Eirelli", "Joselia Silva", "contato@bolosfofos.com");
        when(repository.findAll()).thenReturn(List.of(new PessoaJuridica(pessoa)));
        List<ListagemPessoaJuridica> lista = service.listar();

        Assertions.assertFalse(lista.isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Não deve atualizar uma pessoa jurídica já cadastrada.")
    void atualizar() {
        CadastroPessoaJuridica atualiza = new CadastroPessoaJuridica("0171", "84122878004","86371379000161", "Bolos fofos Eirelli", "Joselia Silva", "contato@bolosfofos.com");
        when(repository.getReferenceByCnpj(anyString())).thenReturn(new PessoaJuridica());
        when(repository.save(any())).thenReturn(new PessoaJuridica(atualiza));

        assertThrows(PessoaJuridicaException.class, () -> service.atualizar(atualiza));
    }

    @Test
    @DisplayName("Não deve detalhar uma pessoa jurídica cadastrada.")
    void detalharPessoa() {
        UUID uuid = UUID.randomUUID();
        when(repository.getReferenceByUuid(uuid)).thenReturn(null);

        assertThrows(PessoaJuridicaException.class, () -> service.detalharPessoa(uuid));
    }

    @Test
    @DisplayName("Deve detalhar uma pessoa jurídica cadastrada.")
    void detalharPessoa_cenario2() {
        CadastroPessoaJuridica cadastro = new CadastroPessoaJuridica("0171", "84122878004","86371379000161", "Bolos fofos Eirelli", "Joselia Silva", "contato@bolosfofos.com");
        when(repository.findByCnpj(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(new PessoaJuridica(cadastro));
        PessoaJuridica pessoaJuridica = service.cadastrar(cadastro);

        UUID uuid = pessoaJuridica.getUuid();
        when(repository.getReferenceByUuid(uuid)).thenReturn(pessoaJuridica);
        ListagemPessoaJuridica detalhe = service.detalharPessoa(uuid);

        assertNotNull(detalhe);
        assertEquals(uuid, detalhe.uuid());
    }

    @Test
    @DisplayName("Deve excluir uma pessoa física.")
    void excluir() {
        UUID uuid = UUID.randomUUID();
        when(repository.getReferenceByUuid(uuid)).thenReturn(null);

        assertThrows(PessoaJuridicaException.class, () -> service.excluir(uuid));
    }
}