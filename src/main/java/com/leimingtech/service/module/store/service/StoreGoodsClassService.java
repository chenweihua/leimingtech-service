package com.leimingtech.service.module.store.service;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.StoreGoodsClass;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.vo.StoreGoodsClassVo;
import com.leimingtech.service.utils.page.Pager;

public interface StoreGoodsClassService {
    public List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo);

    public void deleteByPrimaryKey(Integer id);

    public List<StoreGoodsClass> findParentList(int id);

    public void save(StoreGoodsClass storeGoodsClass);

    public void update(StoreGoodsClass storeGoodsClass);

    public StoreGoodsClass selectByPrimaryKey(Integer stcId);


    public Map<String, List<StoreGoodsClass>> queryStoreClass(int id);

    List<StoreGoodsClass> findChild(int id);
    
    public List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass);
    
    public StoreGoodsClass findbystcName(String stcName);
    
    /**
     * 查询父子关联通过显示状态
     * @param id
     */
    public List<StoreGoodsClass> findListbystate(StoreGoodsClass storeGoodsClass);
    
    /**
     * 查询条数
     * @param pager
     * @return
     */
    int queryCount(StoreGoodsClass storeGoodsClass);
    
    /**
     * 查询店铺自定义分类分页数据
     * @param pager
     * @return
     */
    List<StoreGoodsClass> queryList(Pager pager);
    
    public void updateByPrimaryKeySelective(StoreGoodsClass storeGoodsClass);
}
