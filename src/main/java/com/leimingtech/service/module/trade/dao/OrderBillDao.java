package com.leimingtech.service.module.trade.dao;

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
public interface OrderBillDao {
	/**
	 * 保存结算表
	 * @param orderBill
	 */
	void saveOrderBill(OrderBill orderBill);
	
	/**
	 * 修改结算表
	 * @param orderBill
	 */
	void updateOrderBill(OrderBill orderBill);
	
	/**
	 * 根据id查询结算表
	 * @param id
	 * @return
	 */
	OrderBill findOrderBillById(Integer id);
	
	/**
	 * 查询分页结算表数据
	 * @param pager
	 * @return
	 */
	List<OrderBill> findOrderBillPagerList(Pager pager);
	
	/**
	 * 获取所有结算表数据
	 * @return
	 */
	List<OrderBill> findOrderBillAllList();
	
	/**
	 * 条件查询结算信息(无分页)
	 * @param orderBill 
	 * @return
	 */
	List<OrderBill> findOrderBillList(OrderBill orderBill);
	
	/**
	 * 条件查询结算详情,必传结算id
	 * @param orderBill
	 * @return
	 */
	OrderBill findOrderBillDetail(OrderBill orderBill);
	
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
	 * 条件查询结算管理总账单excel信息
	 * @param billVo
	 * @return
	 */
	List<BillExcelVo> findBillExcelVoList(BillVo billVo);
}
