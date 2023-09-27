package com.adatech.cielo.prospect.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    @Bean
    public DadosCadastroClienteQueue dadosQueue(){
        return new DadosCadastroClienteQueue();
    }
}
