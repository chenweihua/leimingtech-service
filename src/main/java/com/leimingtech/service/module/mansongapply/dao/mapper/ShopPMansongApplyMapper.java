package com.leimingtech.service.module.mansongapply.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopPMansongApply;

/**
 * 满就送套餐申请mapper接口
 * 
 * @author admin
 * @version 2015-11-19
 */
@SqlMapper
public interface ShopPMansongApplyMapper {

	/**
	 * 查询分页满就送套餐申请数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<ShopPMansongApply> findShopPMansongApplyPagerList(Pager pager);

	/**
	 * 通过id获取单条满就送套餐申请数据
	 * 
	 * @param id
	 * @return
	 */
	public ShopPMansongApply findShopPMansongApplyById(int id);

	/**
	 * 通过id删除满就送套餐申请数据
	 * 
	 * @param id
	 */
	public void deleteShopPMansongApplyById(int id);

	/**
	 * 修改满就送套餐申请数据
	 * 
	 * @param shopPMansongApply
	 */
	public void updateShopPMansongApply(ShopPMansongApply shopPMansongApply);

	/**
	 * 保存满就送套餐申请数据
	 * 
	 * @param shopPMansongApply
	 */
	public void saveShopPMansongApply(ShopPMansongApply shopPMansongApply);

	/**
	 * 获取所有满就送套餐申请数据
	 * 
	 * @return
	 */
	public List<ShopPMansongApply> findShopPMansongApplyAllList();

}