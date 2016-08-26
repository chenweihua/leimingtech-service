package com.leimingtech.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.StoreGoodsClass;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.vo.StoreGoodsClassVo;
import com.leimingtech.service.module.store.dao.StoreGoodsClassDao;
import com.leimingtech.service.module.store.dao.mapper.StoreGoodsClassMapper;
import com.leimingtech.service.utils.page.Pager;

@Repository
public class StoreGoodsClassDaoIpml implements StoreGoodsClassDao {
    @Resource
    private StoreGoodsClassMapper storeGoodsClassMapper;

    @Override
    public List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo) {
        return storeGoodsClassMapper.queryClasssList(storeGoodsClassVo);
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        storeGoodsClassMapper.deleteByPrimaryKey(id);

    }

    @Override
    public List<StoreGoodsClass> findParentList(int id) {
        return storeGoodsClassMapper.findParentList(id);
    }

    @Override
    public void insertSelective(StoreGoodsClass storeGoodsClass) {
        storeGoodsClassMapper.insertSelective(storeGoodsClass);

    }

    @Override
    public void updateByPrimaryKeySelective(StoreGoodsClass storeGoodsClass) {
        storeGoodsClassMapper.updateByPrimaryKeySelective(storeGoodsClass);

    }

    @Override
    public StoreGoodsClass selectByPrimaryKey(Integer stcId) {
        return storeGoodsClassMapper.selectByPrimaryKey(stcId);
    }

    @Override
    public List<StoreGoodsClass> findAll(int id) {
        // TODO Auto-generated method stub
        return storeGoodsClassMapper.findAll(id);
    }

    @Override
    public List<StoreGoodsClass> findChild(int id) {
        return storeGoodsClassMapper.findChild(id);
    }

    @Override
    public void updateState(StoreGoodsClass storeGoodsClass) {
        storeGoodsClassMapper.updateState(storeGoodsClass);
    }

	@Override
	public List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass) {
		return storeGoodsClassMapper.findList(storeGoodsClass);
	}

	@Override
	public StoreGoodsClass findbystcName(String stcName) {
		return storeGoodsClassMapper.findbystcName(stcName);
	}
    
	/**
     * 查询父子关联通过显示状态
     * @param id
     */
	@Override
	public List<StoreGoodsClass> findListbystate(StoreGoodsClass storeGoodsClass) {
		return storeGoodsClassMapper.findListbystate(storeGoodsClass);
	}
	 /**
     * 查询条数
     * @param pager
     * @return
     */
	@Override
	public int queryCount(StoreGoodsClass storeGoodsClass) {
		return storeGoodsClassMapper.queryCount(storeGoodsClass);
	}
    
	 /**
     * 查询店铺自定义分类分页数据
     * @param pager
     * @return
     */
	@Override
	public List<StoreGoodsClass> queryList(Pager pager) {
		return storeGoodsClassMapper.queryList(pager);
	}
}
