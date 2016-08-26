package com.leimingtech.service.module.mansongapply.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.mansongapply.dao.ShopPMansongApplyDao;
import com.leimingtech.service.module.mansongapply.dao.mapper.ShopPMansongApplyMapper;
import com.leimingtech.core.entity.base.ShopPMansongApply;

/**
 * 满就送套餐申请daoImpl
 *
 * @author admin
 * @version 2015-11-19
 */
@Repository
public class ShopPMansongApplyDaoImpl implements ShopPMansongApplyDao {
	
	/** 满就送套餐申请mapper接口*/
	@Resource
	private ShopPMansongApplyMapper shopPMansongApplyMapper;
	
	/**
	 * 查询分页满就送套餐申请数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopPMansongApply> findShopPMansongApplyPagerList(Pager pager){
		return shopPMansongApplyMapper.findShopPMansongApplyPagerList(pager);
	}

	/**
	 * 通过id获取单条满就送套餐申请数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopPMansongApply findShopPMansongApplyById(int id){
		return shopPMansongApplyMapper.findShopPMansongApplyById(id);
	}

	/**
	 * 通过id删除满就送套餐申请数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongApplyById(int id){
		shopPMansongApplyMapper.deleteShopPMansongApplyById(id);
	}

	/**
	 * 修改满就送套餐申请数据
	 * 
	 * @param shopPMansongApply
	 */
	@Override
	public void updateShopPMansongApply(ShopPMansongApply shopPMansongApply){
		shopPMansongApplyMapper.updateShopPMansongApply(shopPMansongApply);
	}
	/**
	 * 保存满就送套餐申请数据
	 * 
	 * @param shopPMansongApply
	 */
	@Override
	public void saveShopPMansongApply(ShopPMansongApply shopPMansongApply){
		shopPMansongApplyMapper.saveShopPMansongApply(shopPMansongApply);
	}
	/**
	 * 获取所有满就送套餐申请数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansongApply> findShopPMansongApplyAllList(){
		return shopPMansongApplyMapper.findShopPMansongApplyAllList();
	}
	
}