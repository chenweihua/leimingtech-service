package com.leimingtech.service.module.goods.dao.mapper;

import com.leimingtech.core.entity.base.GoodsAttrIndex;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface GoodsAttrIndexMapper {

	/**
	 * 保存
	 */
	void save(GoodsAttrIndex goodsAttrIndex);
	
	/**
	 * 查询通过goodsId
	 */
	GoodsAttrIndex findByGoodsId(Integer goodsId);
	
	/**
	 * 根据goodsId删除
	 */
	void deleteByGoodsId(Integer goodsId);
}
