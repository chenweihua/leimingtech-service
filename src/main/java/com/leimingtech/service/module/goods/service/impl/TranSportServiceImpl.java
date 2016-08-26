package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.Transport;
import com.leimingtech.service.module.goods.dao.TranSportDao;
import com.leimingtech.service.module.goods.service.TranSportService;

@Service
public class TranSportServiceImpl implements TranSportService{

	@Resource
    private TranSportDao tranSportDao;

	@Override
	public List<Transport> queryStoreList(Integer storeId) {
		// TODO Auto-generated method stub
		return tranSportDao.queryStoreList(storeId);
	}

   
}
