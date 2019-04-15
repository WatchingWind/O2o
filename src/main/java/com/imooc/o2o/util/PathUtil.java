package com.imooc.o2o.util;

public class PathUtil {
	
	//��ȡ������ϵͳ�ĵ��ļ��ָ��������ͨ���ȶ���ʹ��java�ı�׼�ļ��ָ��seperator�滻��ϵͳ�ķָ���
	private static String seperator = System.getProperty("file.separator");
	
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";

		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/o2o/img";
		} else {
			basePath = "/home/yang/image";
		}
		
		basePath = basePath.replace("/", seperator);
		
		return basePath;
	}
	
	public static String getShopImagePath(Long shopId){
		String imagePath = "/upload/item/shop/" + shopId + "/";

		return imagePath.replace("/", seperator);
	}
}
