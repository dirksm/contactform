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
import gov.mo.dolir.models.CustomerAddressModel;


@Repository("customerAddressDao")
public class CustomerAddressDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(CustomerAddressDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int addCustomerAddress(CustomerAddressModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + CUSTOMER_ADDRESS + " (")
            .append(" customer_id " )
            .append(", address_id " )
            .append(", date_address_from " )
            .append(", date_address_to " )
            .append(", notes " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            bean.getCustomerId(), 
            bean.getAddressId(), 
            bean.getDateAddressFrom(), 
            bean.getDateAddressTo(), 
            bean.getNotes()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateCustomerAddress(CustomerAddressModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + CUSTOMER_ADDRESS)
        .append(" SET ")
        .append(" customer_id = ? " )
        .append(", address_id = ? " )
        .append(", date_address_from = ? " )
        .append(", date_address_to = ? " )
        .append(", notes = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE customerId = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getCustomerId(), 
            bean.getAddressId(), 
            bean.getDateAddressFrom(), 
            bean.getDateAddressTo(), 
            bean.getNotes(),
            bean.getCustomerId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }


    public CustomerAddressModel getCustomerAddress(Integer customerId) {
    String sqlString = "select " +
        "customer_id" +
        ", address_id" +
        ", date_address_from" +
        ", date_address_to" +
        ", notes" +
        " from " + CUSTOMER_ADDRESS + " where customerId = ?";
        Object[] args = {customerId};
        List<CustomerAddressModel> matches = getTemplate().query(sqlString, args, new RowMapper<CustomerAddressModel>() {
            public CustomerAddressModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomerAddressModel model = new CustomerAddressModel();
                    model.setCustomerId(rs.getInt("customer_id"));
                    model.setAddressId(rs.getInt("address_id"));
                    model.setDateAddressFrom(rs.getDate("date_address_from")!=null?new java.util.Date(rs.getDate("date_address_from").getTime()):null);
                    model.setDateAddressTo(rs.getDate("date_address_to")!=null?new java.util.Date(rs.getDate("date_address_to").getTime()):null);
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<CustomerAddressModel> getCustomerAddressList() {
    String sqlString = "select " +
        "customer_id" +
        ", address_id" +
        ", date_address_from" +
        ", date_address_to" +
        ", notes" +
        " from " + CUSTOMER_ADDRESS;
        return getTemplate().query(sqlString, new RowMapper<CustomerAddressModel>() {
            public CustomerAddressModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomerAddressModel model = new CustomerAddressModel();
                    model.setCustomerId(rs.getInt("customer_id"));
                    model.setAddressId(rs.getInt("address_id"));
                    model.setDateAddressFrom(rs.getDate("date_address_from")!=null?new java.util.Date(rs.getDate("date_address_from").getTime()):null);
                    model.setDateAddressTo(rs.getDate("date_address_to")!=null?new java.util.Date(rs.getDate("date_address_to").getTime()):null);
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
    }


    public int deleteCustomerAddress(Integer customerId) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + CUSTOMER_ADDRESS);
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE customerId = ?");
        sDeleteStmt.append(sWhereStmt);
        Object[] args = {customerId};
        int numRows = getTemplate().update(sDeleteStmt.toString(), args);
        return numRows;
    }


    public int getAutoIncrementKey() {
        String sqlString = "select last_insert_id()";
        return getTemplate().queryForInt(sqlString);
    }


}
