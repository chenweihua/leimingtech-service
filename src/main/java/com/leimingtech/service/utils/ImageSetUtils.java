
package com.leimingtech.service.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import magick.MagickException;

import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.ImageUtil;
import com.leimingtech.core.common.SpringContextUtil;
import com.leimingtech.core.entity.ImageSet;
import com.leimingtech.core.image.ImageJDKUtil;
import com.leimingtech.core.image.ImageUtilSet;
import com.leimingtech.service.module.setting.service.SettingService;


/**
 * admin系统设置---图片设置工具类
 * @author zhaorh
 * @version 2015-10-27
 */
public class ImageSetUtils {
	private static SettingService settingService = SpringContextUtil.getBean(SettingService.class);
	
	/**
	 * 图片设置尺寸
	 */
	public static void setImages(String imageMore){
		String[] imageArr = imageMore.split(",");
		ImageSet imageSet = getImageSetByMap();
		for(int i=0;i<imageArr.length;i++){
			String imgStr = imageArr[i];
			//获取图片扩展名
			String ext = "."+imgStr.substring(imgStr.lastIndexOf(".") + 1);
			// 水印
			new ImageMarketThread(CommonConstants.FILE_BASEPATH + imgStr, CommonConstants.FILE_BASEPATH + "/watermark.png");
			//商品详细页小图尺寸
			int tiny_pic_width = imageSet.getTiny_pic_width();
			int tiny_pic_height = imageSet.getTiny_pic_height();
			//CommonConstants.FILE_BASEPATH
			new ImageSetThread(CommonConstants.FILE_BASEPATH+imgStr,CommonConstants.FILE_BASEPATH+imgStr+"_"+tiny_pic_width+"x"+tiny_pic_height+ext,tiny_pic_width,tiny_pic_height).start(); 
			//缩略图尺寸
			int thumbnail_pic_width = imageSet.getThumbnail_pic_width();
			int thumbnail_pic_height = imageSet.getThumbnail_pic_height();
			new ImageSetThread(CommonConstants.FILE_BASEPATH+imgStr,CommonConstants.FILE_BASEPATH+imgStr+"_"+thumbnail_pic_width+"x"+thumbnail_pic_height+ext,thumbnail_pic_width,thumbnail_pic_height).start(); 
			//商品详细页图片尺寸
			int small_pic_width = imageSet.getSmall_pic_width();
			int small_pic_height = imageSet.getSmall_pic_height();
			new ImageSetThread(CommonConstants.FILE_BASEPATH+imgStr,CommonConstants.FILE_BASEPATH+imgStr+"_"+small_pic_width+"x"+small_pic_height+ext,small_pic_width,small_pic_height).start(); 
			//商品相册图片尺寸
			int big_pic_width = imageSet.getBig_pic_width();
			int big_pic_height = imageSet.getBig_pic_height();
			new ImageSetThread(CommonConstants.FILE_BASEPATH+imgStr,CommonConstants.FILE_BASEPATH+imgStr+"_"+big_pic_width+"x"+big_pic_height+ext,big_pic_width,big_pic_height).start(); 
		}
	}
	
	/**
	 * 获取数据库map集合的value放到ImageSet实体中
	 */
	private static ImageSet getImageSetByMap() {
		Map<String,String> map = settingService.findByNameResultMap("images");
		ImageSet imageSet = new ImageSet();
		imageSet.setBig_pic_height(Integer.valueOf(map.get("big_pic_height")));
		imageSet.setBig_pic_width(Integer.valueOf(map.get("big_pic_width")));
		imageSet.setSmall_pic_height(Integer.valueOf(map.get("small_pic_height")));
		imageSet.setSmall_pic_width(Integer.valueOf(map.get("small_pic_width")));
		imageSet.setThumbnail_pic_height(Integer.valueOf(map.get("thumbnail_pic_height")));
		imageSet.setThumbnail_pic_width(Integer.valueOf(map.get("thumbnail_pic_width")));
		imageSet.setTiny_pic_height(Integer.valueOf(map.get("tiny_pic_height")));
		imageSet.setTiny_pic_width(Integer.valueOf(map.get("tiny_pic_width")));
		return imageSet;
	}

	/**
	 * 生成四种规格图片的线程
	 */
	public static class ImageSetThread extends Thread{
		
		private String fromFileName;
		private String toFileName;
		private int toWidth;
		private int toHeight;

		public ImageSetThread() {
			
		}
		
		public ImageSetThread(String fromFileName, String toFileName,int toWidth, int toHeight) {
			this.fromFileName = fromFileName;
			this.toFileName = toFileName;
			this.toWidth = toWidth;
			this.toHeight = toHeight;
		}
		
		@Override
		public void run() {
			try {
				ImageUtil.scaleRateImageFile(fromFileName, toFileName, toWidth, toHeight);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MagickException e) {
				e.printStackTrace();
			}
		}
		
//		@Override
//		public void run() {
//			try {
//				ImageUtilSet.compressImage(fromFileName, toFileName, toWidth, toHeight);
//			} catch (Exception e) {
//				e.printStackTrace();
//
//			}
//		}
	}
	
	
	/**
	 * 图片水印的线程
	 */
	public static class ImageMarketThread extends Thread{
		
		private String targetImg;
		private String pressImg;

		public ImageMarketThread() {
			
		}
		
		public ImageMarketThread(String targetImg, String pressImg) {
			this.targetImg = targetImg;
			this.pressImg = pressImg;
		}
		
		@Override
		public void run() {
			try {
				File file = new File(targetImg);
				if(file.exists()){
					ImageJDKUtil.pressImage(targetImg, pressImg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
