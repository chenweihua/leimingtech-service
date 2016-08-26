package com.leimingtech.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.StoreNavigation;
import com.leimingtech.service.module.store.dao.StoreNavigationDao;
import com.leimingtech.service.module.store.service.StoreNavigationService;

@Service
public class StoreNavigationServiceImpl implements StoreNavigationService {
	
   @Resource
   private StoreNavigationDao storeNavigationDao;
	
   public List<StoreNavigation> findAll(Integer id){
	   return storeNavigationDao.findAll(id);
   }
   
}