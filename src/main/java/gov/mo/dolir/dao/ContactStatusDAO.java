package gov.mo.dolir.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.mo.dolir.constants.DBConstants;
import gov.mo.dolir.models.ContactStatusModel;


@Repository("contactStatusDao")
public class ContactStatusDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(ContactStatusDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int addContactStatus(ContactStatusModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + CONTACT_STATUS + " (")
            .append(" id " )
            .append(", description " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            bean.getId(), 
            bean.getDescription()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateContactStatus(ContactStatusModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + CONTACT_STATUS)
        .append(" SET ")
        .append(" id = ? " )
        .append(", description = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getId(), 
            bean.getDescription(),
            bean.getId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }


    public ContactStatusModel getContactStatus(Integer id) {
    String sqlString = "select " +
        "id" +
        ", description" +
        " from " + CONTACT_STATUS + " where id = ?";
        Object[] args = {id};
        List<ContactStatusModel> matches = getTemplate().query(sqlString, args, new RowMapper<ContactStatusModel>() {
            public ContactStatusModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ContactStatusModel model = new ContactStatusModel();
                    model.setId(rs.getInt("id"));
                    model.setDescription(rs.getString("description"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<ContactStatusModel> getContactStatusList() {
    String sqlString = "select " +
        "id" +
        ", description" +
        " from " + CONTACT_STATUS;
        return getTemplate().query(sqlString, new RowMapper<ContactStatusModel>() {
            public ContactStatusModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ContactStatusModel model = new ContactStatusModel();
                    model.setId(rs.getInt("id"));
                    model.setDescription(rs.getString("description"));
                return model;
            }
        });
    }


    public int deleteContactStatus(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + CONTACT_STATUS);
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


}
