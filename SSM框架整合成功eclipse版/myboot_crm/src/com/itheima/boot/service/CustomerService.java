package com.itheima.boot.service;

import java.util.List;

import com.itheima.boot.pojo.Customer;
import com.itheima.boot.pojo.QueryVo;
import com.itheima.boot.utils.Page;

public interface CustomerService {
	Page<Customer> getCustomerList(QueryVo queryVo);
	Customer getCustomerById(Long id);
	void updateCustomer(Customer customer);
	void deleteCustomer(Long id);
}

