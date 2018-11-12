package br.com.hubfintech.CardProcessor;

import java.net.Socket;

import javax.net.SocketFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hubfintech.CardProcessor.businesses.TransactionBusiness;
import br.com.hubfintech.CardProcessor.dtos.RequestTransactionDTO;
import br.com.hubfintech.CardProcessor.dtos.ResponseTransactionDTO;

@SpringBootApplication
public class CardProcessorApplication {

	@Autowired
	private TransactionBusiness transactionBusiness;
	
	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext context = SpringApplication.run(CardProcessorApplication.class, args);
	        Socket socket = SocketFactory.getDefault().createSocket("localhost", 9999);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
    @Bean
    public TcpNetServerConnectionFactory cf() {
        return new TcpNetServerConnectionFactory(9999);
    }

    @Bean
    public TcpReceivingChannelAdapter inbound(AbstractServerConnectionFactory cf) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(cf);
        adapter.setOutputChannel(tcpIn());
        return adapter;
    }

    @Bean
    public MessageChannel tcpIn() {
        return new DirectChannel();
    }

    @Transformer(inputChannel = "tcpIn", outputChannel = "serviceChannel")
    @Bean
    public ObjectToStringTransformer transformer() {
        return new ObjectToStringTransformer();
    }

    @ServiceActivator(inputChannel = "serviceChannel")
    public String service(String in) {
        try {
        	ObjectMapper objectMapperRequest = new ObjectMapper();
        	RequestTransactionDTO requestTransactionDTO = objectMapperRequest.readValue(in, RequestTransactionDTO.class);
			ResponseTransactionDTO responseTransactionDTO = transactionBusiness.transact(requestTransactionDTO);
			
			ObjectMapper objectMapperResponse = new ObjectMapper();
			
			return objectMapperResponse.writeValueAsString(responseTransactionDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
}
