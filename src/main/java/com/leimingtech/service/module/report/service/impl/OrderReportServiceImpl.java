package com.leimingtech.service.module.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.OrderReport;
import com.leimingtech.service.module.report.dao.OrderReportDao;
import com.leimingtech.service.module.report.service.OrderReportService;

@Service
public class OrderReportServiceImpl implements OrderReportService{
	
	@Autowired
	private OrderReportDao orderReportDao;

	@Override
	public List<OrderReport> getOrderReport(OrderReport orderReport) {
		// TODO Auto-generated method stub
		return orderReportDao.getOrderReport(orderReport);
	}

}
