package com.leimingtech.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.Pay;
import com.leimingtech.core.entity.base.Payment;
import com.leimingtech.core.entity.base.PredepositRecharge;
import com.leimingtech.service.module.setting.service.PaymentService;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.PayService;
import com.leimingtech.service.module.trade.service.PredepositRechargeService;
import com.leimingtech.service.module.trade.service.PredepositService;

/**
 * 支付相关service
 * @author liukai
 */
@Service
public class PayServiceImpl implements PayService{
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private PredepositRechargeService predepositRechargeService;
	
	@Resource
	private PredepositService predepositService;
	
	@Resource
	private PaymentService paymentService;
	
	/**
	 * 根据单号查询支付金额,支付单号可传订单号,支付单号或充值单号
	 * @param Pay 可传订单号和支付单号
	 * @return 返回Pay只有paySn,payAmount两个字段有值
	 */
	public Pay findPayBySn(String sn){
		Pay pay = new Pay();
		if("P".equals(sn.substring(0, 1))){ //判断编号类型,支付单号
			//新建一个付款状态字段，查询支付单下所有订单支付状态都为已支付后，设置为已支付
			int payState = 1; //默认已支付
			//通过支付单号查询订单集合
			List<Order> orderList = orderService.findByPaySn(sn);
			//支付总额
			double amount = 0.00;
			for(Order order:orderList){
				amount += order.getOrderAmount().doubleValue();
				if(order.getPaymentState()==0){ 
					payState = 0;
				}
			}
			//将支付单号存入paySn
			pay.setPaySn(sn);
			pay.setPayAmount(BigDecimal.valueOf(amount));
			pay.setBeWrite("订单支付");
			pay.setPayState(payState);
		}else if("R".equals(sn.substring(0, 1))){ //判断编号类型,余额充值单号
			//通过余额充值编号查询余额充值单信息
			PredepositRecharge predepositRecharge = predepositRechargeService.findByPdrSn(sn);
			//将余额充值单号存入paySn
			pay.setPaySn(sn);
			pay.setPayAmount(predepositRecharge.getPdrAmount());
			pay.setBeWrite("余额充值");
			pay.setPayState(Integer.valueOf(predepositRecharge.getPdrPaymentState()));
		}else{ //订单编号
			List<Order> orderList = new ArrayList<Order>();
			//通过订单编号查询单个订单
			Order order = orderService.findByOrderSn(sn);
			orderList.add(order);
			//将订单号存入paySn
			pay.setPaySn(sn);
			pay.setPayAmount(order.getOrderAmount());
			pay.setBeWrite("订单支付");
			pay.setPayState(order.getPaymentState());
		}
		
		return pay;
	}
	
	/**
	 * 支付完成后,根据编号修改相应的信息
	 * @param sn 编号
	 * @param tradeSn 支付流水号
	 * @param paymentBranch 支付分支
	 */
	public void updatePayFinish(String sn, String tradeSn, String paymentBranch){
		//判断单号类型
		if("R".equals(sn.substring(0, 1))){ //判断为余额充值相关
			predepositService.updateFinishRecharge(sn); 
		}else{ //判断为订单相关
			orderService.updateOrderStatePayFinish(sn,tradeSn,paymentBranch);
		}
	}
	
	
	/**
	 * 修改相关支付信息
	 * @param sn 编号
	 * @param code 支付标识
	 */
	public void updatePaymentBySn(String sn,String code){
		Payment payment = paymentService.selectByCode(code);
		if(payment!=null){
			if("R".equals(sn.substring(0, 1))){ //判断编号类型,充值单号
				PredepositRecharge predepositRecharge = predepositRechargeService.findByPdrSn(sn);
				predepositRecharge.setPdrPaymentCode(payment.getPaymentCode());  //支付方式编号
				predepositRecharge.setPdrPaymentName(payment.getPaymentName());  //支付方式名称
				//修改预存款充值表
				predepositRechargeService.updatePdr(predepositRecharge);
			}else if("P".equals(sn.substring(0, 1))){ //判断编号类型,支付单号
				List<Order> orderList = orderService.findByPaySn(sn);
				for(Order order : orderList){
					order.setPaymentCode(payment.getPaymentCode()); //支付方式名称代码
					order.setPaymentId(payment.getPaymentId().intValue()); //支付方式id
					order.setPaymentName(payment.getPaymentName()); //支付方式名称
					orderService.updateOrder(order);
				}
			}else{ //订单编号
				Order order = orderService.findByOrderSn(sn);
				order.setPaymentCode(payment.getPaymentCode()); //支付方式名称代码
				order.setPaymentId(payment.getPaymentId().intValue()); //支付方式id
				order.setPaymentName(payment.getPaymentName()); //支付方式名称
				orderService.updateOrder(order);
			}
		}
	}
	
	/**
	 * 充值新增充值信息并set到支付实体
	 * @param amount
	 * @param memberId
	 * @return
	 */
	public Pay addPredepositRechargeToPay(Double amount,Integer memberId){
		Pay pay = new Pay();
		if(amount!=null&&memberId!=null){
			PredepositRecharge predepositRecharge =  predepositService.addRechargePredeposit(memberId, BigDecimal.valueOf(amount));
			pay.setPaySn(predepositRecharge.getPdrSn());
			pay.setPayAmount(predepositRecharge.getPdrAmount());
			pay.setBeWrite("余额充值");
		}
		return pay;
	}
}
