package com.leimingtech.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.base.ReturnLog;
import com.leimingtech.core.entity.vo.ReturnDetailVo;
import com.leimingtech.service.module.trade.common.OrderState;
import com.leimingtech.service.module.trade.common.RefundReturnState;
import com.leimingtech.service.module.trade.dao.RefundReturnDao;
import com.leimingtech.service.module.trade.dao.ReturnLogDao;
import com.leimingtech.service.module.trade.service.OrderGoodsService;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.RefundReturnService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款退货ServiceImpl
 *
 * @author liukai
 * @version 2015-11-02
 */
@Service
public class RefundReturnServiceImpl implements RefundReturnService {

	/** 退款退货DAO接口*/
	@Resource
	private RefundReturnDao refundReturnDao;
	
	@Resource
	private ReturnLogDao returnLogDao;
	
	@Resource
	private OrderGoodsService orderGoodsService;
	
	@Resource
	private OrderService orderService;
	
	
	/**
	 * 查询分页退款退货数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<RefundReturn> findRefundReturnPagerList(Pager pager){
		return refundReturnDao.findRefundReturnPagerList(pager);
	}

	/**
	 * 通过id获取单条退款退货数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public RefundReturn findRefundReturnById(int id){
		return refundReturnDao.findRefundReturnById(id);
	}
	
	/**
	 * 条件查询退款退货总条数
	 * @param refundReturn
	 * @return
	 */
	public int findRefundReturnCount(RefundReturn refundReturn){
		return refundReturnDao.findRefundReturnCount(refundReturn);
	}

	/**
	 * 通过id删除退款退货数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteRefundReturnById(int id){
		refundReturnDao.deleteRefundReturnById(id);
	}

	/**
	 * 获取所有退款退货数据
	 * 
	 * @return
	 */
	@Override
	public List<RefundReturn> findRefundReturnAllList(){
		return refundReturnDao.findRefundReturnAllList();
	}
	
	/**
	 * 查询退款退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 * @param refundId
	 * @param buyerId
	 * @param storeId
	 * @return
	 */
	public ReturnDetailVo findRefundReturnDetail(Integer refundId,Integer buyerId,Integer storeId){
		RefundReturn refundReturn = new RefundReturn();
		refundReturn.setRefundId(refundId);
		if(buyerId!=null){
			refundReturn.setBuyerId(buyerId);
		}
		if(storeId!=null){
			refundReturn.setStoreId(storeId);
		}
		return refundReturnDao.findRefundReturnDetail(refundReturn);
	}
	
