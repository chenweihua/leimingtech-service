package com.leimingtech.service.module.store.service;

import java.util.List;

import com.leimingtech.core.entity.base.StoreGrade;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：StoreGradeService   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月12日 上午11:23:29   
 * 修改人：yanghui   
 * 修改时间：2014年11月12日 上午11:23:29   
 * 修改备注：   
 * @version    
 *
 */
public interface StoreGradeService {
	
    public List<StoreGrade> queryStoreGradeList(Pager pager);
    public void save(StoreGrade grade);
    public void delete(Long id);
    public StoreGrade queryById(Long id);
    public void update(StoreGrade grade);

    /**
     * 校验查重
     * @param storeGrade
     * @return
     */
    public int queryCount(StoreGrade storeGrade);
}
