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
import gov.mo.dolir.models.ContactActivitiesModel;


@Repository("contactActivitiesDao")
public class ContactActivitiesDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(ContactActivitiesDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int addContactActivities(ContactActivitiesModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + CONTACT_ACTIVITIES + " (")
            .append(" id " )
            .append(", contact_id " )
            .append(", activity_type_id " )
            .append(", activity_outcome_id " )
            .append(", activity_date " )
            .append(", notes " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            bean.getId(), 
            bean.getContactId(), 
            bean.getActivityTypeId(), 
            bean.getActivityOutcomeId(), 
            bean.getActivityDate(), 
            bean.getNotes()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateContactActivities(ContactActivitiesModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + CONTACT_ACTIVITIES)
        .append(" SET ")
        .append(" id = ? " )
        .append(", contact_id = ? " )
        .append(", activity_type_id = ? " )
        .append(", activity_outcome_id = ? " )
        .append(", activity_date = ? " )
        .append(", notes = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getId(), 
            bean.getContactId(), 
            bean.getActivityTypeId(), 
            bean.getActivityOutcomeId(), 
            bean.getActivityDate(), 
            bean.getNotes(),
            bean.getId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }


    public ContactActivitiesModel getContactActivities(Integer id) {
    String sqlString = "select " +
        "id" +
        ", contact_id" +
        ", activity_type_id" +
        ", activity_outcome_id" +
        ", activity_date" +
        ", notes" +
        " from " + CONTACT_ACTIVITIES + " where id = ?";
        Object[] args = {id};
        List<ContactActivitiesModel> matches = getTemplate().query(sqlString, args, new RowMapper<ContactActivitiesModel>() {
            public ContactActivitiesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ContactActivitiesModel model = new ContactActivitiesModel();
                    model.setId(rs.getInt("id"));
                    model.setContactId(rs.getInt("contact_id"));
                    model.setActivityTypeId(rs.getInt("activity_type_id"));
                    model.setActivityOutcomeId(rs.getInt("activity_outcome_id"));
                    model.setActivityDate(rs.getDate("activity_date")!=null?new java.util.Date(rs.getDate("activity_date").getTime()):null);
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<ContactActivitiesModel> getContactActivitiesList() {
    String sqlString = "select " +
        "id" +
        ", contact_id" +
        ", activity_type_id" +
        ", activity_outcome_id" +
        ", activity_date" +
        ", notes" +
        " from " + CONTACT_ACTIVITIES;
        return getTemplate().query(sqlString, new RowMapper<ContactActivitiesModel>() {
            public ContactActivitiesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ContactActivitiesModel model = new ContactActivitiesModel();
                    model.setId(rs.getInt("id"));
                    model.setContactId(rs.getInt("contact_id"));
                    model.setActivityTypeId(rs.getInt("activity_type_id"));
                    model.setActivityOutcomeId(rs.getInt("activity_outcome_id"));
                    model.setActivityDate(rs.getDate("activity_date")!=null?new java.util.Date(rs.getDate("activity_date").getTime()):null);
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
    }


    public int deleteContactActivities(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + CONTACT_ACTIVITIES);
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
