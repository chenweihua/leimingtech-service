package com.leimingtech.service.module.goods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.TypeBrand;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * Created by ss on 2014/10/15.
 */
@SqlMapper
public interface TypeBrandMapper {

    List<TypeBrand> findListByType(@Param("typeId") Integer typeId);
    
    /**
	 * 将List<TypeBrand> 插入表
	 * @param list 
	 */
    void batchSave(List<TypeBrand> list);
    
    /**
     * 根据id删除
     * @param typeId
     */
    void delete(@Param("typeId") Integer typeId);
}
