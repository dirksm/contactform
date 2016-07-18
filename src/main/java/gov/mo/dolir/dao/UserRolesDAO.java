package gov.mo.dolir.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.mo.dolir.constants.DBConstants;

@Repository("userRolesDao")
public class UserRolesDAO implements DBConstants {

	private static Logger log = LoggerFactory.getLogger(UserRolesDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int addUserRoles(String username, String role) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + USER_ROLES + " (")
            .append(" username " )
            .append(", role " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            username, 
            role};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public TreeSet<String> getUserRoles(String username) {
    String sqlString = "select " +
        " role" +
        " from " + USER_ROLES + " where username = ?";
        Object[] args = {username};
        List<String> matches = getTemplate().query(sqlString, args, new RowMapper<String>() {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("role");
            }
        });
        TreeSet<String> list = new TreeSet<String>();
        for (String match : matches) {
			list.add(match);
		}
        return list;
    }


    public int deleteUserRoles(String username) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + USER_ROLES);
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE username = ?");
        sDeleteStmt.append(sWhereStmt);
        Object[] args = {username};
        int numRows = getTemplate().update(sDeleteStmt.toString(), args);
        return numRows;
    }


    public int getAutoIncrementKey() {
        String sqlString = "select last_insert_id()";
        return getTemplate().queryForInt(sqlString);
    }
    
}
