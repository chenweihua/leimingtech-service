package com.leimingtech.service.module.report.dao;

import java.util.List;

import com.leimingtech.core.entity.base.MemberRegister;

public interface MemberReportDao {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<MemberRegister> getMemberRegister(MemberRegister memberRegister);
}
