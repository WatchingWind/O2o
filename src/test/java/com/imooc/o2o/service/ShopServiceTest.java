package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Transactional
	@Rollback(false)
	@Test
	public void testAddShop(){
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCateGory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCateGory.setShopCategoryId(1L);
		shop.setArea(area);
		shop.setShopCategory(shopCateGory);
		shop.setOwner(owner);
		
		shop.setShopName("∏’◊¢≤·µƒµÍ∆Ã2");
		shop.setShopDesc("≤‚ ‘√Ë ˆ1");
		shop.setShopAddr("≤‚ ‘µÿ÷∑1");
//		shop.setPriority(1);
//		shop.setShopId(3l);
		shop.setPhone("test1");
		shop.setAdvice("…Û∫À÷–1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		File imagShop = new File("G:/A/Thumbnails/1.jpg");
		ShopExecution se = shopService.addShop(shop, imagShop);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}
