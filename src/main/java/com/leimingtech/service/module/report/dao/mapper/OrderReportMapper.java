package com.leimingtech.service.module.report.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.OrderReport;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface OrderReportMapper {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<OrderReport> getOrderReport(OrderReport orderReport);
}
