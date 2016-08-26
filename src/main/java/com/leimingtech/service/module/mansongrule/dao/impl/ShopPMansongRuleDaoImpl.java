package com.leimingtech.service.module.mansongrule.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.mansongrule.dao.ShopPMansongRuleDao;
import com.leimingtech.core.entity.base.ShopPMansongRule;
import com.leimingtech.service.module.mansongrule.dao.mapper.ShopPMansongRuleMapper;

/**
 * 满就送活动规则daoImpl
 *
 * @author linmj
 * @version 2015-11-19
 */
@Repository
public class ShopPMansongRuleDaoImpl implements ShopPMansongRuleDao {
	
	/** 满就送活动规则mapper接口*/
	@Resource
	private ShopPMansongRuleMapper shopPMansongRuleMapper;
	
	/**
	 * 查询分页满就送活动规则数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopPMansongRule> findShopPMansongRulePagerList(Pager pager){
		return shopPMansongRuleMapper.findShopPMansongRulePagerList(pager);
	}

	/**
	 * 通过id获取单条满就送活动规则数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopPMansongRule findShopPMansongRuleById(int id){
		return shopPMansongRuleMapper.findShopPMansongRuleById(id);
	}

	/**
	 * 通过id删除满就送活动规则数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongRuleById(int id){
		shopPMansongRuleMapper.deleteShopPMansongRuleById(id);
	}

	/**
	 * 修改满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	@Override
	public void updateShopPMansongRule(ShopPMansongRule shopPMansongRule){
		shopPMansongRuleMapper.updateShopPMansongRule(shopPMansongRule);
	}
	/**
	 * 保存满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	@Override
	public void saveShopPMansongRule(ShopPMansongRule shopPMansongRule){
		shopPMansongRuleMapper.saveShopPMansongRule(shopPMansongRule);
	}
	/**
	 * 获取所有满就送活动规则数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansongRule> findShopPMansongRuleAllList(){
		return shopPMansongRuleMapper.findShopPMansongRuleAllList();
	}

	/**
	 * 获取满送的规则，通过满送的id
	 */
	@Override
	public List<ShopPMansongRule> findShopPMansongRuleByMansongid(int mansongId) {
		return shopPMansongRuleMapper.findShopPMansongRuleByMansongid(mansongId);
	}

	/**
	 * 通过满送id，删除对应的规则
	 */
	@Override
	public void deleteShopPMansongRuleByMansongid(int id) {
		shopPMansongRuleMapper.deleteShopPMansongRuleByMansongid(id);
	}

	@Override
	public ShopPMansongRule findManSongRule(ShopPMansongRule mansongRule) {
		return shopPMansongRuleMapper.findManSongRule(mansongRule);
	}
	
}