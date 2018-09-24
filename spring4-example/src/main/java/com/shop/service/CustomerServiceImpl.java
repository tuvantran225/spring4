package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.CustomerDao;
import com.shop.datatablespagination.TablePaginator;
import com.shop.datatablespagination.model.PaginationCriteria;
import com.shop.datatablespagination.model.TablePage;
import com.shop.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
    private CustomerDao customerDao;
    private TablePaginator tablePaginator;
    
    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, TablePaginator tablePaginator) {
        this.customerDao = customerDao;
        this.tablePaginator = tablePaginator;
    }

    @Override
    public Customer findById(Integer id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void saveOrUpdate(Customer customer) {

        if (findById(customer.getId()) == null) {
            customerDao.add(customer);
        } else {
            customerDao.update(customer);
        }

    }

    @Override
    public void delete(Integer id) {
        customerDao.delete(id);
    }
    
    @Override
    public TablePage getCustomersData(PaginationCriteria treq) {
		return tablePaginator.getPage(treq);
	}
    
}
