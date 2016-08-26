package com.leimingtech.service.module.goods.service;

import java.util.List;

import com.leimingtech.core.entity.base.GoodsSpecIndex;
import com.leimingtech.service.utils.page.Pager;

public interface GoodsSpecIndexService {

	/**
	 * 保存
	 */
	void save(GoodsSpecIndex goodsSpecIndex);
	
	/**
	 * 查询通过goodsId
	 */
	GoodsSpecIndex findByGoodsId(Integer goodsId);
	
	/**
	 * 根据goodsId删除
	 */
	void deleteByGoodsId(Integer goodsId);
	
	/**
	 * 分页查找总数
	 */
	int findPagerListCount(Pager pager);
	
	/**
	 * 分页查询
	 */
	List<GoodsSpecIndex> findPagerList(Pager pager);
}
