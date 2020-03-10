package com.mj.Audit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuditDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource masterDataSource;
	private String SQL = "insert into audit(correlation_key,  hcpid, state, message, key ) values (?,?,?,?,?);";

	org.slf4j.Logger logger = LoggerFactory.getLogger(AuditDao.class);

	public void insert(com.mj.dao.entity.Audit audit) throws Exception {

		if (jdbcTemplate != null) {

			Connection con;

			if (audit.getKey() != null) {
				logger.debug(SQL+ ": key = " +  audit.getKey());
			}
			if (audit.getCorrelation_key() != null) {
				logger.debug(SQL+ ": key = " +  audit.getCorrelation_key());
			}
			try {
				con = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement statement = con.prepareStatement(SQL);
				statement.setString(1, audit.getCorrelation_key());
				statement.setString(2, audit.getHcpid());
				statement.setString(3, audit.getState().toString());
				statement.setString(4, audit.getMessage());
				statement.setString(5, audit.getKey());
				statement.execute();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Unable to insert audit record ", e);
			}
		} else {
			if (masterDataSource == null) {
				
			
				Exception e = new Exception("Data source is null and jdbcTemplate are null");
				logger.error("Autowired Failed - manual failed", e);
				throw e;

			}
		}
	}
}