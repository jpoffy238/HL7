package com.mj;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Map;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mj.dao.entity.Audit;
import com.mj.dao.entity.MessageState;

@RestController
@RequestMapping("/api")
public class MessageController {
	private Decoder decoder = Base64.getDecoder();
	@Autowired
	private Map<String, Queue> queue;

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private Queue auditDestination = null;

	@GetMapping("message/{message}")
	public ResponseEntity<String> publish(@PathVariable("message") final String message) {
		if (auditDestination == null) {
			auditDestination = queue.get(JmsConfig.Audit);
		}
		Audit audit = new Audit();
		audit.setMessage(message);

		try {
			audit.setCorrelation_key(toHexString(getSHA(message)));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		// jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		String s = new String(decoder.decode(message));

		String[] tokens = s.split("\\|");
		
		if (tokens.length > 2) {
			audit.setKey(tokens[0].split(" ")[0]);
			String hcpid = JmsConfig.QueNamePrefix + tokens[2];
			audit.setHcpid(tokens[2]);
			audit.setState(MessageState.DECODED);
			audit.setMessage(s);
			
			jmsTemplate.convertAndSend(auditDestination, audit);
			
			Queue destination = queue.get(hcpid);
			//Queue destination = queue.get("adt_100");
			if (destination != null) {
				
				jmsTemplate.convertAndSend(destination, s);
				/*, m -> {
  		            m.setJMSCorrelationID(audit.getCorrelation_key());
		            m.setJMSMessageID(audit.getKey());
		           
		            return m;
		        }); */
				audit.setState(MessageState.SENT);
				jmsTemplate.convertAndSend(auditDestination, audit);
				return new ResponseEntity<String>(hcpid, HttpStatus.OK);
			} else {
				audit.setState(MessageState.FAILED);
				audit.setMessage("Queue not defined");
				jmsTemplate.convertAndSend(auditDestination, audit);
				return new ResponseEntity<String>(hcpid, HttpStatus.FORBIDDEN);

			}
		} else {
			audit.setState(MessageState.FAILED);
			audit.setMessage("Bad Message");
			jmsTemplate.convertAndSend(auditDestination, audit);
			return new ResponseEntity<String>("FORMAT FAULT", HttpStatus.FORBIDDEN);
		}

	}

	public byte[] getSHA(String input) throws NoSuchAlgorithmException {
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	public String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}
}

// last line
