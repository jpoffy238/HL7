package com.mj.dao.entity;

public  class Audit {
	/**
	 * 
	 */
	

	public String getCorrelation_key() {
		return correlation_key;
	}

	public void setCorrelation_key(String correlation_key) {
		this.correlation_key = correlation_key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageState getState() {
		return state;
	}

	public void setState(MessageState state) {
		this.state = state;
	}

	public String getHcpid() {
		return hcpid;
	}

	public void setHcpid(String hcpid) {
		this.hcpid = hcpid;
	}
	@Override
	public String toString() {
		  String returnValue =  "Audit{" +
		                "correlation_key='" + correlation_key  +
		                ", message=" + message +
		                ", state=" + state +
		                ", hcpid = " + hcpid +
		                ", key = " + key +
		                '}';
		    
		return returnValue;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String correlation_key;
	private String message;
	private MessageState state;
	private String hcpid;
	private String key;
}
