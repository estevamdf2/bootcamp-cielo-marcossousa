package com.adatech.cielo.prospect.domain.cliente;

import com.adatech.cielo.prospect.queue.DadosCadastroClienteQueue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {

    private final DadosCadastroClienteQueue dadosQueue;

    public ClienteService(DadosCadastroClienteQueue dadosQueue) {
        this.dadosQueue = dadosQueue;
    }

    public List<DadosCadastroCliente> listarClientes(){
        List<DadosCadastroCliente> clientes = new ArrayList<>();

        DadosCadastroClienteQueue filaCopia = new DadosCadastroClienteQueue();
        while (!this.dadosQueue.isEmpty()) {
            DadosCadastroCliente cliente = this.dadosQueue.dequeue();
            clientes.add(cliente);
            filaCopia.enqueue(cliente);
        }

        while (!filaCopia.isEmpty()) {
            this.dadosQueue.enqueue(filaCopia.dequeue());
        }
        return clientes;
    }

    public DadosCadastroCliente retirarCliente() {
        DadosCadastroCliente cliente = null;
        boolean itera = true;
        if(dadosQueue == null || dadosQueue.isEmpty()){
            throw new NoSuchElementException("Lista vazia para retirar cliente para atendimento");
        }
        while (itera) {
            cliente = this.dadosQueue.dequeue();
            System.out.println("Cliente retirado da fila para atendimento "+cliente.toString());
            itera = false;
        }
        return cliente;
    }
}
