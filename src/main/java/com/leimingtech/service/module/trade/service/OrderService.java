
package com.leimingtech.service.module.trade.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.OrderCount;
import com.leimingtech.core.entity.OrderStaticExcel;
import com.leimingtech.core.entity.base.OrderLog;
import com.leimingtech.core.entity.base.OrderPay;
import com.leimingtech.core.entity.vo.CartVo;
import com.leimingtech.core.entity.vo.OrdermVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * 订单
 * @author liukai
 */
public interface OrderService {
	
	/**
	 * 删除订单
	 * @param orderId 订单id
	 */
	void deleteOrder(Integer orderId);
	
	/**
	 * 修改订单
	 * @param order
	 */
	void updateOrder(Order order);
	
	/**
	 * 修改订单状态
	 * @param order 订单实体,需orderaId,orderState字段,如果需要修改付款状态,需给paymentState字段赋值,
	 * 同时可以传入finnshedTime和shippingTime来更改订单完成和配送时间
	 * @param orderLog 订单日志,可传可不传,传可保存订单日志,不传不保存
	 */
	void updateOrderState(Order order,OrderLog orderLog);
	
	/**
	 * 分页查询订单总条数
	 * @param order
	 * 		可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	int findOrderCount(Order order); 
	
	/**
	 * 分页查询订单
	 * @param pager
	 * 		可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	List<Order> findOrderList(Pager pager);
	
	/**
	 * 根据id查询订单,有订单项,订单日志
	 * @param orderId
	 * @return
	 */
	Order findById(Integer orderId);
	
	/**
	 * 订单详情,必传订单id,可传用户id和店铺id,不需要传null
	 * @param orderId 订单id
	 * @param buyerId 用户id
	 * @param storeId 店铺id
	 * @return
	 */
	Order findOrderDetail(Integer orderId,Integer buyerId,Integer storeId);
	
	/**
	 * 根据订单编号查询订单信息
	 * @param orderSn
	 * @return
	 */
	Order findByOrderSn(String orderSn);
	
	
	/**
	 * 提交订单
	 * @param cartIds 多个购物车id
	 * @param memberId 用户id
	 * @param map 存储买家留言信息,键为店铺id,值为店铺留言
	 * @param addressId 收货地址id
	 * @param paytype 支付方式 1:在线支付,2:货到付款
	 * @param freight 运费信息
	 * @param couponId 优惠券id
	 * @param invoiceId 发票id
	 * @param isPd 是否余额支付
	 * @return 返回OrderPay 订单支付表
	 */
	OrderPay addOrderReturnPaySn(String cartIds,Integer memberId,Map<Integer,String> map,Integer addressId,String paytype, String freight, String couponId, String invoiceId, Integer isPd);
	
	/**
	 * 计算商品总价格
	 * @param cartVo
	 * @return
	 */
	BigDecimal findAmount(CartVo cartVo);
	
	/**
	 * 根据订单支付编号查询订单
	 * @param paySn
	 * @return
	 */
	List<Order> findByPaySn(String paySn);
	
	/**
	 * 根据订单支付id查询订单
	 * @param payId
	 * @return
	 */
	List<Order> findByPayId(Integer payId);
	
	/**
	 * 根据支付单号查询订单总价,支付单号可传订单号和支付单号
	 * @param paySn 可传订单号和支付单号
	 * @return 返回PaySn只有paySn,orderList,payAmount三个字段有值
	 */
	OrderPay findOrderPayBySn(String paySn);
	
	/**
	 * 支付完成后,修改订单状态
	 * @param paySn
	 * @param tradeSn 支付流水号
	 * @param paymentBranch 支付分支
	 */
	void updateOrderStatePayFinish(String paySn, String tradeSn, String paymentBranch);
	
	/**
	 * 根据订单状态查询订单数量
	 * @param order 可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	int findOrderCountByOrder(Order order);
	
	/**
	 * 取消订单
	 * @param orderSn 订单编号
	 * @param cancelCause 取消原因
	 * @param opType 操作人(1:买家;2:卖家,3:系统定时)
	 */
	void updateCancelOrder(String orderSn,String cancelCause,Integer opType);
	
	/**
	 * 订单完成
	 * @param orderSn
	 */
	void updateFinishOrder(String orderSn);
	
	/**
	 * 订单确认(货到付款确认)
	 * @param orderSn 
	 */
	void updateConfirmOrder(String orderSn);
	
	/**
	 * 订单发货
	 * @param orderSn 订单编号
	 * @param daddressId 订单发货地址id
	 * @param shippingExpressId 配送公司id
	 * @param shippingCode 物流单号
	 * @param deliverExplain 发货备注
	 */
	void updateDeliveryOrder(String orderSn,Integer daddressId,Integer shippingExpressId,
			String shippingCode,String deliverExplain);
	
	/**
	 * 修改订单发货信息
	 * @param orderSn 订单编号
	 * @param daddressId 订单发货地址id
	 * @param shippingCode 物流单号
	 * @param deliverExplain 发货备注
	 */
	void updateOrderShipments(String orderSn,Integer daddressId,String shippingCode,String deliverExplain);
	
	/**
	 * 订单退款
	 * @param orderId 订单id
	 * @param orderRefund 退款金额
	 * @param buyerMessage 退款原因
	 */
	void addOrderRefund(Integer orderId,BigDecimal orderRefund,String buyerMessage);
	
	/**
	 * 订单退货
	 * @param orderId 订单id
	 * @param goodsNum 订单退货数量
	 * @param buyerMessage 退货原因
	 * @param orderGoodsId 订单项id
	 * @param goodsImageMore 退货图片信息
	 */
	void addOrderReturn(Integer orderId,Integer goodsNum,String buyerMessage,Integer orderGoodsId,String goodsImageMore);
	
	/**
	 * 订单评价完成
	 * @param orderSn
	 */
	void updateEvaluationOrder(String orderSn);
	
	/**
	 * 订单结算完成
	 * @param orderSn
	 */
	void updateBalanceOrder(String orderSn);
	
	/**
	 * 订单批量结算 
	 * @param ids 订单id,中间已","分隔 
	 */
	void updateBalanceOrderByIds(String ids);
	
	/**
	 * 根据支付单号更改订单支付方式id和支付方式名称代码
	 * @param paySn 支付单号
	 * @param paymentId 支付方式id
	 */
	void updateOrderPaymentByPaySn(String paySn,Integer paymentId);
	
	/**
     * 修改订单项评价状态
     * @param recId 订单项id
     * @param order order
     */
    void updateEvaluationStatus(Order order,Integer recId);
    /**
	 * 根据不同条件查询订单状态
	 * @param map 可加查询条件:店铺id,订单状态,开始结束时间(starttime,endtime)，时间代号
	 * @return
	 */
	List<OrderCount> countorderbuy(Map<String,Object> map);
	/**
	 * 根据不同条件查询订单状态
	 * @param map 可加查询条件:店铺id,订单状态
	 * @return
	 */
	List<OrderStaticExcel> findorderexcel(Map<String,Object> map);
	/**
	 * 分页查询订单
	 * @param pager 
	 * 			可加查询条件:店铺id,订单状态
	 * @return
	 */
	List<OrdermVo> findOrderinfo(Pager pager);
}
