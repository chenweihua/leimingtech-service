package com.leimingtech.service.module.trade.dao.mapper;

import com.leimingtech.core.entity.base.OrderDaddress;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * 订单发货地址
 * @author liukai
 */
@SqlMapper
public interface OrderDaddressMapper {
	/**
	 * 保存订单发货地址
	 * @param daddress
	 */
	void saveOrderDaddress(OrderDaddress OrderDaddress);
	
	/**
	 * 修改订单发货地址
	 * @param daddress
	 */
	void updateOrderDaddress(OrderDaddress orderDaddress);
	
	/**
	 * 根据id查询订单发货地址
	 * @param addressId
	 * @return
	 */
	OrderDaddress findOrderDaddressById(Integer addressId);
}
