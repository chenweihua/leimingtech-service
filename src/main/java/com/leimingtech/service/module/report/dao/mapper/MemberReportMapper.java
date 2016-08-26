package com.leimingtech.service.module.report.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.MemberRegister;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface MemberReportMapper {
	
	/**
	 * 订单报表
	 * @param orderReport
	 * @return
	 */
	List<MemberRegister> getMemberRegister(MemberRegister memberRegister);
}
