package com.leimingtech.service.module.log.service;

import java.util.List;

import com.leimingtech.core.entity.base.SellerLog;
import com.leimingtech.service.utils.page.Pager;

public interface SellerLogService {

	// 查询总数
	public int countSellerLog(SellerLog sellerLog);

	// 查询日志,并且分页
	public List<SellerLog> selectSellerLogByPager(Pager pager);

	// 增加日志
	public int saveSellerLog(SellerLog sellerLog);

	// 删除日志
	public int deleteSellerLog(int logId);

	// 修改日志
	public int updateSellerLog(SellerLog sellerLog);

	// 查询所有日志
	public List<SellerLog> selectAllSellerLog();

}
