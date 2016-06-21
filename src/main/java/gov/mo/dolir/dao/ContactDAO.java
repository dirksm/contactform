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
import gov.mo.dolir.models.ContactModel;


@Repository("contactDao")
public class ContactDAO implements DBConstants {
	
    private static Logger log = LoggerFactory.getLogger(ContactDAO.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
	
    public int getContactCount() {
    	String sqlString = "select count(*) as num_rows" +
        " from " + CONTACT;
    	return getTemplate().queryForObject(sqlString, Integer.class);
    }

    public int addContact(ContactModel bean) {
        StringBuffer sInsertStmt = new StringBuffer(200);
        sInsertStmt.append( "INSERT INTO " + CONTACT + " (")
            .append(" id " )
            .append(", customer_id " )
            .append(", status_cd " )
            .append(", email " )
            .append(", website " )
            .append(", salutation " )
            .append(", contact_name " )
            .append(", title " )
            .append(", dept " )
            .append(", work_phone " )
            .append(", cell_phone " )
            .append(", fax " )
            .append(", notes " )
            .append(") VALUES ( ")
            .append(" ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(", ?")
            .append(")");
        Object[] args = {
            bean.getId(), 
            bean.getCustomerId(), 
            bean.getStatusCd(), 
            bean.getEmail(), 
            bean.getWebsite(), 
            bean.getSalutation(), 
            bean.getContactName(), 
            bean.getTitle(), 
            bean.getDept(), 
            bean.getWorkPhone(), 
            bean.getCellPhone(), 
            bean.getFax(), 
            bean.getNotes()};
        int numRows = getTemplate().update(sInsertStmt.toString(), args);
        return getAutoIncrementKey();
    }


    public int updateContact(ContactModel bean) {
        StringBuffer sUpdateStmt = new StringBuffer(200);
        sUpdateStmt.append("UPDATE " + CONTACT)
        .append(" SET ")
        .append(" id = ? " )
        .append(", customer_id = ? " )
        .append(", status_cd = ? " )
        .append(", email = ? " )
        .append(", website = ? " )
        .append(", salutation = ? " )
        .append(", contact_name = ? " )
        .append(", title = ? " )
        .append(", dept = ? " )
        .append(", work_phone = ? " )
        .append(", cell_phone = ? " )
        .append(", fax = ? " )
        .append(", notes = ? " ); 
        StringBuffer sWhereStmt = new StringBuffer(100);
        sWhereStmt.append(" WHERE id = ?");
        sUpdateStmt.append( sWhereStmt );
        Object[] args = {
            bean.getId(), 
            bean.getCustomerId(), 
            bean.getStatusCd(), 
            bean.getEmail(), 
            bean.getWebsite(), 
            bean.getSalutation(), 
            bean.getContactName(), 
            bean.getTitle(), 
            bean.getDept(), 
            bean.getWorkPhone(), 
            bean.getCellPhone(), 
            bean.getFax(), 
            bean.getNotes(),
            bean.getId()};
        int numRows = getTemplate().update(sUpdateStmt.toString(), args);
        return numRows;
    }


    public ContactModel getContact(Integer id) {
    String sqlString = "select " +
        "id" +
        ", customer_id" +
        ", (select company from " + CUSTOMER + " c where c.id = customer_id) as companyName" +
        ", status_cd" +
        ", (select description from " + CONTACT_STATUS + " s where s.id = status_cd) as status" +
        ", email" +
        ", website" +
        ", salutation" +
        ", contact_name" +
        ", title" +
        ", dept" +
        ", work_phone" +
        ", cell_phone" +
        ", fax" +
        ", notes" +
        " from " + CONTACT + " where id = ?";
        Object[] args = {id};
        List<ContactModel> matches = getTemplate().query(sqlString, args, new RowMapper<ContactModel>() {
            public ContactModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ContactModel model = new ContactModel();
                    model.setId(rs.getInt("id"));
                    model.setCustomerId(rs.getInt("customer_id"));
                    model.setCompanyName(rs.getString("companyName"));
                    model.setStatus(rs.getString("status"));
                    model.setStatusCd(rs.getInt("status_cd"));
                    model.setEmail(rs.getString("email"));
                    model.setWebsite(rs.getString("website"));
                    model.setSalutation(rs.getInt("salutation"));
                    model.setContactName(rs.getString("contact_name"));
                    model.setTitle(rs.getString("title"));
                    model.setDept(rs.getString("dept"));
                    model.setWorkPhone(rs.getString("work_phone"));
                    model.setCellPhone(rs.getString("cell_phone"));
                    model.setFax(rs.getString("fax"));
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
        return matches!=null&&matches.size()>0?matches.get(0):null;
    }


    public List<ContactModel> getContactList() {
    String sqlString = "select " +
        "id" +
        ", customer_id" +
        ", (select company from " + CUSTOMER + " c where c.id = customer_id) as companyName" +
        ", status_cd" +
        ", (select description from " + CONTACT_STATUS + " s where s.id = status_cd) as status" +
        ", email" +
        ", website" +
        ", salutation" +
        ", contact_name" +
        ", title" +
        ", dept" +
        ", work_phone" +
        ", cell_phone" +
        ", fax" +
        ", notes" +
        " from " + CONTACT;
        return getTemplate().query(sqlString, new RowMapper<ContactModel>() {
            public ContactModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ContactModel model = new ContactModel();
                    model.setId(rs.getInt("id"));
                    model.setCustomerId(rs.getInt("customer_id"));
                    model.setStatusCd(rs.getInt("status_cd"));
                    model.setCompanyName(rs.getString("companyName"));
                    model.setStatus(rs.getString("status"));
                    model.setEmail(rs.getString("email"));
                    model.setWebsite(rs.getString("website"));
                    model.setSalutation(rs.getInt("salutation"));
                    model.setContactName(rs.getString("contact_name"));
                    model.setTitle(rs.getString("title"));
                    model.setDept(rs.getString("dept"));
                    model.setWorkPhone(rs.getString("work_phone"));
                    model.setCellPhone(rs.getString("cell_phone"));
                    model.setFax(rs.getString("fax"));
                    model.setNotes(rs.getString("notes"));
                return model;
            }
        });
    }


    public int deleteContact(Integer id) {
        StringBuffer sDeleteStmt = new StringBuffer(200);
        sDeleteStmt.append("DELETE FROM " + CONTACT);
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
