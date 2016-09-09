package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.SpecValue;
import com.leimingtech.service.module.goods.dao.SpecValueDao;
import com.leimingtech.service.module.goods.service.SpecValueService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class SpecValueServiceImpl implements SpecValueService{

	@Autowired
	SpecValueDao specValueDao;

	@Override
	public void save(SpecValue specValue) {
		// TODO Auto-generated method stub
		specValueDao.save(specValue);
	}

	@Override
	public void update(SpecValue specValue) {
		// TODO Auto-generated method stub
		specValueDao.update(specValue);
	}

	@Override
	public void deleteBySpId(Integer id) {
		// TODO Auto-generated method stub
		specValueDao.deleteBySpId(id);
	}

	@Override
	public SpecValue findById(Integer id) {
		// TODO Auto-generated method stub
		return specValueDao.findById(id);
	}

	@Override
	public List<SpecValue> findListBySpId(Integer spId) {
		// TODO Auto-generated method stub
		return specValueDao.findListBySpId(spId);
	}

	@Override
	public int findCount(Pager pager) {
		// TODO Auto-generated method stub
		return specValueDao.findCount(pager);
	}

	@Override
	public List<SpecValue> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return specValueDao.findPageList(pager);
	}

	@Override
	public void deleteBySpValueId(Integer id) {
		// TODO Auto-generated method stub
		specValueDao.deleteBySpValueId(id);
	}

	@Override
	public void insert(SpecValue specValue) {
		// TODO Auto-generated method stub
		specValueDao.insert(specValue);
	}
}
