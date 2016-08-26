package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.ReturnLog;
import com.leimingtech.service.module.trade.dao.ReturnLogDao;
import com.leimingtech.service.module.trade.dao.mapper.ReturnLogMapper;

/**
 * 退货日志
 * @author liukai
 */
@Repository
public class ReturnLogDaoImpl implements ReturnLogDao{
	
	@Resource
	private ReturnLogMapper returnLogMapper;
	
	/**
	 * 保存退货日志
	 * @param returnLog
	 */
	@Override
	public void saveReturnLog(ReturnLog returnLog) {
		returnLogMapper.saveReturnLog(returnLog);
	}
	
	/**
	 * 修改退货日志
	 * @param returnLog
	 */
	@Override
	public void updateReturnLog(ReturnLog returnLog) {
		returnLogMapper.updateReturnLog(returnLog);
	}
	
	/**
	 * 根据id删除退货日志
	 * @param id
	 */
	@Override
	public void deleteReturnLogById(Integer id) {
		returnLogMapper.deleteReturnLogById(id);
	}
	
	/**
	 * 根据退货表id查询退货日志表信息
	 * @param returnId
	 * @return
	 */
	@Override
	public List<ReturnLog> findListByReturnId(Integer returnId) {
		return returnLogMapper.findListByReturnId(returnId);
	}
}
