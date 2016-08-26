package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.Transport;
import com.leimingtech.service.module.goods.dao.TranSportDao;
import com.leimingtech.service.module.goods.dao.mapper.TranSportMapper;

@Repository
public class TranSportDaoImpl implements TranSportDao {

    @Resource
    private TranSportMapper sportMapper;

	@Override
	public List<Transport> queryStoreList(Integer storeId) {
		// TODO Auto-generated method stub
		return sportMapper.queryStoreList(storeId);
	}
    

    
}
