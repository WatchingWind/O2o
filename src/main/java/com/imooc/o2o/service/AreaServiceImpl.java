package com.imooc.o2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;



//����⵽��Ľӿ��Զ�װ���ô���ʱ����spring��bean���أ�
@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaDao areaDao;
	
	
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}

}