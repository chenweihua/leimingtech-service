/*package com.leimingtech.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ReturnLog;
import com.leimingtech.core.entity.base.ReturnOrder;
import com.leimingtech.core.entity.vo.ReturnOrderDetailVo;
import com.leimingtech.core.entity.vo.ReturnOrderVo;
import com.leimingtech.service.module.trade.common.RefundReturnState;
import com.leimingtech.service.module.trade.dao.ReturnLogDao;
import com.leimingtech.service.module.trade.dao.ReturnOrderDao;
import com.leimingtech.service.module.trade.service.ReturnOrderService;
import com.leimingtech.service.utils.page.Pager;

*//**
 * 退货表
 * @author liukai
 *//*
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService{
	
	@Resource
	private ReturnOrderDao returnOrderDao;
	
	@Resource
	private ReturnLogDao returnLogDao;
	
	*//**
	 * 保存退货表
	 * @param returnOrder
	 *//*
	public void saveReturnOrder(ReturnOrder returnOrder){
		returnOrderDao.saveReturnOrder(returnOrder);
	}
	
	*//**
	 * 修改退货表
	 * @param returnOrder
	 *//*
	public void updateReturnOrder(ReturnOrder returnOrder){
		returnOrderDao.updateReturnOrder(returnOrder);
	}
	
	*//**
	 * 根据id删除退货表
	 * @param returnId
	 *//*
	public void deleteReturnOrder(Integer returnId){
		returnOrderDao.deleteReturnOrder(returnId);
		
	}
	
	*//**
	 * 分页查询退货总条数
	 * @param returnOrder
	 * @return
	 *//*
	public int findReturnOrderCount(ReturnOrder returnOrder){
		return returnOrderDao.findReturnOrderCount(returnOrder);
	}
	
	*//**
	 * 分页查询退货记录
	 * @param pager
	 * @return
	 *//*
	public List<ReturnOrderVo> findReturnOrderList(Pager pager){
		return returnOrderDao.findReturnOrderList(pager);
	}
	
	*//**
	 * 根据id查询退货记录
	 * @param returnId
	 * @return
	 *//*
	public ReturnOrderVo findById(Integer returnId){
		return returnOrderDao.findById(returnId);
	}
	
	*//**
	 * 查询退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 * @param returnId
	 * @param buyerId
	 * @param storeId
	 * @return
	 *//*
	public ReturnOrderDetailVo findReturnOrderDetail(Integer returnId,Integer buyerId,Integer storeId){
		ReturnOrder returnOrder = new ReturnOrder();
		returnOrder.setReturnId(returnId);
		if(buyerId!=null){
			returnOrder.setBuyerId(buyerId);
		}
		if(storeId!=null){
			returnOrder.setStoreId(storeId);
		}
		return returnOrderDao.findReturnOrderDetail(returnOrder);
	}
	
	*//**
	 * 根据订单id查询退货信息
	 * @param orderId 订单id
	 * @return
	 *//*
	public ReturnOrder findByOrderId(Integer orderId){
		return returnOrderDao.findByOrderId(orderId);
	}
	
	*//**
	 * 卖家退货审核
	 * @param returnId 退货id
	 * @param returnState 退货状态
	 * @param returnMessage 退货备注
	 * @param operator 操作人
	 *//*
	public void updateReturnOrderSeller(Integer returnId, Integer returnState,String returnMessage,String operator){
		ReturnOrder returnOrder = returnOrderDao.findById(returnId);
		if(returnOrder != null){
			returnOrder.setReturnState(returnState);
			returnOrder.setReturnMessage(returnMessage);
			returnOrderDao.updateReturnOrder(returnOrder);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(returnOrder.getReturnId()); //退货表id
			//判断卖家同意或拒绝
			if(returnState==0){
				returnLog.setReturnState(RefundReturnState.RETURN_STATE_REFUSE+""); //退货状态信息
				returnLog.setChangeState(""); //下一步退货状态信息
				returnLog.setStateInfo("卖家已决绝退货服务申请"); //退货状态描述
			}else if(returnState==2){
				returnLog.setReturnState(RefundReturnState.RETURN_STATE_AGREE+""); //退货状态信息
				returnLog.setChangeState(RefundReturnState.RETURN_STATE_RECEIVING+""); //下一步退货状态信息
				returnLog.setStateInfo("退货服务已通过卖家审核,请买家尽快将商品寄回"); //退货状态描述
			}
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(operator); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	*//**
	 * 买家发货
	 * @param returnId 退货id
	 * @param shippingFee 运费价格 
	 * @param shippingExpressName 配送公司名称
	 * @param shippingCode 物流单号
	 *//*
	public void updateReturnOrderDelivery(Integer returnId, Double shippingFee, String shippingExpressName, String shippingCode){
		ReturnOrder returnOrder = returnOrderDao.findById(returnId);
		if(returnOrder != null){
			returnOrder.setReturnState(RefundReturnState.RETURN_STATE_RECEIVING);
			returnOrder.setShippingFee(BigDecimal.valueOf(shippingFee));
			returnOrder.setShippingExpressName(shippingExpressName);
			returnOrder.setShippingCode(shippingCode);
			returnOrderDao.updateReturnOrder(returnOrder);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(returnOrder.getReturnId()); //退货表id
			returnLog.setReturnState(RefundReturnState.RETURN_STATE_RECEIVING+""); //退货状态信息
			returnLog.setChangeState(RefundReturnState.RETURN_STATE_UNREFUND+""); //下一步退货状态信息
			returnLog.setStateInfo("买家已发货,等待卖家确认"); //退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(returnOrder.getBuyerName()); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	*//**
	 * 卖家收货
	 * @param returnId 退货id
	 * @param operator 操作人
	 *//*
	public void updateReturnOrderConfirm(Integer returnId, String operator){
		ReturnOrder returnOrder = returnOrderDao.findById(returnId);
		if(returnOrder != null){
			returnOrder.setReturnState(RefundReturnState.RETURN_STATE_UNREFUND);
			returnOrderDao.updateReturnOrder(returnOrder);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(returnOrder.getReturnId()); //退货表id
			returnLog.setReturnState(RefundReturnState.RETURN_STATE_UNREFUND+""); //退货状态信息
			returnLog.setChangeState(RefundReturnState.RETURN_STATE_REFUNDED+""); //下一步退货状态信息
			returnLog.setStateInfo("卖家已收货,等待卖家退款"); //退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(operator); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	*//**
	 * 卖家退款
	 * @param returnId 退货id
	 * @param operator 操作人
	 *//*
	public void updateReturnOrderRefund(Integer returnId, String operator){
		ReturnOrder returnOrder = returnOrderDao.findById(returnId);
		if(returnOrder != null){
			returnOrder.setReturnState(RefundReturnState.RETURN_STATE_REFUNDED);
			returnOrderDao.updateReturnOrder(returnOrder);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(returnOrder.getReturnId()); //退货表id
			returnLog.setReturnState(RefundReturnState.RETURN_STATE_REFUNDED+""); //退货状态信息
			returnLog.setChangeState(RefundReturnState.RETURN_STATE_FINISH+""); //下一步退货状态信息
			returnLog.setStateInfo("卖家已退款,等待买家确认"); //退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(operator); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
	
	*//**
	 * 退货完成
	 * @param returnId 退货id
	 *//*
	public void updateReturnFinish(Integer returnId){
		ReturnOrder returnOrder = returnOrderDao.findById(returnId);
		if(returnOrder != null){
			returnOrder.setReturnState(RefundReturnState.RETURN_STATE_FINISH);
			returnOrderDao.updateReturnOrder(returnOrder);
			
			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(returnOrder.getReturnId()); //退货表id
			returnLog.setReturnState(RefundReturnState.RETURN_STATE_FINISH+""); //退货状态信息
			returnLog.setChangeState(""); //下一步退货状态信息
			returnLog.setStateInfo("买家确认收款,退货成功!"); //退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); //创建时间
			returnLog.setOperator(returnOrder.getBuyerName()); //操作人
			//保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}
}
*/