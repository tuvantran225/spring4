package com.shop.service;

import java.util.List;

import com.shop.datatablespagination.model.PaginationCriteria;
import com.shop.datatablespagination.model.TablePage;
import com.shop.model.Customer;

public interface CustomerService {

    Customer findById(Integer id);

    List<Customer> findAll();

    void saveOrUpdate(Customer user);

    void delete(Integer id);
    
    TablePage getCustomersData(PaginationCriteria treq);

}