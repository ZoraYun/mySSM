package com.itheima.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itheima.boot.pojo.BaseDict;
import com.itheima.boot.pojo.Customer;
import com.itheima.boot.pojo.QueryVo;
import com.itheima.boot.service.BaseDictService;
import com.itheima.boot.service.CustomerService;
import com.itheima.boot.utils.Page;



@Controller
public class CustomerController {
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	@Value("${customer.source.code}")
	private String custSourceCode;
	@Value("${customer.industry.code}")
	private String custIndustryCode;
	@Value("${customer.level.code}")
	private String custLevelCode;
	@RequestMapping("/customer/list")
	public String showCustomerList(QueryVo queryVo, Model model) throws Exception {
		//初始化客户来源
		List<BaseDict> sourceList = baseDictService.getDictListByTypeCode(custSourceCode);
		//所属行业
		List<BaseDict> industoryList = baseDictService.getDictListByTypeCode(custIndustryCode);
		//客户级别
		List<BaseDict> levelList = baseDictService.getDictListByTypeCode(custLevelCode);
		//把字典信息传递给页面
		model.addAttribute("fromType", sourceList);
		model.addAttribute("industryType", industoryList);
		model.addAttribute("levelType", levelList);
		//根据查询条件查询客户列表
		Page<Customer> page = customerService.getCustomerList(queryVo);
		//把客户列表传递给页面
		model.addAttribute("page", page);
		//参数回显
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustory());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		return "customer";
	}
	@RequestMapping("/customer/edit")
		@ResponseBody
		public Customer getCustomerById(Long id){
		Customer customer=customerService.getCustomerById(id);
		return customer;
		}
	@RequestMapping(value="/customer/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateCustomer(Customer customer) {
		customerService.updateCustomer(customer);
		return "OK";
	}
	@RequestMapping("/customer/delete")
	@ResponseBody
	public String deleteCustomer(Long id) {
		customerService.deleteCustomer(id);
		return "OK";
	}
}