	/**
	 * 退货买家发货
	 * @param refundId
	 * @param expressName
	 * @param invoiceNo
	 */
	public void updaterefundReturnDelivery(Integer refundId,String expressName,String invoiceNo){
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if(refundReturn.getGoodsState()!=null && refundReturn.getGoodsState()==RefundReturnState.GOODS_STATE_UNSHIP){
			//Express express = expressService.findById(expressId);
			refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_NOT_RECEIVING); //物流状态
			refundReturn.setExpressId(0); //物流公司编号
			refundReturn.setInvoiceNo(invoiceNo); //物流单号
			refundReturn.setExpressName(expressName); //物流公司名称
			refundReturn.setShipTime(System.currentTimeMillis()); //发货时间
			refundReturnDao.updateRefundReturn(refundReturn);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); //退货表id
			returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE+""); //退货状态信息
			returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE+""); //下一步退货状态信息
			returnLog.setStateInfo("买家已发货,等待卖家收货"); //退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(refundReturn.getBuyerName()); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	
	/**
	 * 卖家退货审核
	 * @param refundId 记录ID
	 * @param sellerState 卖家处理状态
	 * @param sellerMessage 卖家备注
	 * @param operator 操作人
	 */
	public void updateRefundReturnSeller(Integer refundId,Integer sellerState,String sellerMessage,String operator){
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if(refundReturn.getSellerState()==RefundReturnState.SELLER_STATE_PENDING_AUDIT){
			//判断卖家是否同意
			if(sellerState==RefundReturnState.SELLER_STATE_AGREE){ //若同意,修改商品状态为带发货
				refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_UNSHIP); //物流状态
				refundReturn.setSellerState(RefundReturnState.SELLER_STATE_AGREE); //卖家处理状态
			}else{
				refundReturn.setSellerState(RefundReturnState.SELLER_STATE_DISAGREE); //卖家处理状态
			}
			refundReturn.setSellerMessage(sellerMessage); //卖家备注
			refundReturn.setSellerTime(System.currentTimeMillis()); //卖家处理时间
			refundReturnDao.updateRefundReturn(refundReturn);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); //退货表id
			//判断卖家同意或拒绝
			if(sellerState==RefundReturnState.SELLER_STATE_DISAGREE){
				returnLog.setReturnState(RefundReturnState.SELLER_STATE_DISAGREE+""); //退货状态信息
				returnLog.setChangeState(""); //下一步退货状态信息
				returnLog.setStateInfo("卖家已决绝退货服务申请"); //退货状态描述
			}else if(sellerState==RefundReturnState.SELLER_STATE_AGREE){
				returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE+""); //退货状态信息
				returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE+""); //下一步退货状态信息
				returnLog.setStateInfo("退货服务已通过卖家审核,请买家尽快将商品寄回"); //退货状态描述
			}
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(operator); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	/**
	 * 退货卖家收货
	 * @param refundId 记录ID
	 * @param receiveMessage 收货备注
	 * @param operator 操作人
	 */
	public void updateRefundReturnConfirm(Integer refundId,String receiveMessage,String operator){
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if(refundReturn.getGoodsState()!=null && refundReturn.getGoodsState() == RefundReturnState.GOODS_STATE_NOT_RECEIVING){
			//通过订单id查询订单信息
			Order order = orderService.findById(refundReturn.getOrderId());
			OrderGoods orderGoods = orderGoodsService.findById(refundReturn.getOrderGoodsId());
			
			refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_RECEIVED); //物流状态
			refundReturn.setReceiveMessage(receiveMessage); //收货备注
			refundReturn.setReceiveTime(System.currentTimeMillis()); //收货时间
			refundReturn.setDelayTime(System.currentTimeMillis()-refundReturn.getShipTime()); //收货延迟时间
			refundReturn.setRefundState(RefundReturnState.REFUND_STATE_PENDING); //申请状态
			refundReturnDao.updateRefundReturn(refundReturn);
			
			/**
			 * 修改订单项退货数量
			 */
			orderGoods.setGoodsReturnNum(refundReturn.getGoodsNum());
			//修改订单项
			orderGoodsService.updateOrderGoods(orderGoods);
			
			/**
			 * 修改订单退货数量和退货状态
			 */
			//创建一个订单退货的订单项个数,初始为1,代表当前退货的订单项
			int goodsSize = 1;
			
			for(OrderGoods orderGoods1:order.getOrderGoodsList()){
				//判断当前订单项是否已退货
				if(orderGoods1.getGoodsReturnNum()!=null&&orderGoods1.getGoodsReturnNum()!=0){ //已退货
					goodsSize += 1;
				}
			}
			
			Order newOrder = new Order();
			newOrder.setOrderId(refundReturn.getOrderId());
			newOrder.setReturnNum(order.getReturnNum()+refundReturn.getGoodsNum());
			//判断订单中订单项是否都退货
			if(order.getOrderGoodsList().size()>goodsSize){
				newOrder.setReturnState(OrderState.RETURN_STATE_SOM);
			}else if(order.getOrderGoodsList().size()==goodsSize){
				newOrder.setReturnState(OrderState.RETURN_STATE_ALL);
			}
			//修改订单
			orderService.updateOrder(newOrder);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); //退货表id
			returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE+""); //退货状态信息
			returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE+""); //下一步退货状态信息
			returnLog.setStateInfo("卖家已收货,等待系统审核退款"); //退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(operator); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	/**
	 * 退款退货管理员审核退款
	 * @param refundId 记录ID
	 * @param adminMessage 管理员备注
	 */
	public void updateRefundReturnAudiReturn(Integer refundId,String adminMessage){
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if(refundReturn.getRefundState()!=null && refundReturn.getRefundState() == RefundReturnState.REFUND_STATE_PENDING){
			//通过订单id查询订单信息
			Order order = orderService.findById(refundReturn.getOrderId());
			
			refundReturn.setAdminMessage(adminMessage); //管理员备注
			refundReturn.setAdminTime(System.currentTimeMillis()); //管理员处理时间
			refundReturn.setRefundState(RefundReturnState.REFUND_STATE_FINISH); //申请状态
			refundReturnDao.updateRefundReturn(refundReturn);
			

			/**
			 * 修改订单退款金额和退款状态
			 */
			//创建一个订单退款的最大退款总数,因为订单项退款最大金额按比例计算,存在误差,所以退款总数=订单项退款最大金额相加
			double totalReturnPrice = 0.00;
			//新建一个订单当前全部退款金额(包括本次退款的金额)
			double refundedAmount = 0.00;
			
			for(OrderGoods orderGoods1:order.getOrderGoodsList()){
				totalReturnPrice += orderGoods1.getGoodsPayPrice().doubleValue();
			}
			
			Order newOrder = new Order();
			newOrder.setOrderId(refundReturn.getOrderId());
			if(order.getRefundAmount()!=null){
				refundedAmount = order.getRefundAmount().doubleValue() + refundReturn.getRefundAmount().doubleValue(); 
			}else{
				refundedAmount = refundReturn.getRefundAmount().doubleValue(); 
			}
			newOrder.setRefundAmount(BigDecimal.valueOf(refundedAmount));
			//判断订单是否全部退款
			if(totalReturnPrice>refundedAmount){
				newOrder.setRefundState(OrderState.REFUND_STATE_SOM);
			}else{
				newOrder.setRefundState(OrderState.REFUND_STATE_ALL);
			}
			//订单解锁
			newOrder.setLockState(OrderState.ORDER_LOCK_STATE_NO);
			//修改订单
			orderService.updateOrder(newOrder);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); //退货表id
			returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE+""); //退货状态信息
			returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE+""); //下一步退货状态信息
			returnLog.setStateInfo("系统审核退款成功"); //退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator("系统"); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	/**
	 * 根据退款批次号修改管理员审核状态(只限支付宝)
	 * @param batchNo 退款批次号
	 * @param adminMessage 管理员备注
	 */
	public void updateRefundReturnByBatchNo(String batchNo,String adminMessage){
		RefundReturn refundReturn = new RefundReturn();
		refundReturn.setAdminMessage(adminMessage); //管理员备注
		refundReturn.setAdminTime(System.currentTimeMillis()); //管理员处理时间
		refundReturn.setRefundState(RefundReturnState.REFUND_STATE_FINISH); //申请状态
		refundReturn.setBatchNo(batchNo); //退款批次号
		refundReturnDao.updateRefundReturnByBatchNo(refundReturn);
	}
	
	/**
	 * 退款退货保存退款批次号
	 * @param refundId 记录ID
	 * @param batchNo 退款批次号
	 */
	public void saveBatchNo(Integer refundId, String batchNo){
		RefundReturn refundReturn = new RefundReturn();
		refundReturn.setRefundId(refundId); //记录ID
		refundReturn.setBatchNo(batchNo); //退款批次号
		refundReturnDao.updateRefundReturn(refundReturn);
	}
}
