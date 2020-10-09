package com.mj.Audit;

import org.slf4j.LoggerFactory;

public class ExceptionHandlerAudit extends RuntimeException {
	org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionHandlerAudit.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 7499359888090828854L;
	public ExceptionHandlerAudit(Exception e, String message) {
		super(e);
		logger.error(message, e);
		
	}

}
