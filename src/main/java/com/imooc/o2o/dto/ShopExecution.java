package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

public class ShopExecution {
	
	//���״̬
	private int state;
	
	//��ʶ״̬
	private String stateInfo;
	
	//��������
	private int count;
	
	//������shop
	private Shop shop;
	
	//shop�б�
	private List<Shop> shopList;
	
	public ShopExecution(){
		
	}
	
	//���̲���ʧ�ܵ�ʱ��ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum){
		this.stateInfo = stateEnum.getStateInfo();
		this.state = stateEnum.getState();
	}
	
	//���̲����ɹ���ʱ��ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum,Shop shop){
		this.stateInfo = stateEnum.getStateInfo();
		this.state = stateEnum.getState();
		this.shop = shop;
	}
	
	//���̲����ɹ���ʱ��ʹ�õĹ�����
		public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList){
			this.stateInfo = stateEnum.getStateInfo();
			this.state = stateEnum.getState();
			this.shopList = shopList;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public String getStateInfo() {
			return stateInfo;
		}

		public void setStateInfo(String stateInfo) {
			this.stateInfo = stateInfo;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Shop getShop() {
			return shop;
		}

		public void setShop(Shop shop) {
			this.shop = shop;
		}

		public List<Shop> getShopList() {
			return shopList;
		}

		public void setShopList(List<Shop> shopList) {
			this.shopList = shopList;
		}
}
