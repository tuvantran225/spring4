package com.shop.service.datatablespagination;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.CustomerDao;
import com.shop.datatablespagination.data.DataServiceBase;
import com.shop.datatablespagination.data.TableDataException;
import com.shop.datatablespagination.model.PaginationCriteria;
import com.shop.model.Customer;

@Service
public class CustomerDataTableService extends DataServiceBase<Customer> {
	
	private CustomerDao customerDao;
	
    @Autowired
    public CustomerDataTableService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
	
    @Override
	public long countFilteredEntries(PaginationCriteria paginationCriteria) throws TableDataException {
		return 0;
	}

    @Override
	protected List<Customer> getData(PaginationCriteria paginationCriteria) throws TableDataException {
		return customerDao.findAll().stream().filter(c->c.getFirstName().contains(paginationCriteria.getSearch().getValue())).collect(Collectors.toList());
	}

    @Override
	public long countTotalEntries() throws TableDataException {
		return customerDao.findAll().size();
	}
	
}
