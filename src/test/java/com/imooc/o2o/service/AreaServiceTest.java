package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaServiceImpl;


public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaServiceImpl areaServiceImpl;
	@Test
	public void TestGetAreaList(){
		List<Area> areaList = areaServiceImpl.getAreaList();
		assertEquals("��",areaList.get(0).getAreaName());
	}
}
