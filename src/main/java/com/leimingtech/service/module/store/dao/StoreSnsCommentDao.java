package com.leimingtech.service.module.store.dao;


import java.util.List;

import com.leimingtech.core.entity.base.StoreSnsComment;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 */
public interface StoreSnsCommentDao {
	public int countComment(Pager pager) ;
	public List<StoreSnsComment> queryCommentList(Pager pager);
	public void delete(Integer id) ;
	public StoreSnsComment findLogById(Integer id);
    public void updateStateById(Integer id, Integer state);
}
