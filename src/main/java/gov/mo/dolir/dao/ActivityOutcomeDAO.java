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
import gov.mo.dolir.models.ActivityOutcomeModel;


@Repository("activityOutcomeDao")
public class ActivityOutcomeDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(ActivityOutcomeDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int addActivityOutcome(ActivityOutcomeModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + ACTIVITY_OUTCOME + " (")
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


    public int updateActivityOutcome(ActivityOutcomeModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + ACTIVITY_OUTCOME)
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


    public ActivityOutcomeModel getActivityOutcome(Integer id) {
    String sqlString = "select " +
        "id" +
        ", description" +
        " from " + ACTIVITY_OUTCOME + " where id = ?";
        Object[] args = {id};
        List<ActivityOutcomeModel> matches = getTemplate().query(sqlString, args, new RowMapper<ActivityOutcomeModel>() {
            public ActivityOutcomeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ActivityOutcomeModel model = new ActivityOutcomeModel();
                    model.setId(rs.getInt("id"));
                    model.setDescription(rs.getString("description"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<ActivityOutcomeModel> getActivityOutcomeList() {
    String sqlString = "select " +
        "id" +
        ", description" +
        " from " + ACTIVITY_OUTCOME;
        return getTemplate().query(sqlString, new RowMapper<ActivityOutcomeModel>() {
            public ActivityOutcomeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ActivityOutcomeModel model = new ActivityOutcomeModel();
                    model.setId(rs.getInt("id"));
                    model.setDescription(rs.getString("description"));
                return model;
            }
        });
    }


    public int deleteActivityOutcome(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + ACTIVITY_OUTCOME);
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
