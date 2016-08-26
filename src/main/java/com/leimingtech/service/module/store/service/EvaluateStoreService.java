package com.leimingtech.service.module.store.service;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.base.EvaluateGoods;
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
 * @修改人：sangyuchen   
 * @修改时间：2014年12月18日 上午11:57:21   
 * @修改备注：   
 * @version    
 *
 */
public interface EvaluateStoreService {

	public EvaluateStore findEvaluateStore(Integer id);
	
	/**
     * 分页列表
     * @param pager
     * @param evaluateGoods
     * @return
     */
    List<EvaluateGoods> findPageList(Pager pager,EvaluateGoods evaluateGoods);
	
	/**
     * 总条数
     * @param evaluateGoods
     * @return
     */
    int findCount(EvaluateStore evaluateStore);

    /**
     * 删除
     * @param id
     */
    void delete(int id);
    
    void update(EvaluateGoods evaluateGoods);
    
    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<EvaluateStore> findPageList(Pager pager);
    
    /**
     * 根据店铺id查询店铺评分信息
     * @param storeId
     * @return
     */
    public EvaluateStore findEvaluateStoreByStoreId(Integer storeId);
    /**
    * 获取店铺的平均分
    * @param map
    */
    public Map<String,Integer> findEvaluate(Map<Object,Object> map);
	
}