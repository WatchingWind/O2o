package com.imooc.o2o.util;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;



public class ImageUtil {

	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static String generateThumbnail(File file, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(file);
		System.out.println(basePath);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(file)
					.size(200, 200).watermark(Positions.BOTTOM_RIGHT,
							ImageIO.read(new File(basePath + File.separator + "blog.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return relativeAddr;
	}
	
	/*
	 * ��������ļ�������ǰ������Сʱ��������+ ��λ�����
	 */
	/**
	 * ����Ŀ��·�����漰��Ŀ¼����/home/work/yang/xxx
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
		File dirPath = new File(realFileParentPath);
		if(! dirPath.exists()){
			dirPath.mkdirs();
		}
		
	}

	public static String getRandomFileName(){
		//��ȡ�����λ��
		int randomNumber = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormate.format(new Date());
		
		return randomNumber + nowTimeStr;
	}
	
	public static String getFileExtension(File file){
		String originalFileName = file.getName();
		
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}
	
	public static void main(String[] args) throws IOException {
		
		Thumbnails.of(new File("G:"+File.separator+"A"+File.separator+"1.jpg")).
		size(200,200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new 
				File(basePath+File.separator+"blog.jpg")),0.5f).
				outputQuality(0.8f).toFile("G:/A1/a.jpg");
	}


}
