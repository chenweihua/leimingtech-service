package com.leimingtech.service.module.report.service;

import java.util.List;

import com.leimingtech.core.entity.base.BalanceReport;

public interface BalanceReportService {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<BalanceReport> getHaveBalanced(BalanceReport balanceReport);
}
