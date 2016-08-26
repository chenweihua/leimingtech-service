package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.TypeSpec;
import com.leimingtech.service.module.goods.dao.TypeSpecDao;
import com.leimingtech.service.module.goods.service.TypeSpecService;

@Service
public class TypeSpecServiceImpl implements TypeSpecService{

    @Resource
    private TypeSpecDao typeSpecDao;

	@Override
	public void batchSave(List<TypeSpec> list) {
		typeSpecDao.batchSave(list);
	}

	@Override
	public void delete(Integer typeId) {
		typeSpecDao.delete(typeId);
	}

	@Override
	public List<TypeSpec> findListByType(Integer typeId) {
		return typeSpecDao.findListByType(typeId);
	}

    
}
