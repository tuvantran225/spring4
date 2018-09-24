package com.shop.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.datatablespagination.model.PaginationCriteria;
import com.shop.datatablespagination.model.TablePage;
import com.shop.model.Customer;
import com.shop.service.CustomerService;

@Controller
public class TablesController {

	private final Logger logger = LoggerFactory.getLogger(TablesController.class);
	
	private CustomerService customerService;
	
    @Autowired
    public void setCustomerService(CustomerService userService) {
        this.customerService = userService;
    }

	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public String tables() {
		logger.debug("tables()");
		return "tables";
	}
	
	// list all customers
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseBody
    public TablePage getCustomersData(@RequestBody(required = false) PaginationCriteria treq) {
    	logger.debug("getCustomersData()");
    	return customerService.getCustomersData(treq);
    }
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomers() {
    	return customerService.findAll();
    }
	
}