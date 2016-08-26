package com.leimingtech.service.module.report.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.GoodsClick;
import com.leimingtech.core.entity.base.StorePurchaseRate;
import com.leimingtech.core.entity.base.StoreSellCount;
import com.leimingtech.core.entity.base.StoreTotalCount;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface StoreReportMapper {

	/**
	 * 获得商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	List<GoodsClick> getGoodsClick(GoodsClick goodsClick);
	
	/**
	 * 获得所有商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	List<GoodsClick> getAllGoodsClick(GoodsClick goodsClick);
	
	/**
	 * 本店铺销售总量统计
	 * @param storeSellCount
	 * @return
	 */
	List<StoreSellCount> getStoreSellCount(StoreSellCount storeSellCount);
	
	/**
	 * 本店铺销售总量统计
	 * @param StoreTotalCount
	 * @return
	 */
	List<StoreTotalCount> getStoreTotalCount(StoreTotalCount storeTotalCount);
	
	/**
	 * 本店商品购买率
	 * @param storeId
	 * @return
	 */
	List<StorePurchaseRate> getStorePurchaseRate(Integer storeId);
}
