package com.imooc.o2o.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void insertShop(){
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
		shop.setShopName("∏’◊¢≤·µƒµÍ∆Ã");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setShopImg("test");
		shop.setShopId(1L);
		shop.setPhone("test");
		shop.setAdvice("…Û∫À÷–");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1,effectedNum);
	}
	
	@Test
	public void testupdateShop(){
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
		shop.setShopName("∏’◊¢≤·µƒµÍ∆Ã");
		shop.setShopDesc("≤‚ ‘√Ë ˆ");
		shop.setShopAddr("≤‚ ‘µÿ÷∑");
		shop.setShopImg("test");
		shop.setShopId(1L);
		shop.setPriority(1);
		shop.setPhone("test");
		shop.setAdvice("…Û∫À÷–");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1,effectedNum);
	}
}
