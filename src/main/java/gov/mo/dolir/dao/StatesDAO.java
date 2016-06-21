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
import gov.mo.dolir.models.StatesModel;


@Repository("statesDao")
public class StatesDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(StatesDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int addStates(StatesModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + STATES + " (")
            .append(" id " )
            .append(", name " )
            .append(", abbrev " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            bean.getId(), 
            bean.getName(), 
            bean.getAbbrev()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateStates(StatesModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + STATES)
        .append(" SET ")
        .append(" id = ? " )
        .append(", name = ? " )
        .append(", abbrev = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getId(), 
            bean.getName(), 
            bean.getAbbrev(),
            bean.getId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }


    public StatesModel getStates(Integer id) {
    String sqlString = "select " +
        "id" +
        ", name" +
        ", abbrev" +
        " from " + STATES + " where id = ?";
        Object[] args = {id};
        List<StatesModel> matches = getTemplate().query(sqlString, args, new RowMapper<StatesModel>() {
            public StatesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                StatesModel model = new StatesModel();
                    model.setId(rs.getInt("id"));
                    model.setName(rs.getString("name"));
                    model.setAbbrev(rs.getString("abbrev"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<StatesModel> getStatesList() {
    String sqlString = "select " +
        "id" +
        ", name" +
        ", abbrev" +
        " from " + STATES;
        return getTemplate().query(sqlString, new RowMapper<StatesModel>() {
            public StatesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                StatesModel model = new StatesModel();
                    model.setId(rs.getInt("id"));
                    model.setName(rs.getString("name"));
                    model.setAbbrev(rs.getString("abbrev"));
                return model;
            }
        });
    }


    public int deleteStates(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + STATES);
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
