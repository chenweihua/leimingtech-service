/**
 * 
 */
package com.leimingtech.service.module.promotion.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Promotion;
import com.leimingtech.service.module.promotion.dao.PromotionDao;
import com.leimingtech.service.module.promotion.dao.mapper.PromotionMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>Title: PromotionDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Repository
public class PromotionDaoImpl implements PromotionDao {
	
	@Resource
	private PromotionMapper promotionMapper;

	@Override
	public void save(Promotion promotion) {
		promotionMapper.save(promotion);
		
	}

	@Override
	public void delete(Integer id) {
		promotionMapper.delete(id);
		
	}

	@Override
	public void update(Promotion promotion) {
		promotionMapper.update(promotion);
		
	}

	@Override
	public List<Promotion> findList(Pager pager) {
		
		return promotionMapper.findList(pager);
	}

	@Override
	public int findCount(Promotion promotion) {
		
		return promotionMapper.findCount(promotion);
	}

	@Override
	public Promotion findById(Integer pcId) {
		return promotionMapper.findById(pcId);
	}

}
