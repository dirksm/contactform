package gov.mo.dolir.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.mo.dolir.constants.DBConstants;
import gov.mo.dolir.models.PasswordResetModel;
import gov.mo.dolir.util.AppUtil;

@Repository("userAuthDao")
public class UserAuthDAO implements DBConstants {

	private static Logger log = LoggerFactory.getLogger(UserAuthDAO.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getTemplate() {
		return this.jdbcTemplate;
	}

	public boolean doesUserExist(String userName) {
		String sqlString = "SELECT " + "username " + " FROM " + USERS + " WHERE username = ?";
		Object[] args = { userName };
		List<String> matches = getTemplate().query(sqlString, args, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("username");
			}
		});
		return matches != null && matches.size() > 0 ? true : false;
	}

	public boolean validateUser(String username, String password) {
		String sqlString = "SELECT " + "username " + " FROM " + USERS + " WHERE username = ? AND password = ?";
		Object[] args = { username, AppUtil.encodeSHA(password) };
		List<String> matches = getTemplate().query(sqlString, args, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("username");
			}
		});
		return matches != null && matches.size() > 0 ? true : false;
	}

	public PasswordResetModel getResetInfo(Integer id) {
		String sqlString = "Select id, username, new_password_key, new_password_requested from " + USERS
				+ " where id = ?";
		Object[] args = { id };
		List<PasswordResetModel> matches = getTemplate().query(sqlString, args, new RowMapper<PasswordResetModel>() {
			public PasswordResetModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				PasswordResetModel model = new PasswordResetModel();
				model.setId(rs.getInt("id"));
				model.setUsername(rs.getString("username"));
				model.setKey(rs.getString("new_password_key"));
				model.setRequestDate(rs.getDate("new_password_requested") != null
						? new java.util.Date(rs.getDate("new_password_requested").getTime()) : null);
				return model;
			}
		});
		return matches != null && matches.size() > 0 ? matches.get(0) : null;
	}

	public int clearPasswordResetKeys(Integer id) {
		String sqlString = "Update " + USERS
				+ " set new_password_key = NULL, new_password_requested = NULL where id = ?";
		Object[] args = { id };
		return getTemplate().update(sqlString, args);
	}

	public String setPasswordResetKey(String username) {
		long l = System.currentTimeMillis();
		String key = AppUtil.encodeSHA(String.valueOf(l));
		String sqlString = "Update "+ USERS +" set new_password_key = ?, new_password_requested = ? where username = ?";
		Object args[] = {key, new Date(l), username};
		getTemplate().update(sqlString, args);
		return key;
	}
	
}
