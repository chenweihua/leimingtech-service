package com.leimingtech.service.module.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.BalanceReport;
import com.leimingtech.service.module.report.dao.BalanceReportDao;
import com.leimingtech.service.module.report.service.BalanceReportService;

@Service
public class BalanceReportServiceImpl implements BalanceReportService{
	
	@Autowired
	private BalanceReportDao balanceReportDao;

	@Override
	public List<BalanceReport> getHaveBalanced(BalanceReport balanceReport) {
		// TODO Auto-generated method stub
		return balanceReportDao.getHaveBalanced(balanceReport);
	}

}
