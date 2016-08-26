package com.leimingtech.service.module.mansongrule.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopPMansongRule;

/**
 * 满就送活动规则mapper接口
 * 
 * @author linmj
 * @version 2015-11-19
 */
@SqlMapper
public interface ShopPMansongRuleMapper {

	/**
	 * 查询分页满就送活动规则数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<ShopPMansongRule> findShopPMansongRulePagerList(Pager pager);

	/**
	 * 通过id获取单条满就送活动规则数据
	 * 
	 * @param id
	 * @return
	 */
	public ShopPMansongRule findShopPMansongRuleById(int id);

	/**
	 * 通过id删除满就送活动规则数据
	 * 
	 * @param id
	 */
	public void deleteShopPMansongRuleById(int id);

	/**
	 * 通过满就送的id删除该满送的对应规则
	 * @param id
	 */
	public void deleteShopPMansongRuleByMansongid(int id);
	/**
	 * 修改满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	public void updateShopPMansongRule(ShopPMansongRule shopPMansongRule);

	/**
	 * 保存满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	public void saveShopPMansongRule(ShopPMansongRule shopPMansongRule);

	/**
	 * 获取所有满就送活动规则数据
	 * 
	 * @return
	 */
	public List<ShopPMansongRule> findShopPMansongRuleAllList();

	/**
	 * 获取满送的规则，通过满送的id
	 * @param mansongId
	 * @return
	 */
	public List<ShopPMansongRule> findShopPMansongRuleByMansongid(int mansongId);
	
	/**
	 *  获取当前店铺满即送下的符合条件一条规则
	 * @param ShopPMansongRule
	 * @return mansongRule
	 */
	public ShopPMansongRule findManSongRule(ShopPMansongRule mansongRule);

}