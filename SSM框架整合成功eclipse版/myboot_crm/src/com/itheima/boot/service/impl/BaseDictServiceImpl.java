package com.itheima.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.boot.dao.BaseDictDao;
import com.itheima.boot.pojo.BaseDict;
import com.itheima.boot.service.BaseDictService;

@Service
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictDao baseDictDao;
	@Override
	public List<BaseDict> getDictListByTypeCode(String typeCode) {
		List<BaseDict> list = baseDictDao.getDictListByTypeCode(typeCode);
		
		return list;
	}

}
