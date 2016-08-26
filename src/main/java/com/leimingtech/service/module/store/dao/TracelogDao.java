package com.leimingtech.service.module.store.dao;


import java.util.List;

import com.leimingtech.core.entity.base.TraceLog;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 */
public interface TracelogDao {
	public int countTraceLog(Pager pager) ;
	public List<TraceLog> queryTraceLogList(Pager pager);
	public void delete(Integer id) ;
	public TraceLog findLogById(Integer id);
    public void updateStateById(Integer id, Integer state);
}
