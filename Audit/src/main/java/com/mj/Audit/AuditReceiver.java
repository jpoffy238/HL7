package com.mj.Audit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.mj.dao.entity.Audit;

@Component
public class AuditReceiver {
	@Autowired
	AuditDao auditDao;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(AuditReceiver.class);
	
	
	public void receiveMessage(Audit audit) {
		logger.debug(audit.toString());
		try {
			auditDao.insert(audit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unable to insert audit message", e);
		}

	}
}