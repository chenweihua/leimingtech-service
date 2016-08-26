package com.leimingtech.service.module.mansong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.mansong.dao.ShopPMansongDao;
import com.leimingtech.core.entity.base.ShopPMansong;
import com.leimingtech.service.module.mansong.dao.mapper.ShopPMansongMapper;

/**
 * 满就送daoImpl
 *
 * @author linjm
 * @version 2015-11-19
 */
@Repository
public class ShopPMansongDaoImpl implements ShopPMansongDao {
	
	/** 满就送mapper接口*/
	@Resource
	private ShopPMansongMapper shopPMansongMapper;
	
	/**
	 * 查询分页满就送数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopPMansong> findShopPMansongPagerList(Pager pager){
		return shopPMansongMapper.findShopPMansongPagerList(pager);
	}

	/**
	 * 通过id获取单条满就送数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopPMansong findShopPMansongById(int id){
		return shopPMansongMapper.findShopPMansongById(id);
	}

	/**
	 * 通过id删除满就送数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongById(int id){
		shopPMansongMapper.deleteShopPMansongById(id);
	}

	/**
	 * 修改满就送数据
	 * 
	 * @param shopPMansong
	 */
	@Override
	public void updateShopPMansong(ShopPMansong shopPMansong){
		shopPMansongMapper.updateShopPMansong(shopPMansong);
	}
	/**
	 * 保存满就送数据
	 * 
	 * @param shopPMansong
	 */
	@Override
	public void saveShopPMansong(ShopPMansong shopPMansong){
		shopPMansongMapper.saveShopPMansong(shopPMansong);
	}
	/**
	 * 获取所有满就送数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansong> findShopPMansongAllList(){
		return shopPMansongMapper.findShopPMansongAllList();
	}

	/**
	 * 通过满就送套餐id，获取其对应的满就送活动列表
	 */
	@Override
	public List<ShopPMansong> findShopPMansongByQuotaId(int quotaId) {
		return shopPMansongMapper.findShopPMansongByQuotaId(quotaId);
	}

	@Override
	public ShopPMansong findStoreCurrentMansong(int storeId, long endTime) {
		return shopPMansongMapper.findStoreCurrentMansong(storeId,endTime);
	}
	
}