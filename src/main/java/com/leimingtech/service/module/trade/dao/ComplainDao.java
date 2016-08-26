package com.leimingtech.service.module.trade.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Complain;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
public interface ComplainDao {

    /**
     * 列表
     * @param pager
     * @return
     */
    List<Complain> findList(Pager pager);

    /**
     * 查询条数
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    Complain findById(int id);
}
