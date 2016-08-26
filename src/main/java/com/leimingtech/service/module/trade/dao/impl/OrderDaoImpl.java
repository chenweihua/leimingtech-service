package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.OrderCount;
import com.leimingtech.core.entity.OrderStaticExcel;
import com.leimingtech.core.entity.vo.OrdermVo;
import com.leimingtech.service.module.trade.dao.OrderDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 订单
 * @author liukai
 */
@Repository
public class OrderDaoImpl implements OrderDao{

    @Autowired
    private OrderMapper orderMapper;
    
    /**
	 * 插入订单
	 * @param order
	 */
    @Override
	public void saveOrder(Order order) {
    	orderMapper.saveOrder(order);
	}
    
    /**
	 * 删除订单
	 * @param orderId 订单id
	 */
	public void deleteOrder(Integer orderId){
		orderMapper.deleteOrder(orderId);
	}
	
	/**
	 * 修改订单
	 * @param order
	 */
	@Override
	public void updateOrder(Order order) {
		orderMapper.updateOrder(order);
	}
    
    /**
	 * 修改订单状态
	 * @param order 订单实体,需orderaId,orderState字段,如果需要修改付款状态,需给paymentState字段赋值,
	 * 同时可以传入finnshedTime和shippingTime来更改订单完成和配送时间
	 */
    @Override
	public void updateOrderState(Order order) {
    	orderMapper.updateOrderState(order);
	}
    
    /**
	 * 分页查询订单总条数
	 * @param order
	 * 		可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	@Override
	public int findOrderCount(Order order) {
		return orderMapper.findOrderCount(order);
	}
	
	/**
	 * 分页查询订单
	 * @param pager
	 * 		可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	@Override
	public List<Order> findOrderList(Pager pager) {
		return orderMapper.findOrderList(pager);
	}
	
	/**
	 * 根据id查询订单,有订单项,订单日志
	 * @param orderId
	 * @return
	 */
	@Override
	public Order findById(Integer orderId) {
		return orderMapper.findById(orderId);
	}
	
	/**
	 * 订单详情,必传订单id,可传用户id和店铺id
	 * @param order
	 * @return
	 */
	public Order findOrderDetail(Order order){
		return orderMapper.findOrderDetail(order);
	}
	
	/**
	 * 根据订单编号查询订单信息
	 * @param orderSn
	 * @return
	 */
	@Override
	public Order findByOrderSn(String orderSn) {
		return orderMapper.findByOrderSn(orderSn);
	}
	
	/**
	 * 根据订单支付编号查询订单
	 * @param paySn
	 * @return
	 */
	@Override
	public List<Order> findByPaySn(String paySn) {
		return orderMapper.findByPaySn(paySn);
	}
	
	/**
	 * 根据订单支付id查询订单
	 * @param payId
	 * @return
	 */
	@Override
	public List<Order> findByPayId(Integer payId) {
		return orderMapper.findByPayId(payId);
	}

	/**
	 * 根据订单状态查询订单数量
	 * @param order 可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	public int findOrderCountByOrder(Order order) {
		return orderMapper.findOrderCountByOrder(order);
	}
    
	
	/**
	 * 根据不同条件查询订单状态
	 * @param order 可加查询条件:店铺id,订单状态,开始结束时间(starttime,endtime)，时间代号
	 * @return
	 */
	@Override
	public List<OrderCount> countorderbuy(Map<String, Object> map) {
		return orderMapper.countorderbuy(map);
	}
    

	@Override
	/**
	 * 根据不同条件查询订单状态
	 * @param map 可加查询条件:店铺id,订单状态
	 * @return
	 */
	public List<OrderStaticExcel> findorderexcel(Map<String, Object> map) {
		return orderMapper.findorderexcel(map);
	}
	/**
	 * 根据不同条件查询订单状态
	 * @param pager 可加查询条件:店铺id,订单状态
	 * @return
	 */
	@Override
	public List<OrdermVo> findOrderinfo(Pager pager) {
		return orderMapper.findOrderinfo(pager);
	}
	
	
}
