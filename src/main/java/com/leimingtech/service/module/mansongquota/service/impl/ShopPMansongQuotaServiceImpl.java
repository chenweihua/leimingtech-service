package com.leimingtech.service.module.mansongquota.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.mansongquota.dao.ShopPMansongQuotaDao;
import com.leimingtech.core.entity.base.ShopPMansongQuota;
import com.leimingtech.service.module.mansongquota.service.ShopPMansongQuotaService;

/**
 * 满就送套餐表ServiceImpl
 *
 * @author admin
 * @version 2015-11-19
 */
@Service
public class ShopPMansongQuotaServiceImpl implements ShopPMansongQuotaService {

	/** 满就送套餐表DAO接口*/
	@Resource
	private ShopPMansongQuotaDao shopPMansongQuotaDao;
	
	/**
	 * 查询分页满就送套餐表数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopPMansongQuota> findShopPMansongQuotaPagerList(Pager pager){
		return shopPMansongQuotaDao.findShopPMansongQuotaPagerList(pager);
	}

	/**
	 * 通过id获取单条满就送套餐表数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopPMansongQuota findShopPMansongQuotaById(int id){
		return shopPMansongQuotaDao.findShopPMansongQuotaById(id);
	}

	/**
	 * 通过id删除满就送套餐表数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongQuotaById(int id){
		shopPMansongQuotaDao.deleteShopPMansongQuotaById(id);
	}

	/**
	 * 修改满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	@Override
	public void updateShopPMansongQuota(ShopPMansongQuota shopPMansongQuota){
		shopPMansongQuotaDao.updateShopPMansongQuota(shopPMansongQuota);
	}
	/**
	 * 保存满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	@Override
	public void saveShopPMansongQuota(ShopPMansongQuota shopPMansongQuota){
		shopPMansongQuotaDao.saveShopPMansongQuota(shopPMansongQuota);
	}
	/**
	 * 获取所有满就送套餐表数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansongQuota> findShopPMansongQuotaAllList(){
		return shopPMansongQuotaDao.findShopPMansongQuotaAllList();
	}

	@Override
	public ShopPMansongQuota findShopPMansongQuotaByStoreId(int storeId) {
		return shopPMansongQuotaDao.findShopPMansongQuotaByStoreId(storeId);
	}
	
}