package com.adatech.cielo.prospect.domain.cliente.pessoajuridica;

import com.adatech.cielo.prospect.domain.cliente.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    Optional<PessoaJuridica> findByCnpj(String cnpj);
    PessoaJuridica getReferenceByCnpj(String cnpj);
    PessoaJuridica getReferenceByUuid(UUID uuid);
}
