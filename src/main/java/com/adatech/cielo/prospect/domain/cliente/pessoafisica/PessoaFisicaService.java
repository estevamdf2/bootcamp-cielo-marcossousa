package com.adatech.cielo.prospect.domain.cliente.pessoafisica;

import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.adatech.cielo.prospect.domain.cliente.PessoaFisica;
import com.adatech.cielo.prospect.infra.exception.PessoaFisicaException;
import com.adatech.cielo.prospect.queue.DadosCadastroClienteQueue;
import com.adatech.cielo.prospect.servico.AmazonSQSService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaFisicaService {
    private final PessoaFisicaRepository repository;
    private DadosCadastroClienteQueue dadosQueue;

    private final AmazonSQSService amazonService;

    public PessoaFisicaService(PessoaFisicaRepository repository, DadosCadastroClienteQueue dadosQueue, AmazonSQSService service) {
        this.repository = repository;
        this.dadosQueue = dadosQueue;
        this.amazonService = service;
    }

    public PessoaFisica cadastrar(CadastroPessoaFisica dados){
        var pessoaFisica = new PessoaFisica(dados);
        validaSePessoaFisicaEhCadastrada(dados.cpf());
        pessoaFisica = repository.save(pessoaFisica);
        var dadosCliente = new DadosCadastroCliente(pessoaFisica);
        enviarClienteFilaAplicacao(dadosCliente);
        enviarClienteAmazonSQS(dadosCliente);
        return pessoaFisica;
    }

    private void enviarClienteAmazonSQS(DadosCadastroCliente dadosCliente) {
        this.amazonService.enviarMensagem(dadosCliente);
        System.out.println("Cliente incluído na fila AWS SQS para atendimento "+ dadosCliente.toString());
    }

    private void enviarClienteFilaAplicacao(DadosCadastroCliente dadosCliente) {
        dadosQueue.enqueue(dadosCliente);
        System.out.println("Cliente incluído na fila para atendimento: "+dadosQueue.toString());
    }

    public void validaSePessoaFisicaEhCadastrada(String cpf){
        Optional<PessoaFisica> pessoaFisicaOptional = this.repository.findByCpf(cpf);
        if(pessoaFisicaOptional.isPresent()){
            throw new PessoaFisicaException("Pessoa física com o CPF "+ cpf + " já está cadastrada.");
        }
    }

    public void pessoaFisicaNaoDeveSerCadastrada(String cpf){
        Optional<PessoaFisica> pessoaFisicaOptional = repository.findByCpf(cpf);
        if(!pessoaFisicaOptional.isPresent()){
            throw new PessoaFisicaException("Pessoa física com o CPF "+ cpf + " não está cadastrada. Utilize o método correto para cadastra-lo.");
        }
    }

    public List<ListagemPessoaFisica> listar() {
        return this.repository.findAll().stream().map(ListagemPessoaFisica::new).toList();
    }

    public PessoaFisica atualizar(CadastroPessoaFisica dados) {
        var pessoaFisica = this.repository.getReferenceByCpf(dados.cpf());
        pessoaFisicaNaoDeveSerCadastrada(dados.cpf());
        pessoaFisica.atualizarPessoaFisica(dados, pessoaFisica);
        var dadosCliente = new DadosCadastroCliente(pessoaFisica);
        dadosQueue.atualizar(dadosCliente);
        this.amazonService.enviarMensagem(dadosCliente);
        return this.repository.save(pessoaFisica);
    }

    public ListagemPessoaFisica detalharPessoa(UUID uuid) {
        var pessoa = this.repository.getReferenceByUuid(uuid);
        if(pessoa == null){
            throw new PessoaFisicaException("Pessoa física com UUID "+ uuid + " não está cadastrada.");
        }
        return new ListagemPessoaFisica(pessoa);
    }

    public void excluir(UUID uuid) {
        var pessoa = this.repository.getReferenceByUuid(uuid);
        if(pessoa == null){
            throw new PessoaFisicaException("Pessoa física com UUID "+ uuid + " não está cadastrada. Exclusão não realizada.");
        }
        this.repository.delete(pessoa);
    }
}
