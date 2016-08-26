package com.leimingtech.service.module.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.Transport;

@Repository
public interface TranSportDao {

	public List<Transport> queryStoreList(Integer storeId);
    
    
}
