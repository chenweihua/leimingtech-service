package com.leimingtech.service.module.goods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.SpecValue;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

@SqlMapper
public interface SpecValueMapper {


    /**
     * 保存
     * @param specValue
     */
    void save(SpecValue specValue);

    /**
     * 修改
     * @param specValue
     */
    void update(SpecValue specValue);

    /**
     * 删除
     * @param id
     */
    void deleteBySpId(@Param("spId") Integer id);

    /**
     * 删除
     * @param id
     */
    void insert(SpecValue specValue);
    
    /**
     * 删除某个规格的值
     * @param id
     */
    void deleteBySpValueId(@Param("spId") Integer id);
    
    
    /**
     * 查询单条,通过规格id
     * @param id
     * @return
     */
    SpecValue findById(@Param("spValueId") Integer id);

    public List<SpecValue> findListBySpId(Integer spId);

    /**
     * 查询总条数
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<SpecValue> findPageList(Pager pager);
}
