package com.leimingtech.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.StoreNavigation;
import com.leimingtech.service.module.store.dao.StoreNavigationDao;
import com.leimingtech.service.module.store.dao.mapper.StoreNavigationMapper;

@Repository
public class StoreNavigationDaoImpl implements StoreNavigationDao {
	
   @Resource
   private StoreNavigationMapper storeNavigationMapper;

   @Override
   public List<StoreNavigation> findAll(Integer id){
	  return storeNavigationMapper.findAll(id);
   }
   
}