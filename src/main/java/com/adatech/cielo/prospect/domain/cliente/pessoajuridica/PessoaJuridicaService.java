package com.adatech.cielo.prospect.domain.cliente.pessoajuridica;

import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.adatech.cielo.prospect.domain.cliente.PessoaJuridica;
import com.adatech.cielo.prospect.infra.exception.PessoaJuridicaException;
import com.adatech.cielo.prospect.queue.DadosCadastroClienteQueue;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository repository;
    private final DadosCadastroClienteQueue dadosQueue;

    public PessoaJuridicaService(PessoaJuridicaRepository repository, DadosCadastroClienteQueue dadosQueue) {
        this.repository = repository;
        this.dadosQueue = dadosQueue;
    }

    public PessoaJuridica cadastrar(CadastroPessoaJuridica dados){
        var pessoaJuridica = new PessoaJuridica(dados);
        validaSePessoaJuridicaEhCadastrada(dados.cnpj());
        pessoaJuridica = this.repository.save(pessoaJuridica);
        dadosQueue.enqueue(new DadosCadastroCliente(pessoaJuridica));
        System.out.println("Cliente incluído na fila para atendimento "+dadosQueue.toString());
        return pessoaJuridica;
    }

    public void validaSePessoaJuridicaEhCadastrada(String cnpj){
        Optional<PessoaJuridica> pessoaJuridicaOptional = repository.findByCnpj(cnpj);
        if(pessoaJuridicaOptional.isPresent()){
            throw new PessoaJuridicaException("Pessoa jurídica com o CNPJ "+ cnpj + " já está cadastrada.");
        }
    }

    public void pessoaJuridicaNaoDeveSerCadastrada(String cnpj){
        Optional<PessoaJuridica> pessoaJuridicaOptional = repository.findByCnpj(cnpj);
        if(!pessoaJuridicaOptional.isPresent()){
            throw new PessoaJuridicaException("Pessoa jurídica com o CNPJ "+ cnpj + " não está cadastrada. Utilize o método correto para cadastra-lo.");
        }
    }

    public List<ListagemPessoaJuridica> listar() {
        return this.repository.findAll().stream().map(ListagemPessoaJuridica::new).toList();
    }

    public PessoaJuridica atualizar(CadastroPessoaJuridica dados) {
        var pessoaJuridica = this.repository.getReferenceByCnpj(dados.cnpj());
        pessoaJuridicaNaoDeveSerCadastrada(dados.cnpj());
        pessoaJuridica.atualizarPessoaJuridica(dados, pessoaJuridica);
        dadosQueue.atualizar(new DadosCadastroCliente(pessoaJuridica));


        return this.repository.save(pessoaJuridica);
    }

    public ListagemPessoaJuridica detalharPessoa(UUID uuid) {
        var pessoa = this.repository.getReferenceByUuid(uuid);
        if(pessoa == null){
            throw new PessoaJuridicaException("Pessoa jurídica com UUID "+ uuid + " não está cadastrada.");
        }
        return new ListagemPessoaJuridica(pessoa);
    }

    public void excluir(UUID uuid) {
        var pessoa = this.repository.getReferenceByUuid(uuid);
        if(pessoa == null){
            throw new PessoaJuridicaException("Pessoa jurídica com UUID "+ uuid + " não está cadastrada. Exclusão não realizada.");
        }
        this.repository.delete(pessoa);
    }

}
