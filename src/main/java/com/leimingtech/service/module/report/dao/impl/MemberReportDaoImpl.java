package com.leimingtech.service.module.report.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leimingtech.core.entity.base.MemberRegister;
import com.leimingtech.service.module.report.dao.MemberReportDao;
import com.leimingtech.service.module.report.dao.mapper.MemberReportMapper;

@Component
public class MemberReportDaoImpl implements MemberReportDao{

	@Autowired
	private MemberReportMapper memberReportMapper;

	@Override
	public List<MemberRegister> getMemberRegister(MemberRegister memberRegister) {
		// TODO Auto-generated method stub
		return memberReportMapper.getMemberRegister(memberRegister);
	}

}
