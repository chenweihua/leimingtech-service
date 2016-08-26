package com.leimingtech.service.module.trade.dao;

import java.util.List;

import com.leimingtech.core.entity.base.ComplainGoods;

/**
 * Created by rabook on 2014/12/21.
 */
public interface ComplainGoodsDao {

    List<ComplainGoods> findByComplainId(int id);
}
