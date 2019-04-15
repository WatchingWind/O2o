package com.imooc.o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value = "/registershop",method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr"); 
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try{
			shop = mapper.readValue(shopStr, Shop.class);
		}catch(Exception e){
			modelMap.put("SUCCESS", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg;
		CommonsMultipartResolver commonsMultipartResolver = 
				new CommonsMultipartResolver();
		if(commonsMultipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multipartHttpServletRequest = 
					(MultipartHttpServletRequest)request;
			shopImg  = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}else{
			modelMap.put("SUCCESS", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		
		if(shop != null && shopImg != null){
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);
			File shopImgFile = new File(PathUtil.getImgBasePath()+ImageUtil.getRandomFileName());
			try {
				shopImgFile.createNewFile();
			} catch (IOException e1) {
				modelMap.put("SUCCESS", false);
				modelMap.put("errMsg", e1.getMessage());
				return modelMap;
			}
			try {
				inputStreamToFile(shopImg.getInputStream(), shopImgFile);
			} catch (IOException e) {
				modelMap.put("SUCCESS", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			ShopExecution se = shopService.addShop(shop, shopImgFile);
			if(se.getState() == ShopStateEnum.CHECK.getState()){
				modelMap.put("SUCCESS", true);
				return modelMap;
			}else{
				modelMap.put("SUCCESS", false);
				modelMap.put("errMsg", se.getStateInfo());
				return modelMap;
			}
					
		}else{
			modelMap.put("SUCCESS", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}	
}
	
	private void inputStreamToFile(InputStream inputStream,File file){
		FileOutputStream  os = null;
		try{
			 os = new FileOutputStream(file);
			 int bytesRead = 0;
			 byte[] buffer = new byte[1024];
			 while((bytesRead = inputStream.read(buffer)) != -1){
				 os.write(buffer,0,bytesRead);
			 }
		}catch(Exception e){
			throw new RuntimeException("调用inputStreamToFile异常：" + e.getMessage());
		}finally {
			try{
				if(os != null){
					os.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
			}catch(IOException e){
				throw new RuntimeException("inputStreamToFile关闭IO异常" + e.getMessage());
			}
		}
		
	}
	

}
