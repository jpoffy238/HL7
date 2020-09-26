package com.mj;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {
	public final static String Audit = "hrc_audit";
	public final static String QueNamePrefix = "adt_";
	private final Logger logger = LoggerFactory.getLogger(JmsConfig.class);
    @Bean
    public Map<String, javax.jms.Queue> queue(){
    	HashMap<String, javax.jms.Queue> map = new HashMap<String, javax.jms.Queue>() ;
    	
    	for (int qnum = 100; qnum < 150; qnum++ ) {
    		Integer i = new Integer(qnum);
    		String qname = QueNamePrefix + i.toString();
    		logger.debug("Creating QUEUE : " + qname);
    		map.put(qname, new ActiveMQQueue(qname));
    	}
    	map.put(Audit, new ActiveMQQueue(Audit));
    	logger.debug("Creating Audit queue: " + Audit);
        return map;
    }
	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
    
   
  
}
