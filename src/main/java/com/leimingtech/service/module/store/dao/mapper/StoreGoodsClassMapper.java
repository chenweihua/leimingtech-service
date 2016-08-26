package com.leimingtech.service.module.store.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.StoreGoodsClass;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.vo.StoreGoodsClassVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;


@SqlMapper
public interface StoreGoodsClassMapper {
    /**
     * 按顺序查询所有的分类属性
     *
     * @param storeGoodsClassVo
     * @return
     */
    public List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo);

    /**
     * 根据id号修改is_del状态，达到删除的目的
     *
     * @param id
     */
    public void deleteByPrimaryKey(Integer id);

    public List<StoreGoodsClass> findParentList(@Param("id") int id);

    public void insertSelective(StoreGoodsClass storeGoodsClass);

    public void updateByPrimaryKeySelective(StoreGoodsClass storeGoodsClass);

    public StoreGoodsClass selectByPrimaryKey(Integer stcId);

    /**
     * 取出所有的分类
     *
     * @param @return 设定文件
     * @return List<StoreGoodsClass>    返回类型
     * @throws
     * @Title: findAll
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public List<StoreGoodsClass> findAll(@Param("id") int id);

    List<StoreGoodsClass> findChild(@Param("id") int id);

    void updateState(StoreGoodsClass storeGoodsClass);
    /**
     * 查询父子关联
     * @param id
     */
    public List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass);
    
    /**
     * 校验重复的分类名称
     * @param id
     */
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
    
}
