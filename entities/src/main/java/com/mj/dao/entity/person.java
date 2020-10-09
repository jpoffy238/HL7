package com.mj.dao.entity;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//import java.sql.Date;
////import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Locale;
//import java.util.TimeZone;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "person")
public class person {
	public final static int KEYIDX = 0;
	public final static int SPACER1 = KEYIDX + 1;
	public final static int HCPIDIDX = SPACER1 + 1;
	public final static int SPACER2 = HCPIDIDX + 1;
	public final static int NAMEIDX = SPACER2 + 1;
	public final static int SSNIDX = NAMEIDX + 1;
	public final static int ADDRESSIDX = SSNIDX + 1;
	public final static int ZIP = ADDRESSIDX + 1;
	public final static int SPACER4 = ZIP + 1;
	public final static int DOBIDX = SPACER4 + 1;
	public final static int EMAILIDX = DOBIDX + 1;
	 private final Logger logger = LoggerFactory.getLogger(person.class);

	//static private SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
	 public person () {};
	 
	public person(String[] Args) {
		//formatter.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
		for (int i = KEYIDX; i < Args.length; i++) {
			switch (i) {
			case KEYIDX:
				String t[] = Args[KEYIDX].split(" ");
				setKey(t[0]);
				break;
			case HCPIDIDX:
				logger.debug("HCPID value passed: " + Args[HCPIDIDX] );
				if (Args[HCPIDIDX] != null) {
					Integer hcpid = new Integer(Args[HCPIDIDX]);
					if (hcpid != null ) {
						this.setHcpid(hcpid.intValue());
				} 
				}
				break;
			case NAMEIDX:
				logger.debug("Name value passed: " + Args[i] );
				String tname = Args[i];
				
				if (tname != null) {
				String[] name = tname.split(",");
				if (name .length == 2 ) {
				setFirst_name(name[1]);
				setLast_name(name[0]);
					}	else {
						logger.error("Unable to parse name : " + tname);
						setLast_name(tname);
					}
				}
				break;
			case SSNIDX:
				setSsn(Args[SSNIDX]);
				break;
			case ADDRESSIDX:
				setAddress(Args[ADDRESSIDX]);
				break;
			case DOBIDX:
				setDob(Args[DOBIDX]);
				break;
			case EMAILIDX:
				setEmail_address(Args[EMAILIDX]);
				break;
			case ZIP :
				this.setZip(Args[ZIP]);
			default:
				break;

			}
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getHcpid() {
		return hcpid;
	}

	public void setHcpid(int hcpid) {
		this.hcpid = hcpid;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDob() {
		
		return dob;
	}

	public void setDob(String dob) {
		

		this.dob = dob;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	//@Column(name = "key")
	private String key;

	//@Column(name = "hcpid")
	private int hcpid;

	//@Column(name = "last_name")
	private String last_name;

	//@Column(name = "first_name")
	private String first_name;

	//@Column(name = "ssn")
	private String ssn;
	//@Column(name = "address")
	private String address;
	//@Column(name = "zip")
	private String zip;
	//@Column(name = "dob")
	private String dob;
	//@Column(name = "email_address")
	private String email_address;
	private int id;
	private Timestamp create_timestamp;
	private int update_number;
	private Timestamp update_timestamp;
	
public String toString() {
	String returnValue = "key:" + key + "\n" +
			"hcpid: " + hcpid + "\n" +
			"last_name: " + last_name + "\n" +
			"fist_name: " + first_name + "\n" +
			"ssn:  " + ssn  + "\n" +
			"address: " + address + "\n" +
			" zip: " + zip + "\n" +
			"dob: " + dob + "\n" +
			"email_address: " + email_address +"\n" +
			"id: " + id + "\n" +
			"create_timestamp: " + create_timestamp + "\n" +
			"update_number: " + update_number + "\n" +
			"update_timestamp: " + update_timestamp  + "\n";
	return returnValue;
}

public Timestamp getCreate_timestamp() {
	return create_timestamp;
}

public void setCreate_timestamp(Timestamp create_timestamp) {
	this.create_timestamp = create_timestamp;
}

public int getUpdate_number() {
	return update_number;
}

public void setUpdate_number(int update_number) {
	this.update_number = update_number;
}

public Timestamp getUpdate_timestamp() {
	return update_timestamp;
}

public void setUpdate_timestamp(Timestamp update_timestamp) {
	this.update_timestamp = update_timestamp;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
}
