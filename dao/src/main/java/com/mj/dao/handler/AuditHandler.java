package com.mj.dao.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mj.dao.ConnectionManager;
import com.mj.dao.entity.Audit;

public class AuditHandler {
	private final Logger logger = LoggerFactory.getLogger(AuditHandler.class);
	private static ConnectionManager cm = new ConnectionManager();
	private Audit audit;
	public AuditHandler (Audit audit) {
		this.audit = audit;
	}
	public void execute() {		
			insert(audit);
		}
	private void insert(Audit a) {
		String newRecord_sql = "insert into audit (key, hcpid, last_name,first_name,ssn,address,zip,dob,email_address ) "
				+ " values (?,?,?,?,?,?,?,?,?);";
		Connection con = cm.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(newRecord_sql);
	
		
			
			statement.execute();
		
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			logger.error("Exceptions[insert]: ", a.toString() + 
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

	
}
