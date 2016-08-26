package com.leimingtech.service.module.mansong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.mansong.dao.ShopPMansongDao;
import com.leimingtech.core.entity.base.ShopPMansong;
import com.leimingtech.service.module.mansong.service.ShopPMansongService;

/**
 * 满就送ServiceImpl
 *
 * @author linjm
 * @version 2015-11-19
 */
@Service
public class ShopPMansongServiceImpl implements ShopPMansongService {

	/** 满就送DAO接口*/
	@Resource
	private ShopPMansongDao shopPMansongDao;
	
	/**
	 * 查询分页满就送数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopPMansong> findShopPMansongPagerList(Pager pager){
		return shopPMansongDao.findShopPMansongPagerList(pager);
	}

	/**
	 * 通过id获取单条满就送数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopPMansong findShopPMansongById(int id){
		return shopPMansongDao.findShopPMansongById(id);
	}

	/**
	 * 通过id删除满就送数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongById(int id){
		shopPMansongDao.deleteShopPMansongById(id);
	}

	/**
	 * 修改满就送数据
	 * 
	 * @param shopPMansong
	 */
	@Override
	public void updateShopPMansong(ShopPMansong shopPMansong){
		shopPMansongDao.updateShopPMansong(shopPMansong);
	}
	/**
	 * 保存满就送数据
	 * 
	 * @param shopPMansong
	 */
	@Override
	public void saveShopPMansong(ShopPMansong shopPMansong){
		shopPMansongDao.saveShopPMansong(shopPMansong);
	}
	/**
	 * 获取所有满就送数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansong> findShopPMansongAllList(){
		return shopPMansongDao.findShopPMansongAllList();
	}

	/**
	 * 通过满就送套餐id，获取其对应的满就送活动列表
	 */
	@Override
	public List<ShopPMansong> findShopPMansongByQuotaId(int quotaId) {
		return shopPMansongDao.findShopPMansongByQuotaId(quotaId);
	}

	@Override
	public ShopPMansong findStoreCurrentMansong(int storeId, long endTime) {
		return shopPMansongDao.findStoreCurrentMansong(storeId ,endTime);
	}
	
}