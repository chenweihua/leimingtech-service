package com.leimingtech.service.module.trade.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.StoreGoodsSalCount;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.vo.OrderGoodsVo;
import com.leimingtech.service.module.trade.dao.OrderGoodsDao;
import com.leimingtech.service.module.trade.service.OrderGoodsService;

/**
 * Created by rabook on 2014/11/17.
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService{

    @Autowired
    private OrderGoodsDao orderGoodsDao;
    
    /**
	 * 插入订单项
	 * @param orderGoods
	 */
	@Override
	public void saveOrderGoods(OrderGoods orderGoods) {
		orderGoodsDao.saveOrderGoods(orderGoods);
	}
	
    /**
     * 	修改订单项
     * @param orderGoods
     */
	@Override
	public void updateOrderGoods(OrderGoods orderGoods) {
		orderGoodsDao.updateOrderGoods(orderGoods);
	}
	
	/**
     * 根据订单id查询订单项
     * @param orderId	订单id
     * @return
     */
	@Override
	public List<OrderGoods> findByOrderId(Integer orderId) {
		return orderGoodsDao.findByOrderId(orderId);
	}
	
	/**
     * 根据id查询订单项
     * @param recId 订单项id
     * @return
     */
	@Override
	public OrderGoods findById(Integer recId) {
		return orderGoodsDao.findById(recId);
	}
	
	/**
     * 根据物品id查询物品订单信息
     * @return
     */
	@Override
	public List<OrderGoodsVo> findOrderGoodsVoByGoodsId(Integer goodsId) {
		return orderGoodsDao.findOrderGoodsVoByGoodsId(goodsId);
	}
	
    
    /**
     * 根据订单id删除订单项
     * @param orderId 订单id
     */
    public void deleteByOrderId(Integer orderId){
    	orderGoodsDao.deleteByOrderId(orderId);
    }
    
    /**
     * 订单商品项详情,必传订单id,可传用户id和店铺id
     * @param recId 订单项id
     * @param buyerId 用户id
     * @param storeId 店铺id
     * @return
     */
    public OrderGoods findOrderGoodsDetail(Integer recId,Integer buyerId,Integer storeId){
    	OrderGoods orderGoods = new OrderGoods();
    	orderGoods.setRecId(recId);
    	if(buyerId!=null){
    		orderGoods.setBuyerId(buyerId);
		}
		if(storeId!=null){
			orderGoods.setStoreId(storeId);
		}
		return orderGoodsDao.findOrderGoodsDetail(orderGoods);
    }
    /**
   	 * 时间段，店铺id查找内容
   	 * @param map
   	 * @return
   	 */
	@Override
	public List<StoreGoodsSalCount> storeDoodsSalCount(Map<String, Object> map) {
		return orderGoodsDao.storeDoodsSalCount(map);
	}
}
