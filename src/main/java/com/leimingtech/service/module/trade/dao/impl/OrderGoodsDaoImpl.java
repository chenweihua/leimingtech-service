package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.StoreGoodsSalCount;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.vo.OrderGoodsVo;
import com.leimingtech.service.module.trade.dao.OrderGoodsDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderGoodsMapper;

/**
 * Created by rabook on 2014/11/17.
 */

@Repository
public class OrderGoodsDaoImpl implements OrderGoodsDao {

	@Autowired
	private OrderGoodsMapper orderGoodsMapper;

	/**
	 * 插入订单项
	 * @param orderGoods
	 */
	@Override
	public void saveOrderGoods(OrderGoods orderGoods) {
		orderGoodsMapper.saveOrderGoods(orderGoods);
	}

	/**
	 * 修改订单项
	 * @param orderGoods
	 */
	@Override
	public void updateOrderGoods(OrderGoods orderGoods) {
		orderGoodsMapper.updateOrderGoods(orderGoods);
	}

	/**
	 * 根据订单id查询订单项
	 * @param orderId	订单id
	 * @return
	 */
	@Override
	public List<OrderGoods> findByOrderId(Integer orderId) {
		return orderGoodsMapper.findByOrderId(orderId);
	}

	/**
	 * 根据id查询订单项
	 * @param recId	订单项id
	 * @return
	 */
	@Override
	public OrderGoods findById(Integer recId) {
		return orderGoodsMapper.findById(recId);
	}
	
	/**
     * 根据物品id查询物品订单信息
     * @return
     */
	@Override
	public List<OrderGoodsVo> findOrderGoodsVoByGoodsId(Integer goodsId) {
		return orderGoodsMapper.findOrderGoodsVoByGoodsId(goodsId);
	}
	
	/**
     * 根据订单id删除订单项
     * @param orderId 订单id
     */
    public void deleteByOrderId(Integer orderId){
    	orderGoodsMapper.deleteByOrderId(orderId);
    }
    
    /**
	 * 订单商品项详情,必传订单id,可传用户id和店铺id
	 * @param order
	 * @return
	 */
	@Override
	public OrderGoods findOrderGoodsDetail(OrderGoods orderGoods) {
		return orderGoodsMapper.findOrderGoodsDetail(orderGoods);
	}
	/**
	 * 时间段，店铺id查找内容
	 * @param map
	 * @return
	 */
	@Override
	public List<StoreGoodsSalCount> storeDoodsSalCount(Map<String, Object> map) {
		return orderGoodsMapper.storeDoodsSalCount(map);
	}
	
}
