package gov.mo.dolir.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.mo.dolir.constants.DBConstants;
import gov.mo.dolir.models.AddressModel;
import gov.mo.dolir.models.CustomerAddressModel;


@Repository("addressDao")
public class AddressDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(AddressDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int getAddressCount() {
    	String sqlString = "select count(*) as num_rows" +
        " from " + ADDRESS;
    	return getTemplate().queryForObject(sqlString, Integer.class);
    }

    public int addAddress(AddressModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + ADDRESS + " (")
            .append(" id " )
            .append(", address_1 " )
            .append(", address_2 " )
            .append(", city " )
            .append(", state " )
            .append(", zip " )
            .append(", notes " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            bean.getId(), 
            bean.getAddress1(), 
            bean.getAddress2(), 
            bean.getCity(), 
            bean.getState(), 
            bean.getZip(), 
            bean.getNotes()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateAddress(AddressModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + ADDRESS)
        .append(" SET ")
        .append(" id = ? " )
        .append(", address_1 = ? " )
        .append(", address_2 = ? " )
        .append(", city = ? " )
        .append(", state = ? " )
        .append(", zip = ? " )
        .append(", notes = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getId(), 
            bean.getAddress1(), 
            bean.getAddress2(), 
            bean.getCity(), 
            bean.getState(), 
            bean.getZip(), 
            bean.getNotes(),
            bean.getId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }
    
    public int inactivateAddressForCustomer(Integer customerId) {
    	StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + CUSTOMER_ADDRESS)
        .append(" SET ")
        .append(" date_address_to = ? " );
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE customer_id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {new Date(),customerId};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }

    public AddressModel getCurrentAddress(Integer customerId) {
        String sqlString = "select " +
        "id" +
        ", address_1" +
        ", address_2" +
        ", city" +
        ", state" +
        ", (select name from states s where s.id = addr.state) as longState" +
        ", (select abbrev from states s where s.id = addr.state) as shortState" +
        ", zip" +
        ", notes" +
        " from " + ADDRESS + " addr where id = (select address_id from " + CUSTOMER_ADDRESS + " where customer_id = ? and date_address_to is null)";
        Object[] args = {customerId};
        List<AddressModel> matches = getTemplate().query(sqlString, args, new RowMapper<AddressModel>() {
            public AddressModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                AddressModel model = new AddressModel();
                    model.setId(rs.getInt("id"));
                    model.setAddress1(rs.getString("address_1"));
                    model.setAddress2(rs.getString("address_2"));
                    model.setCity(rs.getString("city"));
                    model.setState(rs.getInt("state"));
                    model.setShortState(rs.getString("shortState"));
                    model.setLongState(rs.getString("longState"));
                    model.setZip(rs.getString("zip"));
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }

    public List<CustomerAddressModel> getPreviousAddress(Integer customerId) {
        String sqlString = "select " +
        "id" +
        ", address_1" +
        ", address_2" +
        ", city" +
        ", state" +
        ", (select name from states s where s.id = addr.state) as longState" +
        ", (select abbrev from states s where s.id = addr.state) as shortState" +
        ", zip" +
        ", customer_id" + 
        ", date_address_from" + 
        ", date_address_to" +
        " from " + ADDRESS + " addr " +
        " join " + CUSTOMER_ADDRESS + " caddr on (caddr.address_id = addr.id)" +
        " where customer_id = ? and date_address_to is not null order by date_address_to desc";
        Object[] args = {customerId};
        return getTemplate().query(sqlString, args, new RowMapper<CustomerAddressModel>() {
            public CustomerAddressModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            	CustomerAddressModel model = new CustomerAddressModel();
                    model.setId(rs.getInt("id"));
                    model.setAddressId(rs.getInt("id"));
                    model.setCustomerId(rs.getInt("customer_id"));
                    model.setAddress1(rs.getString("address_1"));
                    model.setAddress2(rs.getString("address_2"));
                    model.setCity(rs.getString("city"));
                    model.setState(rs.getInt("state"));
                    model.setShortState(rs.getString("shortState"));
                    model.setLongState(rs.getString("longState"));
                    model.setZip(rs.getString("zip"));
                    model.setDateAddressFrom(rs.getDate("date_address_from")!=null?new java.util.Date(rs.getDate("date_address_from").getTime()):null);
                    model.setDateAddressTo(rs.getDate("date_address_to")!=null?new java.util.Date(rs.getDate("date_address_to").getTime()):null);
                return model;
            }
        });
    }

    public AddressModel getAddress(Integer id) {
    String sqlString = "select " +
        "id" +
        ", address_1" +
        ", address_2" +
        ", city" +
        ", state" +
        ", (select name from states s where s.id = addr.state) as longState" +
        ", (select abbrev from states s where s.id = addr.state) as shortState" +
        ", zip" +
        ", notes" +
        " from " + ADDRESS + " addr where id = ?";
        Object[] args = {id};
        List<AddressModel> matches = getTemplate().query(sqlString, args, new RowMapper<AddressModel>() {
            public AddressModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                AddressModel model = new AddressModel();
                    model.setId(rs.getInt("id"));
                    model.setAddress1(rs.getString("address_1"));
                    model.setAddress2(rs.getString("address_2"));
                    model.setCity(rs.getString("city"));
                    model.setState(rs.getInt("state"));
                    model.setShortState(rs.getString("shortState"));
                    model.setLongState(rs.getString("longState"));
                    model.setZip(rs.getString("zip"));
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<AddressModel> getAddressList() {
    String sqlString = "select " +
        "id" +
        ", address_1" +
        ", address_2" +
        ", city" +
        ", state" +
        ", (select name from states s where s.id = addr.state) as longState" +
        ", (select abbrev from states s where s.id = addr.state) as shortState" +
        ", zip" +
        ", notes" +
        " from " + ADDRESS +" addr";
        return getTemplate().query(sqlString, new RowMapper<AddressModel>() {
            public AddressModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                AddressModel model = new AddressModel();
                    model.setId(rs.getInt("id"));
                    model.setAddress1(rs.getString("address_1"));
                    model.setAddress2(rs.getString("address_2"));
                    model.setCity(rs.getString("city"));
                    model.setState(rs.getInt("state"));
                    model.setShortState(rs.getString("shortState"));
                    model.setLongState(rs.getString("longState"));
                    model.setZip(rs.getString("zip"));
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
    }


    public int deleteAddress(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + ADDRESS);
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
