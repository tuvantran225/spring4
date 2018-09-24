package com.shop.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shop.model.Customer;
import com.shop.web.IndexController;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Customer findById(Integer id) {

        String sql = "SELECT * FROM customers WHERE id = ?";

        Customer customer = (Customer) jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper(Customer.class));
        
        return customer;

    }

    @Override
    public List<Customer> findAll() {
    	String sql = "SELECT * FROM customers";
    	List <Customer> customers = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Customer.class));
        return customers;

    }

    @Override
    public void add(Customer customer) {
    	String sql = "INSERT INTO customers (id, customers, Last_name, first_name, email_address, job_title, "
    			+ "business_phone, home_phone, mobile_phone, fax_number, address, city, state_province, "
    			+ "zip_postal_code, country_region, web_page, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	jdbcTemplate.update(sql,
    			customer.getId(), customer.getCompany(), customer.getLastName(), customer.getFirstName(), customer.getEmailAddress(), customer.getJobTitle(),
    			customer.getBusinessPhone(), customer.getHomePhone(), customer.getMobilePhone(), customer.getFaxNumber(), customer.getAddress(), customer.getCity(), customer.getStateProvince(),
    			customer.getZipPortalCode(), customer.getCountryRegion(), customer.getWebPage(), customer.getNotes());
    	LOGGER.info("Customer Added!!");
    }

    @Override
    public void update(Customer customer) {
    	String sql = "UPDATE customers SET company = ?, last_name = ?, first_name = ?, email_address = ?, job_title = ?, "
    			+ "business_phone = ?, home_phone = ?, mobile_phone = ?, fax_number = ?, address = ?, city = ?, state_province = ?, "
    			+ "zip_postal_code = ?, country_region= ?, web_page = ?, notes = ? WHERE id = ?";
    	jdbcTemplate.update(sql,
    			customer.getCompany(), customer.getLastName(), customer.getFirstName(), customer.getEmailAddress(), customer.getJobTitle(),
    			customer.getBusinessPhone(), customer.getHomePhone(), customer.getMobilePhone(), customer.getFaxNumber(), customer.getAddress(), customer.getCity(), customer.getStateProvince(),
    			customer.getZipPortalCode(), customer.getCountryRegion(), customer.getWebPage(), customer.getNotes());
            LOGGER.info("Customer Updated!!");
    }

    @Override
    public void delete(Integer id) {
    	String sql = "DELETE FROM customers WHERE id= ?";
    	jdbcTemplate.update(sql, id);
    	LOGGER.info("Customer Deleted!!");

    }

}
