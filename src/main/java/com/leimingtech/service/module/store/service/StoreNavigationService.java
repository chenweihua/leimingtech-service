package com.leimingtech.service.module.store.service;

import java.util.List;

import com.leimingtech.core.entity.base.StoreNavigation;

public interface StoreNavigationService {
	
   public List<StoreNavigation> findAll(Integer id);
   
}