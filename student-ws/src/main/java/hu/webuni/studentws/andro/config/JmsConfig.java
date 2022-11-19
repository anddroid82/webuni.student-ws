package hu.webuni.studentws.andro.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class JmsConfig {
	
	private String brokerUrl="tcp://localhost:8090";
	
	@Bean
	public DefaultJmsListenerContainerFactory empJmsContFactory() {
		DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
		containerFactory.setPubSubDomain(true);
		containerFactory.setConnectionFactory(connectionFactory());
		containerFactory.setMessageConverter(jacksonJmsMessageConverter());
		containerFactory.setSubscriptionDurable(true);
		return containerFactory;
	}
	
	@Bean
	public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory();
		ActiveMQConnectionFactory activeMQConnFactory = new ActiveMQConnectionFactory();
		activeMQConnFactory.setBrokerURL(brokerUrl);
		factory.setTargetConnectionFactory(activeMQConnFactory);
		//factory.setClientId("client123");
		return factory;
	}
	
	
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter=new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	/*
	@Bean
	public BrokerService brokerService() throws Exception {
		BrokerService brokerService= new BrokerService();
		brokerService.addConnector("tcp://localhost:8089");
		brokerService.addConnector("vm://localhost");
		brokerService.setPersistent(false);
		return brokerService;
	}
	*/
}
