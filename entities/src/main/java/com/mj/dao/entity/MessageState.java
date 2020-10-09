package com.mj.dao.entity;

public enum MessageState {
	RECEIVE  ("RECEIVE") , // raw message received
	DECODED ("DECODED"), // Message decoded
	SENT ("SENT"),      // Message sent to queue
	DEQUEUED ("DEQUEUED"), // Message dequeued 
	PROCESSING ( "PROCESSING"), // Messaged processin
	COMPLETED( "COMPLETED"), // Message processed 
	FAILED ( "FAILED"), // message failed
	CRC_FAIL ("CRC_FAIL"), // message failed validation
	FORMAT_ERROR("FORMAT_ERROR"),
	INSERT("INSERT"),
	UPDATE("UPDATE");
	private String value;
	
	private MessageState (String name) {
		value = name;
	}
	public String toString() {
		return value;
	}
}
