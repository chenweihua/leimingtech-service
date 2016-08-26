/**
 * 
 */
package com.leimingtech.service.module.adv.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.AdvPosition;
import com.leimingtech.core.entity.vo.AdvPositionVo;
import com.leimingtech.service.module.adv.dao.AdvPositionDao;
import com.leimingtech.service.module.adv.dao.mapper.AdvPositionMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>Title: AdvPositionDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
@Repository
public class AdvPositionDaoImpl implements AdvPositionDao {
	
	@Resource
	private AdvPositionMapper advPositionMapper;

	@Override
	public void save(AdvPosition advPosition) {
		advPositionMapper.save(advPosition);
		
	}

	@Override
	public void update(AdvPosition advPosition) {
		advPositionMapper.update(advPosition);
		
	}

	@Override
	public void delete(Integer id) {
		advPositionMapper.delete(id);
		
	}

	@Override
	public int findAdvPositionCount(AdvPosition advPosition) {
		
		return advPositionMapper.findAdvPositionCount(advPosition);
	}

	@Override
	public List<AdvPosition> findAllAdvPosition(AdvPosition advPosition) {
		
		return advPositionMapper.findAllAdvPosition(advPosition);
	}

	@Override
	public List<AdvPosition> findAdvPositionPagerList(Pager pager) {
		
		return advPositionMapper.findAdvPositionPagerList(pager);
	}

	@Override
	public AdvPosition findAdvPositionById(Integer id) {
		return advPositionMapper.findAdvPositionById(id);
	}

	@Override
	public AdvPositionVo findAdvPositionVoList(AdvPosition advPosition) {
		
		return advPositionMapper.findAdvPositionVoList(advPosition);
	}

//	@Override
//	public AdvPositionVo findAdvPositionVoList(Integer apId) {
//		
//		return advPositionMapper.findAdvPositionVoList(apId);
//	}

	

}
