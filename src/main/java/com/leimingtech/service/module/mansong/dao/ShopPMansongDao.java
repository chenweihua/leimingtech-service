package com.leimingtech.service.module.mansong.dao;

import java.util.List;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopPMansong;

/**
 * 满就送DAO接口
 *
 * @author linjm
 * @version 2015-11-19
 */
public interface ShopPMansongDao {

	/**
	 * 查询分页满就送数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<ShopPMansong> findShopPMansongPagerList(Pager pager);

	/**
	 * 通过id获取单条满就送数据
	 * 
	 * @param id
	 * @return
	 */
	public ShopPMansong findShopPMansongById(int id);

	/**
	 * 通过id删除满就送数据
	 * 
	 * @param id
	 */
	public void deleteShopPMansongById(int id);

	/**
	 * 修改满就送数据
	 * 
	 * @param shopPMansong
	 */
	public void updateShopPMansong(ShopPMansong shopPMansong);

	/**
	 * 保存满就送数据
	 * 
	 * @param shopPMansong
	 */
	public void saveShopPMansong(ShopPMansong shopPMansong);

	/**
	 * 获取所有满就送数据
	 * 
	 * @return
	 */
	public List<ShopPMansong> findShopPMansongAllList();
	
	/**
	 * 通过满就送套餐id，获取该套餐对应的满就送活动
	 * @param id
	 * @return
	 */
	public List<ShopPMansong> findShopPMansongByQuotaId(int quotaId);
	
	/**
	 * 获取一条店铺当前可以使用的满即送
	 * @param storeId
	 * @return ShopPMansong
	 */
	public ShopPMansong findStoreCurrentMansong(int storeId , long endTime);
	
}