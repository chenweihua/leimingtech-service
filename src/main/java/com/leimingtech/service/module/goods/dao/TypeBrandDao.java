package com.leimingtech.service.module.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.TypeBrand;

/**
 * Created by ss on 2014/10/15.
 */
public interface TypeBrandDao {

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
    void delete(int typeId);
}
