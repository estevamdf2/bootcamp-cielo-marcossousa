package com.adatech.cielo.prospect.servico;

import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.List;

@Service
public class AmazonSQSService {

    @Value("${aws.queue-name}")
    private String queueName;

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String queueUrl;

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Autowired
    private DadosCadastroClienteDeserializer dadosDeserializer;

    private AmazonSQS getClient(){
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_EAST_1)
                .build();
        return sqs;
    }

    public List<DadosCadastroCliente> listarMensagens(){
        AmazonSQS sqsClient = getClient();
        List<Message> messages = sqsClient.receiveMessage(queueUrl).getMessages();
        return dadosDeserializer.deserealizaObjeto(messages);
    }
    public void enviarMensagem(DadosCadastroCliente dados){
        AmazonSQS sqsClient = getClient();
        try {
            SendMessageRequest sendMsgRequest = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageGroupId("cielo-group")
                    .withMessageDeduplicationId("cielo-group")
                    .withMessageBody(dados.toJson());

            sqsClient.sendMessage(sendMsgRequest);
        } catch (SqsException e){
            e.getStackTrace();
        }
    }
}
