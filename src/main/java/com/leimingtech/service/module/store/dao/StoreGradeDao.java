package com.leimingtech.service.module.store.dao;


import java.util.List;

import com.leimingtech.core.entity.base.StoreGrade;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 */
public interface StoreGradeDao {
	public List<StoreGrade> queryStoreGradeList(Pager pager);
	public void save(StoreGrade grade);
	public void delete(Long id);
	public StoreGrade queryById(Long id);
	public void update(StoreGrade grade) ;

    /**
     * 校验查重
     * @param storeGrade
     * @return
     */
    public int queryCount(StoreGrade storeGrade);
    
}
