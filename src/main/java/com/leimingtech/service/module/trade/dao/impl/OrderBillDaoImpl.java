package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.OrderBill;
import com.leimingtech.core.entity.vo.BillExcelVo;
import com.leimingtech.core.entity.vo.BillVo;
import com.leimingtech.core.entity.vo.OrderBillExcelVo;
import com.leimingtech.service.module.trade.dao.OrderBillDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderBillMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算表
 * @author liukai
 */
@Repository
public class OrderBillDaoImpl implements OrderBillDao{
	@Resource
	private OrderBillMapper orderBillMapper;
	
	/**
	 * 保存结算表
	 * @param orderBill
	 */
	public void saveOrderBill(OrderBill orderBill){
		orderBillMapper.saveOrderBill(orderBill);
	}
	
	/**
	 * 修改结算表
	 * @param orderBill
	 */
	public void updateOrderBill(OrderBill orderBill){
		orderBillMapper.updateOrderBill(orderBill);
	}
	
	/**
	 * 根据id查询结算表
	 * @param id
	 * @return
	 */
	public OrderBill findOrderBillById(Integer id){
		return orderBillMapper.findOrderBillById(id);
	}
	
	/**
	 * 查询分页结算表数据
	 * @param pager
	 * @return
	 */
	public List<OrderBill> findOrderBillPagerList(Pager pager){
		return orderBillMapper.findOrderBillPagerList(pager);
	}
	
	/**
	 * 获取所有结算表数据
	 * @return
	 */
	public List<OrderBill> findOrderBillAllList(){
		return orderBillMapper.findOrderBillAllList();
	}
	
	/**
	 * 条件查询结算信息(无分页)
	 * @param orderBill 
	 * @return
	 */
	public List<OrderBill> findOrderBillList(OrderBill orderBill){
		return orderBillMapper.findOrderBillList(orderBill);
	}
	
	/**
	 * 条件查询结算详情,必传结算id
	 * @param orderBill
	 * @return
	 */
	public OrderBill findOrderBillDetail(OrderBill orderBill){
		return orderBillMapper.findOrderBillDetail(orderBill);
	}
	
	/**
	 * 条件查询结算excel信息
	 * @param orderBill
	 * @return
	 */
	public List<OrderBillExcelVo> findExcelVoList(OrderBill orderBill){
		return orderBillMapper.findExcelVoList(orderBill);
	}
	
	/**
	 * 分页查询结算管理总账单
	 * @param pager 
	 * @return
	 */
	public List<BillVo> findBillVoPagerList(Pager pager){
		return orderBillMapper.findBillVoPagerList(pager);
	}
	
	/**
	 * 查询结算管理总账单条数
	 * @param billVo
	 * @return
	 */
	public int findBillVoCount(BillVo billVo){
		return orderBillMapper.findBillVoCount(billVo);
	}
	
	/**
	 * 查询店铺下的总帐单
	 * @param billVo 必传店铺id
	 * @return
	 */
	public BillVo findBillVoDetail(BillVo billVo){
		return orderBillMapper.findBillVoDetail(billVo);
	}
	
	/**
	 * 条件查询结算管理总账单excel信息
	 * @param billVo
	 * @return
	 */
	public List<BillExcelVo> findBillExcelVoList(BillVo billVo){
		return orderBillMapper.findBillExcelVoList(billVo);
	}
}
