package com.leimingtech.service.module.mansongquota.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopPMansongQuota;

/**
 * 满就送套餐表mapper接口
 * 
 * @author admin
 * @version 2015-11-19
 */
@SqlMapper
public interface ShopPMansongQuotaMapper {

	/**
	 * 查询分页满就送套餐表数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<ShopPMansongQuota> findShopPMansongQuotaPagerList(Pager pager);

	/**
	 * 通过id获取单条满就送套餐表数据
	 * 
	 * @param id
	 * @return
	 */
	public ShopPMansongQuota findShopPMansongQuotaById(int id);

	/**
	 * 通过店铺id获取套餐表的数据
	 * @param storeId
	 * @return
	 */
	public ShopPMansongQuota findShopPMansongQuotaByStoreId(int storeId);
	
	/**
	 * 通过id删除满就送套餐表数据
	 * 
	 * @param id
	 */
	public void deleteShopPMansongQuotaById(int id);

	/**
	 * 修改满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	public void updateShopPMansongQuota(ShopPMansongQuota shopPMansongQuota);

	/**
	 * 保存满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	public void saveShopPMansongQuota(ShopPMansongQuota shopPMansongQuota);

	/**
	 * 获取所有满就送套餐表数据
	 * 
	 * @return
	 */
	public List<ShopPMansongQuota> findShopPMansongQuotaAllList();
	
	

}