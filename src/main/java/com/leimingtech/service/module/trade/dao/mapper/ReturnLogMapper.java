package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.ReturnLog;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * 退货日志
 * @author liukai
 */
@SqlMapper
public interface ReturnLogMapper {
	
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
