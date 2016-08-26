package com.leimingtech.service.module.report.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.BalanceReport;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface BalanceReportMapper {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<BalanceReport> getHaveBalanced(BalanceReport balanceReport);
}
