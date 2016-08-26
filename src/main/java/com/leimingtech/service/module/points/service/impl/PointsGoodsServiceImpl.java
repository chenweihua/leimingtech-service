package com.leimingtech.service.module.points.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.PointsGoods;
import com.leimingtech.core.entity.vo.GoodsSpecVo;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.dao.GoodsSpecDao;
import com.leimingtech.service.module.points.dao.PointsGoodsDao;
import com.leimingtech.service.module.points.service.PointsGoodsService;
import com.leimingtech.service.utils.goods.GoodsUtils;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：积分商品
 * 类名称：PointsGoodsServiceImpl  
 * 创建人：cgl   
 * 创建时间：2015年08月26日10:51:34
 */
@Service
public class PointsGoodsServiceImpl implements PointsGoodsService{

	@Autowired
	PointsGoodsDao pointsGoodsDao;
	
	@Autowired
	GoodsSpecDao goodsSpecDao;
	
	@Autowired
	GoodsDao goodsDao;

	@Override
	public PointsGoods findPointsGoodById(Integer pointsGoodsId) {
		// TODO Auto-generated method stub
		return pointsGoodsDao.findPointsGoodById(pointsGoodsId);
	}

	@Override
	public int findPointsGoodsPagerListCount(Pager pager) {
		// TODO Auto-generated method stub
		return pointsGoodsDao.findPointsGoodPagerListCount(pager);
	}

	@Override
	public List<PointsGoods> findPointsGoodsPagerList(Pager pager) {
		// TODO Auto-generated method stub
		return pointsGoodsDao.findPointsGoodsPagerList(pager);
	}

	@Override
	public void savePointsGoods(PointsGoods pointsGoods, Integer goodsId) {
		
		/**将goods中的某些属性赋给积分商品*/
		Goods goods = goodsDao.findGoodById(goodsId);
		
		GoodsSpec goodsSpec = goodsSpecDao.findByGoodsSpecId(pointsGoods.getGoodsSpecId());
		
		if(goods == null){
			throw new NullPointerException("can not find goods entity!");
		}
		
		if(goodsSpec == null){
			throw new NullPointerException("can not find goodsSpec entity!");
		}
		
		/**将goods和specGoods中的属性,赋到pointsGoods中*/
		pointsGoods = insertFieldToPointsGoods(pointsGoods, goods, goodsSpec);
		
		pointsGoodsDao.savePointsGoods(pointsGoods);
		
	}

	@Override
	public void updatePointsGoods(PointsGoods pointsGoods) {
		pointsGoodsDao.updatePointsGoods(pointsGoods);
	}

	@Override
	public void deletePointsGoods(Integer pointsGoodsId) {
		// TODO Auto-generated method stub
		pointsGoodsDao.deletePointsGoods(pointsGoodsId);
	}

	@Override
	public PointsGoods findOneGoodByCondition(PointsGoods pointsGoods) {
		// TODO Auto-generated method stub
		return pointsGoodsDao.findOnePointsGoodByCondition(pointsGoods);
	}

	@Override
	public int brandCount(Pager pager) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PointsGoods> queryBrandList(Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPointsGoodsImgList(int pointsGoodsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getPointsGoodsSpec(int pointsGoodsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getPointsGoodsAttr(int pointsGoodsId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 将goods中的某些属性赋给积分商品
	 */
	private PointsGoods insertFieldToPointsGoods(PointsGoods pointsGoods, Goods goods, GoodsSpec goodsSpec){

		pointsGoods.setGcId(goods.getGcId());
		pointsGoods.setGcName(goods.getGcName());
		
		if(goods.getBrandId() != null){
			pointsGoods.setBrandId(goods.getBrandId());
		}
		
		if(goods.getBrandName() != null){
			pointsGoods.setBrandName(goods.getBrandName());
		}
		
		pointsGoods.setTypeId(goods.getTypeId());
			
		if(goods.getStoreId() != null){
			pointsGoods.setStoreId(goods.getStoreId());
		}
		
		if(goods.getStoreName() != null){
			pointsGoods.setStoreName(goods.getStoreName());
		}
		
		if(goods.getGoodsImage() != null){
			pointsGoods.setPointsGoodsImage(goods.getGoodsImage());
		}
		
		if(goods.getGoodsImageMore() != null){
			pointsGoods.setPointsGoodsImageMore(goods.getGoodsImageMore());
		}
		
		if(goods.getGoodsStorePrice() != null){
			pointsGoods.setPointsGoodsStorePrice(goods.getGoodsStorePrice());
		}
		
		if(goodsSpec.getSpecGoodsSerial() != null){
			pointsGoods.setPointsGoodsSerial(goodsSpec.getSpecGoodsSerial());
		}
		
		if(goods.getGoodsAttr() != null){
			pointsGoods.setPointsGoodsAttr(goods.getGoodsAttr());
		}
		
		pointsGoods.setPointsGoodsAddTime(System.currentTimeMillis());
		
		pointsGoods.setCityId(goods.getCityId());
		
		pointsGoods.setCityName(goods.getCityName());
		
		pointsGoods.setProvinceId(goods.getProvinceId());
		
		pointsGoods.setProvinceName(goods.getProvinceName());
		
		/**
		 * 从goodsspec中获得spec,经过转化,存储到pointsGoods
		 */
		List<GoodsSpecVo> list = GoodsUtils.getGoodsSpecVoList(goodsSpec, goods);
		
		String jsonStr = "";
		
		if(list != null){
			jsonStr = JsonUtils.toJsonStr(list);
		}
		
		if(!jsonStr.equals("")){
			pointsGoods.setPointsGoodsSpec(jsonStr);
		}
		
		return pointsGoods;
	}
	
}
