package com.leimingtech.service.module.operation.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.Activity;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * Created by rabook on 2014/11/11.
 */

@SqlMapper
public interface ActivityMapper {

    List<Activity> findList();
}
