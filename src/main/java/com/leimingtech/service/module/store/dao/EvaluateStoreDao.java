package com.leimingtech.service.module.store.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.EvaluateStore;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * @项目名称：leimingtech-seller   
 * @类名称：StoreBindClassDao  
 * @类描述：   
 * @创建人：sangyuchen   
 * @创建时间：2014年12月18日 上午11:57:21   
 * @修改备注：   
 * @version    
 *
 */
@Repository
public interface EvaluateStoreDao {

	public EvaluateStore findEvaluateStore(Integer id);
	
	/**
     * 分页列表
     * @param pager
     * @return
     */
    List<EvaluateStore> findlist(Pager pager);

    /**
     * 总条数
     * @param evaluateStore
     * @return
     */
    int findCount(EvaluateStore evaluateStore);

    /**
     * 删除
     * @param id
     */
    void delete(int id);
    
    /**
     * 保存
     * @param evaluateStore
     */
    public void save(EvaluateStore evaluateStore);
    /**
     * 获取店铺的平均分
     * @param map
     */
    public List<EvaluateStore> findEvaluate(Map<Object,Object> map);
	
}