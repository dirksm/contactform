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
import gov.mo.dolir.models.UserModel;
import gov.mo.dolir.util.AppUtil;

@Repository("userDao")
public class UserDAO implements DBConstants {

	private static Logger log = LoggerFactory.getLogger(UserDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int addUsers(UserModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + USERS + " (")
            .append(" username " )
            .append(", first_name " )
            .append(", last_name " )
            .append(", password " )
            .append(", email " )
            .append(", activated " )
            .append(", position " )
            .append(", created_by " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            bean.getUsername(), 
            bean.getFirstName(), 
            bean.getLastName(), 
            AppUtil.encodeSHA(bean.getPassword()),
            bean.getEmail(), 
            bean.isActivated(), 
            bean.getPosition(), 
            bean.getCreatedBy()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateUsers(UserModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + USERS)
        .append(" SET ")
        .append(" first_name = ? " )
        .append(", last_name = ? " )
        .append(", email = ? " )
        .append(", activated = ? " )
        .append(", position = ? " )
        .append(", modified_by = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getFirstName(), 
            bean.getLastName(), 
            bean.getEmail(), 
            bean.isActivated(), 
            bean.getPosition(), 
            bean.getModifiedBy(),
            bean.getId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }

    public int updatePassword(String username, String password, String modifiedUser) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + USERS)
        .append(" SET ")
        .append(" password = ? " )
        .append(", modified_by = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE username = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            AppUtil.encodeSHA(password),
            modifiedUser,
            username};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }

    public UserModel getUsers(Integer id) {
    String sqlString = "select " +
        "id" +
        ", username" +
        ", first_name" +
        ", last_name" +
        ", email" +
        ", activated" +
        ", last_login" +
        ", position" +
        ", created" +
        ", modified" +
        ", created_by" +
        ", modified_by" +
        " from " + USERS + " where id = ?";
        Object[] args = {id};
        List<UserModel> matches = getTemplate().query(sqlString, args, new RowMapper<UserModel>() {
            public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserModel model = new UserModel();
                    model.setId(rs.getInt("id"));
                    model.setUsername(rs.getString("username"));
                    model.setFirstName(rs.getString("first_name"));
                    model.setLastName(rs.getString("last_name"));
                    model.setEmail(rs.getString("email"));
                    model.setActivated(rs.getBoolean("activated"));
                    model.setLastLogin(rs.getTimestamp("last_login")!=null?new java.util.Date(rs.getTimestamp("last_login").getTime()):null);
                    model.setPosition(rs.getString("position"));
                    model.setCreated(rs.getTimestamp("created")!=null?new java.util.Date(rs.getTimestamp("created").getTime()):null);
                    model.setModified(rs.getTimestamp("modified")!=null?new java.util.Date(rs.getTimestamp("modified").getTime()):null);
                    model.setCreatedBy(rs.getString("created_by"));
                    model.setModifiedBy(rs.getString("modified_by"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }

    public UserModel getUserByUsername(String username) {
    String sqlString = "select " +
        "id" +
        ", username" +
        ", first_name" +
        ", last_name" +
        ", email" +
        ", activated" +
        ", last_login" +
        ", position" +
        ", created" +
        ", modified" +
        ", created_by" +
        ", modified_by" +
        " from " + USERS + " where username = ?";
        Object[] args = {username};
        List<UserModel> matches = getTemplate().query(sqlString, args, new RowMapper<UserModel>() {
            public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserModel model = new UserModel();
                    model.setId(rs.getInt("id"));
                    model.setUsername(rs.getString("username"));
                    model.setFirstName(rs.getString("first_name"));
                    model.setLastName(rs.getString("last_name"));
                    model.setEmail(rs.getString("email"));
                    model.setActivated(rs.getBoolean("activated"));
                    model.setLastLogin(rs.getTimestamp("last_login")!=null?new java.util.Date(rs.getTimestamp("last_login").getTime()):null);
                    model.setPosition(rs.getString("position"));
                    model.setCreated(rs.getTimestamp("created")!=null?new java.util.Date(rs.getTimestamp("created").getTime()):null);
                    model.setModified(rs.getTimestamp("modified")!=null?new java.util.Date(rs.getTimestamp("modified").getTime()):null);
                    model.setCreatedBy(rs.getString("created_by"));
                    model.setModifiedBy(rs.getString("modified_by"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }

    public List<UserModel> getUsersList() {
    String sqlString = "select " +
        "id" +
        ", username" +
        ", first_name" +
        ", last_name" +
        ", email" +
        ", activated" +
        ", last_login" +
        ", position" +
        ", created" +
        ", modified" +
        ", created_by" +
        ", modified_by" +
        " from " + USERS;
        return getTemplate().query(sqlString, new RowMapper<UserModel>() {
            public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserModel model = new UserModel();
                    model.setId(rs.getInt("id"));
                    model.setUsername(rs.getString("username"));
                    model.setFirstName(rs.getString("first_name"));
                    model.setLastName(rs.getString("last_name"));
                    model.setEmail(rs.getString("email"));
                    model.setActivated(rs.getBoolean("activated"));
                    model.setLastLogin(rs.getTimestamp("last_login")!=null?new java.util.Date(rs.getTimestamp("last_login").getTime()):null);
                    model.setPosition(rs.getString("position"));
                    model.setCreated(rs.getTimestamp("created")!=null?new java.util.Date(rs.getTimestamp("created").getTime()):null);
                    model.setModified(rs.getTimestamp("modified")!=null?new java.util.Date(rs.getTimestamp("modified").getTime()):null);
                    model.setCreatedBy(rs.getString("created_by"));
                    model.setModifiedBy(rs.getString("modified_by"));
                return model;
            }
        });
    }
    
    public boolean validateUser(String username, String password) {
    	String sql = "select 1 from " + USERS + " where username = ? and password = ?";
        Object[] args = {username, AppUtil.encodeSHA(password)};
        List<Integer> result = getTemplate().queryForList(sql, args, Integer.class);
        return result != null && result.size() > 0;
    }


    public int deleteUsers(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + USERS);
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sDeleteStmt.append(sWhereStmt);
        Object[] args = {id};
        int numRows = getTemplate().update(sDeleteStmt.toString(), args);
        return numRows;
    }


    public int getAutoIncrementKey() {
        String sqlString = "select last_insert_id()";
        return getTemplate().queryForInt(sqlString);
    }

    public int setLastLoggedIn(String username) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + USERS)
        .append(" SET ")
        .append(" last_login = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE username = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {new Date(), username};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }
    
}
