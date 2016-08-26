package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.GoodsStatCount;
import com.leimingtech.core.entity.StoreGoodsSalCount;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.vo.OrderGoodsVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * 订单商品(订单项)
 * @author Liuk
 */
@SqlMapper
public interface OrderGoodsMapper {
	
	/**
	 * 插入订单项
	 * @param orderGoods
	 */
    void saveOrderGoods(OrderGoods orderGoods);

    /**
     * 	修改订单项
     * @param orderGoods
     */
    void updateOrderGoods(OrderGoods orderGoods);
    
    /**
     * 根据订单id查询订单项
     * @param orderId	订单id
     * @return
     */
    List<OrderGoods> findByOrderId(Integer orderId);
    
    /**
     * 根据id查询订单项
     * @param recId 订单项id
     * @return
     */
    OrderGoods findById(Integer recId);
    
    /**
     * 根据物品id查询物品订单信息
     * @return
     */
    List<OrderGoodsVo> findOrderGoodsVoByGoodsId(Integer goodsId);
    
    /**
	 * 订单商品项详情,必传订单id,可传用户id和店铺id
	 * @param order
	 * @return
	 */
    OrderGoods findOrderGoodsDetail(OrderGoods orderGoods);
    
    /**
     * 根据订单id删除订单项
     * @param orderId 订单id
     */
    void deleteByOrderId(Integer orderId);
    /**
	 * 时间段，店铺id查找内容
	 * @param map
	 * @return
	 */
	List<StoreGoodsSalCount> storeDoodsSalCount(Map<String,Object> map);
}
