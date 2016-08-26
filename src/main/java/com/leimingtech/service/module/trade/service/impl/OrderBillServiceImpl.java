package com.leimingtech.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.common.NumberUtils;
import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.OrderBill;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.vo.BillExcelVo;
import com.leimingtech.core.entity.vo.BillVo;
import com.leimingtech.core.entity.vo.OrderBillExcelVo;
import com.leimingtech.service.module.trade.common.BillState;
import com.leimingtech.service.module.trade.common.OrderState;
import com.leimingtech.service.module.trade.common.RefundReturnState;
import com.leimingtech.service.module.trade.dao.OrderBillDao;
import com.leimingtech.service.module.trade.service.OrderBillService;
import com.leimingtech.service.module.trade.service.OrderGoodsService;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.RefundReturnService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算表
 * @author liukai
 */
@Service
public class OrderBillServiceImpl implements OrderBillService{
	
	@Resource
	private OrderBillDao orderBillDao;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderGoodsService orderGoodsService;
	
	@Resource
	private RefundReturnService refundReturnService;	
	
	/**
	 * 生成订单结算信息
	 * @param obStartTime 开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime 结束时间(年月日 如:"2015-1-31")
	 */
	public void addBill(String obStartTime,String obEndTime){
		this.addOrderBill(obStartTime, obEndTime);
		this.addRefundReturnBill(obStartTime, obEndTime);
	}
	
	/**
	 * 生成订单结算信息
	 * @param obStartTime 开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime 结束时间(年月日 如:"2015-1-31")
	 */
	public void addOrderBill(String obStartTime,String obEndTime){
		Order order1 = new Order();
		order1.setOrderState(OrderState.ORDER_STATE_FINISH); //查询所有订单状态已完成的
		order1.setStartTime(DateUtils.strToLong(obStartTime+" 00:00:00"));
		order1.setEndTime(DateUtils.strToLong(obEndTime+" 23:59:59"));
		//获取时间段内的所有订单数据的条数
		int count = orderService.findOrderCount(order1);
		if(count==0){
			order1 = null;
			return;
		}
		//以300条订单为一组,判断一共有多少组
		int i = count/300+1;
		
		Pager pager = new Pager();
		pager.setCondition(order1);
		pager.setPageSize(300);
		List<Order> orderList = null;
		for(int pageNo=1; pageNo <= i; pageNo++){
			pager.setPageNo(pageNo);
			orderList = orderService.findOrderList(pager);
			if(orderList!=null&&orderList.size()!=0){
				for(Order order:orderList){
					//判断订单是否为已完成订单
					if(order.getOrderState()==OrderState.ORDER_STATE_FINISH){
						//订单总金额
						double orderTotals = order.getOrderTotalPrice().doubleValue();
						//店铺促销活动费用(订单优惠总金额)
						double storeCostTotals = order.getDiscount().doubleValue();
						/**
						 * 计算佣金金额:多个订单项佣金金额相加
						 * 订单项佣金金额=订单项实付金额*商品所在分类佣金比例
						 */
						double commisTotals = 0.00;
						for(OrderGoods orderGoods : order.getOrderGoodsList()){
							commisTotals += orderGoods.getGoodsPayPrice().doubleValue()*orderGoods.getCommisRate();
						}
						/**
						 * 计算应结金额:应结金额=订单总金额-店铺促销活动费用-佣金金额
						 */
						double obResultTotals = orderTotals-storeCostTotals-commisTotals;
						
						//新建一个Calendar,将开始日期塞入Calendar
						Calendar c = Calendar.getInstance();
						c.setTime(new Date(DateUtils.strToLong(obStartTime+" 00:00:00")));
						
						OrderBill orderBill = new OrderBill();
						orderBill.setObNo(DateUtils.getDateStr("yyyyMMddHHmmssSSS")+order.getStoreId()); //结算单编号(年月店铺ID)
						orderBill.setObStartTime(DateUtils.strToLong(obStartTime+" 00:00:00")); //开始日期
						orderBill.setObEndTime(DateUtils.strToLong(obEndTime+" 23:59:59")); //结束日期
						orderBill.setObOrderTotals(order.getOrderTotalPrice()); //订单金额
						orderBill.setObShippingTotals(order.getShippingFee()); //运费
						orderBill.setObCommisTotals(NumberUtils.getsetScale(BigDecimal.valueOf(commisTotals), 2)); //佣金金额
						orderBill.setObStoreCostTotals(order.getPromoPrice()); //店铺促销活动费用
						orderBill.setObResultTotals(NumberUtils.getsetScale(BigDecimal.valueOf(obResultTotals), 2)); //应结金额
						orderBill.setOsMonth(c.get(Calendar.MONTH)+1); //结算单年月份(结算周期结束时间所在月份)
						orderBill.setOsYear(c.get(Calendar.YEAR)); //结算单年份(结算周期结束时间所在年份)
						orderBill.setObState(BillState.OB_STATE_DEFAULT); //结算状态
						orderBill.setObStoreId(order.getStoreId()); //店铺ID
						orderBill.setObStoreName(order.getStoreName()); //店铺名
						orderBill.setCreateTime(System.currentTimeMillis()); //生成结算单日期
						
						//保存结算表
						orderBillDao.saveOrderBill(orderBill);
						
						/**
						 * 修改订单结算状态
						 */
						order.setBalanceState(OrderState.ORDER_BALANCE_YES);
						order.setBalanceTime(System.currentTimeMillis());
						orderService.updateOrder(order);
					}
				}
			}
			orderList = null;
		}
		order1 = null;
		pager = null;
	}
	
