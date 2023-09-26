package com.adatech.cielo.prospect.domain.cliente.pessoafisica;

import com.adatech.cielo.prospect.domain.cliente.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
    Optional<PessoaFisica> findByCpf(String cpf);

    PessoaFisica getReferenceByCpf(String cpf);
    PessoaFisica getReferenceByUuid(UUID uuid);
}
