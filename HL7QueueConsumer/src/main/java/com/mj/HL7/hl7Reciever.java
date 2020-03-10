package com.mj.HL7;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsMessageHeaderAccessor;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;


import com.mj.dao.PersonDao;
import com.mj.dao.entity.Audit;
import com.mj.dao.entity.MessageState;
import com.mj.dao.entity.person;

@Component
public class hl7Reciever {
	private final Logger logger = LoggerFactory.getLogger(hl7Reciever.class);
	@Autowired
	private PersonDao personDao;
	@Autowired
	private javax.jms.Queue auditq;

	@Autowired
	private JmsTemplate jmsTemplate;

	@JmsListener(destination = "${spring.activemq.queue}", containerFactory = "myFactory")
	public void receiveMessage(String message) {
		
		logger.debug(message);
		String[] tokens = message.split("\\|");
		person p = new person(tokens);
		Audit audit = new Audit();
		
		audit.setKey(p.getKey());
		audit.setState(MessageState.DEQUEUED);
		
		audit.setHcpid(Integer.toString(p.getHcpid()));
		audit.setMessage(message);
		jmsTemplate.convertAndSend(auditq, audit);

		try {
			execute(p, audit);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private void execute(person p, Audit audit) throws Exception {
		logger.debug("In Execute: Person key: " + p.getKey());
		List<person> plist = personDao.findByKey(p.getKey());
		if (plist.isEmpty()) {
			logger.debug("Not Found: Person key: " + p.getKey());
			insert(p, audit);
			logger.debug("Inserted : Person key: " + p.getKey());
		} else {
			logger.debug("Updating : Person key: " + p.getKey());
			update(p, audit);

		}
	}

	private void insert(person p, Audit audit) throws SQLException {
		logger.debug("In Insert: Person key: " + p.getKey());
		audit.setState(MessageState.INSERT);
		jmsTemplate.convertAndSend(auditq, audit);
		try {
			personDao.insert(p);
			audit.setState(MessageState.COMPLETED);
			jmsTemplate.convertAndSend(auditq, audit);
			logger.debug("Inserted: Person key: " + p.getKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			audit.setState(MessageState.FAILED);
			audit.setMessage(e.getMessage());
			jmsTemplate.convertAndSend(auditq, audit);
		}
	}

	private void update(person p, Audit audit) throws SQLException {
		logger.debug("In Update: Person key: " + p.getKey());
		audit.setState(MessageState.UPDATE);
		jmsTemplate.convertAndSend(auditq, audit);
		try {
			personDao.update(p);
			logger.debug("Updated: : Person key: " + p.getKey());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			audit.setState(MessageState.FAILED);
			audit.setMessage(e.getSQLState() + ": " + e.getMessage());
			jmsTemplate.convertAndSend(auditq, audit);
			logger.error("Update: Failed to update: PersonKey: " + p.getKey() + e.getMessage());
			throw e;
		}
		audit.setState(MessageState.COMPLETED);
		jmsTemplate.convertAndSend(auditq, audit);
	}

}