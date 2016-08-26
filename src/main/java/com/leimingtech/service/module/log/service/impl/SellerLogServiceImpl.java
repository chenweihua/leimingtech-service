package com.leimingtech.service.module.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leimingtech.core.entity.base.SellerLog;
import com.leimingtech.service.module.log.dao.SellerLogDao;
import com.leimingtech.service.module.log.service.SellerLogService;
import com.leimingtech.service.utils.page.Pager;

@Component
public class SellerLogServiceImpl implements SellerLogService {

	@Autowired
	private SellerLogDao sellerLogDao;

	// 增加日志
	public int saveSellerLog(SellerLog sellerLog) {
		return sellerLogDao.saveSellerLog(sellerLog);
	}

	// 删除日志
	public int deleteSellerLog(int logId) {
		return sellerLogDao.deleteSellerLog(logId);
	}

	// 修改日志
	public int updateSellerLog(SellerLog sellerLog) {
		return sellerLogDao.updateSellerLog(sellerLog);
	}

	@Override
	public List<SellerLog> selectSellerLogByPager(
			Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SellerLog> selectAllSellerLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countSellerLog(SellerLog sellerLog) {
		// TODO Auto-generated method stub
		return 0;
	}


}
