package com.leimingtech.service.module.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.GoodsClick;
import com.leimingtech.core.entity.base.StorePurchaseRate;
import com.leimingtech.core.entity.base.StoreSellCount;
import com.leimingtech.core.entity.base.StoreTotalCount;
import com.leimingtech.service.module.report.dao.StoreReportDao;
import com.leimingtech.service.module.report.service.StoreReportService;

@Service
public class StoreReportServiceImpl implements StoreReportService{
	
	@Autowired
	private StoreReportDao storeReportDao;

	/**
	 * 获得商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	@Override
	public List<GoodsClick> getGoodsClick(GoodsClick goodsClick) {
		// TODO Auto-generated method stub
		return storeReportDao.getGoodsClick(goodsClick);
	}

	/**
	 * 本店铺销售总量统计
	 * @param storeSellCount
	 * @return
	 */
	@Override
	public List<StoreSellCount> getStoreSellCount(StoreSellCount storeSellCount) {
		// TODO Auto-generated method stub
		return storeReportDao.getStoreSellCount(storeSellCount);
	}

	/**
	 * 本店铺销售总量统计
	 * @param StoreTotalCount
	 * @return
	 */
	@Override
	public List<StoreTotalCount> getStoreTotalCount(
			StoreTotalCount storeTotalCount) {
		// TODO Auto-generated method stub
		return storeReportDao.getStoreTotalCount(storeTotalCount);
	}

	/**
	 * 本店商品购买率
	 * @param storeId
	 * @return
	 */
	@Override
	public List<StorePurchaseRate> getStorePurchaseRate(Integer storeId) {
		// TODO Auto-generated method stub
		return storeReportDao.getStorePurchaseRate(storeId);
	}

	/**
	 * 获得所有商品的点击量
	 * @param storeId
	 * @return List<GoodsClick>
	 */
	@Override
	public List<GoodsClick> getAllGoodsClick(GoodsClick goodsClick) {
		// TODO Auto-generated method stub
		return storeReportDao.getAllGoodsClick(goodsClick);
	}

}
