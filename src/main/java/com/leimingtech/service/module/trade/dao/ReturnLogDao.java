package com.leimingtech.service.module.trade.dao;

import java.util.List;

import com.leimingtech.core.entity.base.ReturnLog;

/**
 * 退货日志
 * @author liukai
 */
public interface ReturnLogDao {
	
	/**
	 * 保存退货日志
	 * @param returnLog
	 */
	void saveReturnLog(ReturnLog returnLog);
	
	/**
	 * 修改退货日志
	 * @param returnLog
	 */
	void updateReturnLog(ReturnLog returnLog);
	
	/**
	 * 根据id删除退货日志
	 * @param id
	 */
	void deleteReturnLogById(Integer id);
	
	/**
	 * 根据退货表id查询退货日志表信息
	 * @param returnId
	 * @return
	 */
	List<ReturnLog> findListByReturnId(Integer returnId);
}