	/**
	 * 生成退款退货单结算信息
	 * @param obStartTime 开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime 结束时间(年月日 如:"2015-1-31")
	 */
	public void addRefundReturnBill(String obStartTime,String obEndTime){
		RefundReturn refundReturn1 = new RefundReturn();
		refundReturn1.setRefundState(RefundReturnState.REFUND_STATE_FINISH); //退款状态已完成
		refundReturn1.setStartTime(DateUtils.strToLong(obStartTime+" 00:00:00"));
		refundReturn1.setEndTime(DateUtils.strToLong(obEndTime+" 00:00:00"));
		//获取时间段内的所有订单数据的条数
		int count = refundReturnService.findRefundReturnCount(refundReturn1);
		if(count==0){
			refundReturn1 = null;
			return;
		}
		//以300条订单为一组,判断一共有多少组
		int i = count/300+1;
		
		Pager pager = new Pager();
		pager.setCondition(refundReturn1);
		pager.setPageSize(300);
		List<RefundReturn> refundReturnList = null;
		for(int pageNo=1; pageNo <= i; pageNo++){
			pager.setPageNo(pageNo);
			refundReturnList = refundReturnService.findRefundReturnPagerList(pager);
			if(refundReturnList!=null&&refundReturnList.size()!=0){
				for(RefundReturn refundReturn:refundReturnList){
					//判断退款退货单是否已完成退款
					if(refundReturn.getRefundState()==RefundReturnState.REFUND_STATE_FINISH){
						OrderGoods orderGoods = orderGoodsService.findById(refundReturn.getOrderGoodsId());
						/**
						 * 计算佣金金额 : 这里用作退还佣金
						 * 退货单佣金金额=订单项实付金额*商品所在分类佣金比例
						 */
						double commisTotals = 0.00;
						commisTotals = orderGoods.getGoodsPayPrice().doubleValue()*orderGoods.getCommisRate();
						//退单金额
						double obOrderReturnTotals = refundReturn.getRefundAmount().doubleValue();
						/**
						 * 计算应结金额:应结金额=退单金额-退还佣金
						 */
						double obResultTotals = -(obOrderReturnTotals - commisTotals);
						
						//新建一个Calendar,将开始日期塞入Calendar
						Calendar c = Calendar.getInstance();
						c.setTime(new Date(DateUtils.strToLong(obStartTime+" 00:00:00")));
						
						OrderBill orderBill = new OrderBill();
						orderBill.setObNo(DateUtils.getDateStr("yyyyMMddHHmmssSSS")+refundReturn.getStoreId()); //结算单编号(年月店铺ID)
						orderBill.setObStartTime(DateUtils.strToLong(obStartTime+" 00:00:00")); //开始日期
						orderBill.setObEndTime(DateUtils.strToLong(obEndTime+" 00:00:00")); //结束日期
						orderBill.setObOrderReturnTotals(BigDecimal.valueOf(obOrderReturnTotals)); //退单金额
						orderBill.setObCommisReturnTotals(NumberUtils.getsetScale(BigDecimal.valueOf(commisTotals),2)); //退还佣金
						orderBill.setObResultTotals(NumberUtils.getsetScale(BigDecimal.valueOf(obResultTotals), 2)); //应结金额
						orderBill.setOsMonth(c.get(Calendar.MONTH)+1); //结算单年月份(结算周期结束时间所在月份)
						orderBill.setOsYear(c.get(Calendar.YEAR)); //结算单年份(结算周期结束时间所在年份)
						orderBill.setObState(BillState.OB_STATE_DEFAULT); //结算状态
						orderBill.setObStoreId(refundReturn.getStoreId()); //店铺ID
						orderBill.setObStoreName(refundReturn.getStoreName()); //店铺名
						orderBill.setCreateTime(System.currentTimeMillis()); //生成结算单日期
						
						//保存结算表
						orderBillDao.saveOrderBill(orderBill);
					}
				}
			}
			refundReturnList = null;
		}
		refundReturn1 = null;
		pager = null;
	}
	
	/**
	 * 卖家确认结算
	 * @param obId 结算id
	 * @param storeId 店铺id
	 * @return 1:完成;2:结算单不属于当前店铺;3:状态错误
	 */
	public int updateSellerConfirm(Integer obId, Integer storeId){
		OrderBill orderBill = new OrderBill();
		orderBill.setObId(obId);
		orderBill.setObStoreId(storeId);
		OrderBill bill = orderBillDao.findOrderBillDetail(orderBill);
		if(bill != null){
			//判断当前状态是否为默认
			if(bill.getObState()==BillState.OB_STATE_DEFAULT){
				orderBill.setObState(BillState.OB_STATE_SELLER_CONFIRM);
				orderBillDao.updateOrderBill(orderBill);
				return 1;
			}else{
				return 3;
			}
		}else{
			return 2;
		}
	}
	
