package com.leimingtech.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.StoreGrade;
import com.leimingtech.service.module.store.dao.StoreGradeDao;
import com.leimingtech.service.module.store.service.StoreGradeService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：StoreGradeServiceImpl   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月12日 上午11:40:31   
 * 修改人：yanghui   
 * 修改时间：2014年11月12日 上午11:40:31   
 * 修改备注：   
 * @version    
 *
 */
@Service("StoreGradeService")
public class StoreGradeServiceImpl implements StoreGradeService {

	@Resource
    private StoreGradeDao storeGradeDao;


	@Override
	public List<StoreGrade> queryStoreGradeList(Pager pager) {
		return storeGradeDao.queryStoreGradeList(pager);
	}

	public void save(StoreGrade grade){
		storeGradeDao.save(grade);
	}
	
	public void delete(Long id){
		storeGradeDao.delete(id);
	}
	public StoreGrade queryById(Long id){
		return storeGradeDao.queryById(id);
	}


	@Override
	public void update(StoreGrade grade) {
		grade.setUpdateTime(System.currentTimeMillis());
		storeGradeDao.update(grade);
		
	}

    /**
     * 校验查重
     *
     * @param storeGrade
     * @return
     */
    @Override
    public int queryCount(StoreGrade storeGrade) {
        return storeGradeDao.queryCount(storeGrade);
    }

}

