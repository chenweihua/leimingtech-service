package com.leimingtech.service.module.store.dao;

import java.util.List;

import com.leimingtech.core.entity.base.StoreNavigation;

public interface StoreNavigationDao {

   public List<StoreNavigation> findAll(Integer id);
   
}