	/**
	 * 平台确认审核通过
	 * @param obId 结算id
	 * @param obPayContent 支付备注
	 * @return 1:完成;2不存在;3:状态错误
	 */
	public int updateAdminAudit(Integer obId,String obPayContent){
		OrderBill bill = orderBillDao.findOrderBillById(obId);
		if(bill != null){
			//判断商家是否已确认结算
			if(bill.getObState()==BillState.OB_STATE_SELLER_CONFIRM){
				OrderBill orderBill = new OrderBill();
				orderBill.setObId(obId);
				orderBill.setObPayTime(System.currentTimeMillis()); //付款日期
				orderBill.setObPayContent(obPayContent); //支付备注 
				orderBill.setObState(BillState.OB_STATE_ADMIN_AUDITED);
				orderBillDao.updateOrderBill(orderBill);
				return 1;
			}else{
				return 3;
			}
		}else{
			return 2;
		}
	}
	
	/**
	 * 卖家确认收款
	 * @param obId 结算id
	 * @param storeId 店铺id
	 * @return 1:完成;2:结算单不属于当前店铺;3:状态错误
	 */
	public int updateSellerConfirmReceipt(Integer obId, Integer storeId){
		OrderBill orderBill = new OrderBill();
		orderBill.setObId(obId);
		orderBill.setObStoreId(storeId);
		OrderBill bill = orderBillDao.findOrderBillDetail(orderBill);
		if(bill != null){
			//判断当前状态是否为默认
			if(bill.getObState()==BillState.OB_STATE_ADMIN_AUDITED){
				orderBill.setObState(BillState.OB_STATE_FINISH);
				orderBillDao.updateOrderBill(orderBill);
				return 1;
			}else{
				return 3;
			}
		}else{
			return 2;
		}
	}
	
	/**
	 * 查询分页结算表数据
	 * @param pager
	 * @return
	 */
	public List<OrderBill> findOrderBillPagerList(Pager pager){
		return orderBillDao.findOrderBillPagerList(pager);
	}
	
	/**
	 * 根据id查询结算表
	 * @param id
	 * @return
	 */
	public OrderBill findOrderBillById(Integer id){
		return orderBillDao.findOrderBillById(id);
	}
	
	/**
	 * 条件查询结算详情
	 * @param orderBill
	 * @return
	 */
	public OrderBill findOrderBillDetail(OrderBill orderBill){
		return orderBillDao.findOrderBillDetail(orderBill);
	}
	
	/**
	 * 条件查询结算信息(无分页)
	 * @param orderBill 
	 * @return
	 */
	public List<OrderBill> findOrderBillList(OrderBill orderBill){
		return orderBillDao.findOrderBillList(orderBill);
	}
	
	/**
	 * 通过结算id和店铺id查询结算详情
	 * @param obId 结算id
	 * @param storeId 店铺id
	 * @return
	 */
	public OrderBill findOrderBillByStore(Integer obId,Integer storeId){
		OrderBill orderBill = new OrderBill();
		orderBill.setObId(obId);
		orderBill.setObStoreId(storeId);
		return orderBillDao.findOrderBillDetail(orderBill);
	}
	
	/**
	 * 条件查询结算excel信息
	 * @param orderBill
	 * @return
	 */
	public List<OrderBillExcelVo> findExcelVoList(OrderBill orderBill){
		return orderBillDao.findExcelVoList(orderBill);
	}
	
	/**
	 * 分页查询结算管理总账单
	 * @param pager 
	 * @return
	 */
	public List<BillVo> findBillVoPagerList(Pager pager){
		return orderBillDao.findBillVoPagerList(pager);
	}
	
	/**
	 * 查询结算管理总账单条数
	 * @param billVo
	 * @return
	 */
	public int findBillVoCount(BillVo billVo){
		return orderBillDao.findBillVoCount(billVo);
	}
	
	/**
	 * 查询店铺下的总帐单
	 * @param billVo 必传店铺id
	 * @return
	 */
	public BillVo findBillVoDetail(BillVo billVo){
		return orderBillDao.findBillVoDetail(billVo);
	}
	
	/**
	 * 根据店铺id查询店铺下本期应结的总账信息
	 * @param storeId
	 * @return
	 */
	public BillVo findBillVoByStoreId(Integer storeId){
		BillVo billVo = new BillVo();
		billVo.setStoreId(storeId);
		billVo.setObState(BillState.OB_STATE_ADMIN_AUDITED);
		return orderBillDao.findBillVoDetail(billVo);
	}
	
	/**
	 * 条件查询结算管理总账单excel信息
	 * @param billVo
	 * @return
	 */
	public List<BillExcelVo> findBillExcelVoList(BillVo billVo){
		return orderBillDao.findBillExcelVoList(billVo);
	}
}
