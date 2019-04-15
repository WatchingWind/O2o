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
			// ��������Ϣ����ʼֵ
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());

			// ��ӵ�����Ϣ��
			int effectNumber = shopDao.insertShop(shop);
			if (effectNumber <= 0) {
				throw new ShopOperationException("���̴���ʧ�ܣ�");
			} else {
				// ��ӵ���ͼƬ
				if (shopImg != null) {
					try {
						addShopImg(shop, shopImg);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImgError: " + e.getMessage());
					}
				}

				// ���µ��̵�ͼƬ��ַ
				effectNumber = shopDao.updateShop(shop);
				if (effectNumber < 0) {
					throw new ShopOperationException("����ͼƬ��ַʧ��");
				}
			}

		} catch (Exception e) {
			throw new ShopOperationException("add Shop error" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, File shopImg) {
		//��ȡshopͼƬĿ¼�����ֵ·��
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}

}
