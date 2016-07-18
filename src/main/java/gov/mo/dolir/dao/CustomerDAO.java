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
import gov.mo.dolir.models.CustomerModel;


@Repository("customerDao")
public class CustomerDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(CustomerDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
    
    public int getCustomerCount() {
    	String sqlString = "select count(*) as num_rows" +
        " from " + CUSTOMER;
    	return getTemplate().queryForObject(sqlString, Integer.class);
    }
	
    public int addCustomer(CustomerModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + CUSTOMER + " (")
            .append(" id " )
            .append(", first_name " )
            .append(", last_name " )
            .append(", company " )
            .append(", init_date " )
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
            bean.getFirstName(), 
            bean.getLastName(), 
            bean.getCompany(),
            bean.getInitDate(), 
            bean.getNotes()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateCustomer(CustomerModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + CUSTOMER)
        .append(" SET ")
        .append(" id = ? " )
        .append(", first_name = ? " )
        .append(", last_name = ? " )
        .append(", company = ? " )
        .append(", init_date = ? " )
        .append(", notes = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getId(), 
            bean.getFirstName(), 
            bean.getLastName(), 
            bean.getCompany(),
            bean.getInitDate(), 
            bean.getNotes(),
            bean.getId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }


    public CustomerModel getCustomer(Integer id) {
    String sqlString = "select " +
        "id" +
        ", first_name" +
        ", last_name" +
        ", company" +
        ", init_date" +
        ", notes" +
        " from " + CUSTOMER + " where id = ?";
        Object[] args = {id};
        List<CustomerModel> matches = getTemplate().query(sqlString, args, new RowMapper<CustomerModel>() {
            public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomerModel model = new CustomerModel();
                    model.setId(rs.getInt("id"));
                    model.setFirstName(rs.getString("first_name"));
                    model.setLastName(rs.getString("last_name"));
                    model.setCompany(rs.getString("company"));
                    model.setInitDate(rs.getDate("init_date")!=null?new java.util.Date(rs.getDate("init_date").getTime()):null);
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<CustomerModel> getCustomerList() {
    String sqlString = "select " +
        "id" +
        ", first_name" +
        ", last_name" +
        ", company" +
        ", init_date" +
        ", notes" +
        " from " + CUSTOMER;
        return getTemplate().query(sqlString, new RowMapper<CustomerModel>() {
            public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomerModel model = new CustomerModel();
                    model.setId(rs.getInt("id"));
                    model.setFirstName(rs.getString("first_name"));
                    model.setLastName(rs.getString("last_name"));
                    model.setCompany(rs.getString("company"));
                    model.setInitDate(rs.getDate("init_date")!=null?new java.util.Date(rs.getDate("init_date").getTime()):null);
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
    }


    public int deleteCustomer(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + CUSTOMER);
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
