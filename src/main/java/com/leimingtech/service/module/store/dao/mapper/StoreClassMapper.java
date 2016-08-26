package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.Classs;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rabook on 2015/3/29.
 */
@SqlMapper
public interface StoreClassMapper {

    List<Classs> queryParentClassList();

    List<Classs> queryChildrenClassList(@Param("parentId") int id);
}
