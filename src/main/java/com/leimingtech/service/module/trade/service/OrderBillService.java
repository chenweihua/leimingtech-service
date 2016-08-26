package com.leimingtech.service.module.trade.service;

import java.util.List;

import com.leimingtech.core.entity.base.OrderBill;
import com.leimingtech.core.entity.vo.BillExcelVo;
import com.leimingtech.core.entity.vo.BillVo;
import com.leimingtech.core.entity.vo.OrderBillExcelVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算表
 * @author liukai
 */
public interface OrderBillService {
	
	/**
	 * 生成订单结算信息
	 * @param obStartTime 开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime 结束时间(年月日 如:"2015-1-31")
	 */
	void addBill(String obStartTime,String obEndTime);
	
	/**
	 * 生成订单结算信息
	 * @param obStartTime 开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime 结束时间(年月日 如:"2015-1-31")
	 */
	void addOrderBill(String obStartTime,String obEndTime);
	
	/**
	 * 生成退款退货单结算信息
	 * @param obStartTime 开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime 结束时间(年月日 如:"2015-1-31")
	 */
	void addRefundReturnBill(String obStartTime,String obEndTime);
	
	/**
	 * 卖家确认结算
	 * @param obId 结算id
	 * @param storeId 店铺id
	 * @return 1:完成;2:结算单不属于当前店铺;3:状态错误
	 */
	int updateSellerConfirm(Integer obId, Integer storeId);
	
	/**
	 * 平台确认审核通过
	 * @param obId 结算id
	 * @param obPayContent 支付备注
	 * @return 1:完成;2不存在;3:状态错误
	 */
	int updateAdminAudit(Integer obId,String obPayContent);
	
	/**
	 * 卖家确认收款
	 * @param obId 结算id
	 * @param storeId 店铺id
	 * @return 1:完成;2:结算单不属于当前店铺;3:状态错误
	 */
	int updateSellerConfirmReceipt(Integer obId, Integer storeId);
	
	/**
	 * 查询分页结算表数据
	 * @param pager
	 * @return
	 */
	List<OrderBill> findOrderBillPagerList(Pager pager);
	
	/**
	 * 根据id查询结算表
	 * @param id
	 * @return
	 */
	OrderBill findOrderBillById(Integer id);
	
	/**
	 * 条件查询结算详情
	 * @param orderBill
	 * @return
	 */
	OrderBill findOrderBillDetail(OrderBill orderBill);
	
	/**
	 * 条件查询结算信息(无分页)
	 * @param orderBill 
	 * @return
	 */
	List<OrderBill> findOrderBillList(OrderBill orderBill);
	
	/**
	 * 通过结算id和店铺id查询结算详情
	 * @param obId 结算id
	 * @param storeId 店铺id
	 * @return
	 */
	OrderBill findOrderBillByStore(Integer obId,Integer storeId);
	
	/**
	 * 条件查询结算excel信息
	 * @param orderBill
	 * @return
	 */
	List<OrderBillExcelVo> findExcelVoList(OrderBill orderBill);
	
	/**
	 * 分页查询结算管理总账单
	 * @param pager 
	 * @return
	 */
	List<BillVo> findBillVoPagerList(Pager pager);
	
	/**
	 * 查询结算管理总账单条数
	 * @param billVo
	 * @return
	 */
	int findBillVoCount(BillVo billVo);
	
	/**
	 * 查询店铺下的总帐单
	 * @param billVo 必传店铺id
	 * @return
	 */
	BillVo findBillVoDetail(BillVo billVo);
	
	/**
	 * 根据店铺id查询店铺下本期应结的总账信息
	 * @param storeId
	 * @return
	 */
	BillVo findBillVoByStoreId(Integer storeId);
	
	/**
	 * 条件查询结算管理总账单excel信息
	 * @param billVo
	 * @return
	 */
	List<BillExcelVo> findBillExcelVoList(BillVo billVo);
}
