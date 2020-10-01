package com.mj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mj.dao.entity.person;

@Component
public class PersonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	org.slf4j.Logger logger = LoggerFactory.getLogger(PersonDao.class);

	public void insert(com.mj.dao.entity.person p) throws SQLException {
		String newRecord_sql = "insert into person (key, hcpid, last_name,first_name,ssn,address,zip,dob,email_address, update_number ) "
				+ " values (?,?,?,?,?,?,?,?,?, ?);";
		Connection con = jdbcTemplate.getDataSource().getConnection();

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
			statement.setInt(10, 0);
			statement.execute();

			statement.close();
			con.close();
		} catch (SQLException e) {
			logger.error("Exceptions[insert]: ",
					p.toString() + e.getSQLState() + e.getErrorCode() + e.getLocalizedMessage() + e.getMessage());
			throw e;
		}
		}

	public List<person> findByPerson(person p) {
		return null;
	}

	public List<person> findByKey(String key) throws SQLException {
		String search_SQL = "select * from person where key = ? ;";

		Connection con = null;

		List<person> plist = new ArrayList<person>();

		try {
			con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = con.prepareStatement(search_SQL);
			statement.setString(1, key);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				person p = new person();
				p.setKey(rs.getString("key"));
				p.setId(rs.getInt("id"));
				p.setHcpid(rs.getInt("hcpid"));
				p.setLast_name(rs.getString("last_name"));
				p.setFirst_name(rs.getString("first_name"));
				p.setSsn(rs.getString("ssn"));
				p.setAddress(rs.getString("ssn"));
				p.setZip(rs.getString("zip"));
				p.setEmail_address(rs.getString("email_address"));
				p.setCreate_timestamp(rs.getTimestamp("create_timestamp"));
				p.setDob(rs.getString("dob"));
				p.setUpdate_number(rs.getInt("update_number"));
				p.setUpdate_timestamp(rs.getTimestamp("update_timestamp"));
				plist.add(p);
			}
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Exceptions[foundRecord]: ",
					e.getSQLState() + e.getErrorCode() + e.getLocalizedMessage() + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return plist;

	}
	public List<person> findBySSN(String SSN) throws SQLException {
		String search_SQL = "select * from person where SSN = ? ;";

		Connection con = null;

		List<person> plist = new ArrayList<person>();

		try {
			con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = con.prepareStatement(search_SQL);
			statement.setString(1, SSN);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				person p = new person();
				p.setKey(rs.getString("key"));
				p.setId(rs.getInt("id"));
				p.setHcpid(rs.getInt("hcpid"));
				p.setLast_name(rs.getString("last_name"));
				p.setFirst_name(rs.getString("first_name"));
				p.setSsn(rs.getString("ssn"));
				p.setAddress(rs.getString("ssn"));
				p.setZip(rs.getString("zip"));
				p.setEmail_address(rs.getString("email_address"));
				p.setCreate_timestamp(rs.getTimestamp("create_timestamp"));
				p.setDob(rs.getString("dob"));
				p.setUpdate_number(rs.getInt("update_number"));
				p.setUpdate_timestamp(rs.getTimestamp("update_timestamp"));
				plist.add(p);
			}
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Exceptions[foundRecord]: ",
					e.getSQLState() + e.getErrorCode() + e.getLocalizedMessage() + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return plist;

	}

	public void update(person p) throws SQLException {
		String update_SQL = "update person set " + "last_name = ?, " + "first_name = ?, " + "ssn = ? , "
				+ "address = ?, " + "zip = ?, " + "dob = ?, " + "email_address = ?," + " update_timestamp = now() , "
				+ "update_number = update_number + 1 "
				+ " where key = ? and id = (select max(id) from  person where key = ?);";
		Connection con = null;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement statement = con.prepareStatement(update_SQL);

			statement.setString(1, p.getLast_name());
			statement.setString(2, p.getFirst_name());
			statement.setString(3, p.getSsn());
			statement.setString(4, p.getAddress());
			statement.setString(5, p.getZip());
			statement.setString(6, p.getDob());
			statement.setString(7, p.getEmail_address());
			statement.setString(8, p.getKey());
			statement.setString(9, p.getKey());
			statement.executeUpdate();

			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exceptions[update-0]: ",
					e.getSQLState() + e.getErrorCode() + e.getLocalizedMessage() + e.getMessage());
			throw e;
		}

	}
}
