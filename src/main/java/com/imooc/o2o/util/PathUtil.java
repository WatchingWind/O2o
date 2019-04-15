package com.imooc.o2o.util;

public class PathUtil {
	
	//获取到操作系统的的文件分割符，用于通过比对来使用java的标准文件分割符seperator替换该系统的分隔符
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
