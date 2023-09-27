package com.adatech.cielo.prospect.queue;

import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import lombok.ToString;

import java.util.Arrays;
import java.util.NoSuchElementException;

@ToString
public class DadosCadastroClienteQueue implements Queue<DadosCadastroCliente>{

    private static final int TAMANHO_INICIAL = 10;
    private Object[] elementos;
    private int tamanho;
    private int capacidade;

    public DadosCadastroClienteQueue(){
        this.capacidade = TAMANHO_INICIAL;
        this.elementos = new Object[capacidade];
        this.tamanho = 0;
    }
    @Override
    public DadosCadastroCliente enqueue(DadosCadastroCliente elemento) {
        if (tamanho == capacidade) {
            aumentarCapacidade();
        }
       return (DadosCadastroCliente) (elementos[tamanho++] = elemento);
    }

    @Override
    public DadosCadastroCliente dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("Fila de clientes está vazia.");
        }
        DadosCadastroCliente elementoExcluido = (DadosCadastroCliente) elementos[0];
        for (int i = 0; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        tamanho--;
        return elementoExcluido;
    }

    @Override
    public DadosCadastroCliente front() {
        return isEmpty() ? null : (DadosCadastroCliente) elementos[0];
    }

    @Override
    public DadosCadastroCliente rear() {
        DadosCadastroCliente dados = null;
        if(!isEmpty()){
            if(size() < tamanho){
                dados = (DadosCadastroCliente) elementos[0];
            } else{
                dados = (DadosCadastroCliente)  elementos[tamanho -1];
            }
        }

        return dados;
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return tamanho;
    }

    private void aumentarCapacidade() {
        capacidade *= 2;
        elementos = Arrays.copyOf(elementos, capacidade);
    }
}
