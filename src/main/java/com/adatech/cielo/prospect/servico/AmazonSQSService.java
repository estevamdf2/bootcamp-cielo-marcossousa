package com.adatech.cielo.prospect.servico;

import com.adatech.cielo.prospect.domain.cliente.DadosCadastroCliente;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.sqs.model.SqsException;

import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

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

    private String GROUP_NAME="cielo-group";

    private final int NOVO_TEMPO_VISIBILIDADE=3600;

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

    public List<Message> listarMensagens(){
        AmazonSQS sqsClient = getClient();
        return sqsClient.receiveMessage(queueUrl).getMessages();
    }

    public List<DadosCadastroCliente> listarClientes(){
        return dadosDeserializer.deserealizaObjetos(listarMensagens());
    }
    public void enviarMensagem(DadosCadastroCliente dados){
        AmazonSQS sqsClient = getClient();
        try {
            SendMessageRequest sendMsgRequest = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageGroupId(GROUP_NAME)
                    .withMessageDeduplicationId(GROUP_NAME)
                    .withMessageBody(dados.toJson());

            sqsClient.sendMessage(sendMsgRequest);
        } catch (SqsException e){
            e.getStackTrace();
        }
    }

    public DadosCadastroCliente retirarCliente() {
        AmazonSQS sqsClient = getClient();
        List<Message> messages = listarMensagens();
        DadosCadastroCliente cadastroCliente = null;
        Message message = null;

            try {
                message = messages.stream().findFirst().get();
                cadastroCliente = dadosDeserializer.deserealizaObjeto(message);
                DeleteMessageResult messageResult = sqsClient.deleteMessage(queueUrl, message.getReceiptHandle());
                System.out.println("Cliente " + cadastroCliente.toString() + " removido da fila Amazon SQS " + messageResult.toString());

            } catch (NoSuchElementException e){
                throw new NoSuchElementException("Fila vazia e/ou erro ao obter clientes da fila Amazon SQS. Tente novamente.");

            } catch (AmazonSQSException e){
                    renovarReceiptHandle(queueUrl, message, NOVO_TEMPO_VISIBILIDADE);
                    retirarCliente();
                throw new AmazonSQSException("Falha ao remover cliente da fila da Amazon SQS "+e.getMessage());
            } catch (Exception e){
                throw new RuntimeException("Fila vazia ou erro na aplicação - Amazon SQS. Tente novamente.");
            }
            return cadastroCliente;
        }

    public void renovarReceiptHandle(String queueUrl, Message message, int novoTempoVisibilidade) {
        AmazonSQS sqsClient = getClient();

        String receiptHandle = message.getReceiptHandle();

        // renova o receipt handle
        ChangeMessageVisibilityRequest request = new ChangeMessageVisibilityRequest()
                .withQueueUrl(queueUrl)
                .withReceiptHandle(receiptHandle)
                .withVisibilityTimeout(novoTempoVisibilidade);

        sqsClient.changeMessageVisibility(request);
    }

}
