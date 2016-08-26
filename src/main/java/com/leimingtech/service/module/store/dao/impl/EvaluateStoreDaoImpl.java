package com.leimingtech.service.module.store.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.EvaluateStore;
import com.leimingtech.service.module.store.dao.EvaluateStoreDao;
import com.leimingtech.service.module.store.dao.mapper.EvaluateStoreMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * @项目名称：leimingtech-seller   
 * @类名称： EvaluateStoreDaoImpl   
 * @类描述：   
 * @创建人：sangyuchen   
 * @创建时间：2014年12月18日 上午11:57:21   
 * @修改备注：   
 * @version    
 *
 */
@Repository
public class EvaluateStoreDaoImpl implements EvaluateStoreDao {
   
	@Resource
	private EvaluateStoreMapper evaluateStoreMapper;

	@Override
	public EvaluateStore findEvaluateStore(Integer id){
		return evaluateStoreMapper.findEvaluateStore(id);
	}
	
	/**
     * 分页列表
     * @return
     */
    @Override
    public List<EvaluateStore> findlist(Pager pager) {
        return evaluateStoreMapper.findPageList(pager);
    }

    /**
     * 总条数
     *
     * @param evaluateStore
     * @return
     */
    @Override
    public int findCount(EvaluateStore evaluateStore) {
        return evaluateStoreMapper.findCount(evaluateStore);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        evaluateStoreMapper.delete(id);
    }

	@Override
	public void save(EvaluateStore evaluateStore) {
		evaluateStoreMapper.save(evaluateStore);
		
	}
	/**
     * 获取店铺的平均分
     * @param map
     */
	@Override
	public List<EvaluateStore> findEvaluate(Map<Object, Object> map) {
		return evaluateStoreMapper.findEvaluate(map);
	}
}