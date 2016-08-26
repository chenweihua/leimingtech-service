package com.leimingtech.service.module.mansongquota.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.mansongquota.dao.ShopPMansongQuotaDao;
import com.leimingtech.core.entity.base.ShopPMansongQuota;
import com.leimingtech.service.module.mansongquota.dao.mapper.ShopPMansongQuotaMapper;

/**
 * 满就送套餐表daoImpl
 *
 * @author admin
 * @version 2015-11-19
 */
@Repository
public class ShopPMansongQuotaDaoImpl implements ShopPMansongQuotaDao {
	
	/** 满就送套餐表mapper接口*/
	@Resource
	private ShopPMansongQuotaMapper shopPMansongQuotaMapper;
	
	/**
	 * 查询分页满就送套餐表数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopPMansongQuota> findShopPMansongQuotaPagerList(Pager pager){
		return shopPMansongQuotaMapper.findShopPMansongQuotaPagerList(pager);
	}

	/**
	 * 通过id获取单条满就送套餐表数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopPMansongQuota findShopPMansongQuotaById(int id){
		return shopPMansongQuotaMapper.findShopPMansongQuotaById(id);
	}

	/**
	 * 通过id删除满就送套餐表数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongQuotaById(int id){
		shopPMansongQuotaMapper.deleteShopPMansongQuotaById(id);
	}

	/**
	 * 修改满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	@Override
	public void updateShopPMansongQuota(ShopPMansongQuota shopPMansongQuota){
		shopPMansongQuotaMapper.updateShopPMansongQuota(shopPMansongQuota);
	}
	/**
	 * 保存满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	@Override
	public void saveShopPMansongQuota(ShopPMansongQuota shopPMansongQuota){
		shopPMansongQuotaMapper.saveShopPMansongQuota(shopPMansongQuota);
	}
	/**
	 * 获取所有满就送套餐表数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansongQuota> findShopPMansongQuotaAllList(){
		return shopPMansongQuotaMapper.findShopPMansongQuotaAllList();
	}

	/**
	 * 通过店铺的id获取它的满就送套餐信息
	 */
	@Override
	public ShopPMansongQuota findShopPMansongQuotaByStoreId(int storeId) {
		return shopPMansongQuotaMapper.findShopPMansongQuotaByStoreId(storeId);
	}
	
}