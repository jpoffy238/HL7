package com.mj.Audit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.mj.dao.entity.Audit;


@Component
public class AuditReceiver {
	@Autowired
	AuditDao auditDao;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(AuditReceiver.class);
	
	@JmsListener(destination = "${Audit.queue}", containerFactory = "myFactory")
	public void receiveMessage(Audit audit) {
		logger.debug(audit.toString());
		try {
			auditDao.insert(audit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unable to insert audit message", e);
			throw new ExceptionHandlerAudit(e, "Unable to insert Audit Message" + audit.getCorrelation_key());
		}

	}
}