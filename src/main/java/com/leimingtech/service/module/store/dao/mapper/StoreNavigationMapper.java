package com.leimingtech.service.module.store.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.StoreNavigation;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface StoreNavigationMapper {

  public List<StoreNavigation> findAll(@Param("id")Integer id);
   
}