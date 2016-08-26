package com.leimingtech.service.module.report.service;

import java.util.List;

import com.leimingtech.core.entity.base.MemberRegister;

public interface MemberReportService {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<MemberRegister> getMemberRegister(MemberRegister memberRegister);
}
