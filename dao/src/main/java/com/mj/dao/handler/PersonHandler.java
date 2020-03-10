package com.mj.dao.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mj.dao.ConnectionManager;
import com.mj.dao.entity.person;

public class PersonHandler {
	private final Logger logger = LoggerFactory.getLogger(PersonHandler.class);
	private static ConnectionManager cm = new ConnectionManager();
	private person p;
	public person getP() {
		return p;
	}

	public void setP(person p) {
		this.p = p;
	}

	private String message;

	public PersonHandler(String message) {
		this.message = message;
	}

	public void execute() {
		parse(message);
		if (foundRecord(p)) {
			update(p);
		} else {
			insert(p);
		}
	}

	public void parse(String message) {
		String[] tokens = message.split("\\|");
		p = new person(tokens);

	}

	private boolean foundRecord(person p) {
		String search_SQL = "select count(*)  from person where key = ?;";
		Connection con = cm.getConnection();

		boolean found = false;
		try {
			PreparedStatement statement = con.prepareStatement(search_SQL);
			statement.setString(1, p.getKey());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int rowcount = rs.getInt(1);
				if (rowcount > 0) {
					found = true;
				}
			}
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Exceptions[foundRecord]: ",
					e.getSQLState() + e.getErrorCode() + e.getLocalizedMessage() + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("found: " + found + " -- " + p.getKey());
		return found;
	}

	private void insert(person p) {
		String newRecord_sql = "insert into person (key, hcpid, last_name,first_name,ssn,address,zip,dob,email_address ) "
				+ " values (?,?,?,?,?,?,?,?,?);";
		Connection con = cm.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(newRecord_sql);
			statement.setString(1, p.getKey());
			statement.setInt(2, p.getHcpid());
			statement.setString(3, p.getLast_name());
			statement.setString(4, p.getFirst_name());
			statement.setString(5, p.getSsn());
			statement.setString(6, p.getAddress());
			statement.setString(7, p.getZip());
			statement.setString(8, p.getDob());
			statement.setString(9, p.getEmail_address());
			statement.execute();
		
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			logger.error("Exceptions[insert]: ", p.toString() + 
					e.getSQLState() + e.getErrorCode() + e.getLocalizedMessage() + e.getMessage());
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				logger.error("Exceptions[insert]: ",
						e1.getSQLState() + e1.getErrorCode() + e1.getLocalizedMessage() + e1.getMessage());
			}
			try {
				con.close();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

				logger.error("Exceptions[insert]: ",
						e1.getSQLState() + e1.getErrorCode() + e1.getLocalizedMessage() + e1.getMessage());
			}
		}
	}

	private void update(person p) {
		String update_SQL = "update person set " + "last_name = ?, " + "first_name = ?, " + "ssn = ? , "
				+ "address = ?, " + "zip = ?, " + "dob = ?, " + "email_address = ?," + " update_timestamp = now() , "
				+ "update_number = update_number + 1 " + " where key = ?;";
		Connection con = cm.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(update_SQL);

			statement.setString(1, p.getLast_name());
			statement.setString(2, p.getFirst_name());
			statement.setString(3, p.getSsn());
			statement.setString(4, p.getAddress());
			statement.setString(5, p.getZip());
			statement.setString(6, p.getDob());
			statement.setString(7, p.getEmail_address());
			statement.setString(8, p.getKey());
			statement.executeUpdate();
		
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Exceptions[update]: ",
					e.getSQLState() + e.getErrorCode() + e.getLocalizedMessage() + e.getMessage());
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				logger.error("Exceptions[update]: : ", e1.getLocalizedMessage());
				e1.printStackTrace();
			}
			try {
				con.close();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				logger.error("Exceptions[update]: : ", e1.getLocalizedMessage());
				e1.printStackTrace();
			}

		}

	}
}
