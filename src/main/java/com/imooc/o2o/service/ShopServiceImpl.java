package com.imooc.o2o.service;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exception.ShopOperationException;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	
	public ShopExecution addShop(Shop shop, File shopImg) {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}

		try {
			// 给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());

			// 添加店铺信息。
			int effectNumber = shopDao.insertShop(shop);
			if (effectNumber <= 0) {
				throw new ShopOperationException("店铺创建失败！");
			} else {
				// 添加店铺图片
				if (shopImg != null) {
					try {
						addShopImg(shop, shopImg);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImgError: " + e.getMessage());
					}
				}

				// 更新店铺的图片地址
				effectNumber = shopDao.updateShop(shop);
				if (effectNumber < 0) {
					throw new ShopOperationException("更新图片地址失败");
				}
			}

		} catch (Exception e) {
			throw new ShopOperationException("add Shop error" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, File shopImg) {
		//获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}

}
