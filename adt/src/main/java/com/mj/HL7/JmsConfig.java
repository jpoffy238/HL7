package com.mj.HL7;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import com.mj.dao.PersonDao;




@Configuration
public class JmsConfig {

	private final Logger logger = LoggerFactory.getLogger(JmsConfig.class);

	
    
    @Bean
    public JmsListenerContainerFactory<?> myFactory(
        ConnectionFactory connectionFactory,
        DefaultJmsListenerContainerFactoryConfigurer configurer) {
      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
      
      configurer.configure(factory, connectionFactory);
      return factory;
    }
    @Bean
    @ConfigurationProperties(prefix="spring.activemq") 
    public ConnectionFactory connectionFactory(){
    	logger.debug("Creating connection to ActiveMQ");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        return connectionFactory;
    }
    @Bean
    public javax.jms.Queue auditq () {
    	return  new ActiveMQQueue("hrc_audit");
    	
    }
    @Bean
    public com.mj.dao.PersonDao personDao () {
    	return new PersonDao();
    }


   
  
}
