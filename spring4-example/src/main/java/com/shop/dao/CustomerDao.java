package com.shop.dao;

import com.shop.model.Customer;
import java.util.List;

public interface CustomerDao {

    Customer findById(Integer id);

    List<Customer> findAll();

    void add(Customer user);

    void update(Customer user);

    void delete(Integer id);

}
