package com.leimingtech.service.module.report.dao;

import java.util.List;

import com.leimingtech.core.entity.base.OrderReport;

public interface OrderReportDao {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<OrderReport> getOrderReport(OrderReport orderReport);
}
