package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.vo.ReturnDetailVo;
import com.leimingtech.service.module.trade.dao.RefundReturnDao;
import com.leimingtech.service.module.trade.dao.mapper.RefundReturnMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款退货daoImpl
 *
 * @author liukai
 * @version 2015-11-02
 */
@Repository
public class RefundReturnDaoImpl implements RefundReturnDao {
	
	/** 退款退货mapper接口*/
	@Resource
	private RefundReturnMapper refundReturnMapper;
	
	/**
	 * 查询分页退款退货数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<RefundReturn> findRefundReturnPagerList(Pager pager){
		return refundReturnMapper.findRefundReturnPagerList(pager);
	}

	/**
	 * 通过id获取单条退款退货数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public RefundReturn findRefundReturnById(int id){
		return refundReturnMapper.findRefundReturnById(id);
	}
	
	/**
	 * 条件查询退款退货总条数
	 * @param refundReturn
	 * @return
	 */
	public int findRefundReturnCount(RefundReturn refundReturn){
		return refundReturnMapper.findRefundReturnCount(refundReturn);
	}

	/**
	 * 通过id删除退款退货数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteRefundReturnById(int id){
		refundReturnMapper.deleteRefundReturnById(id);
	}

	/**
	 * 修改退款退货数据
	 * 
	 * @param refundReturn
	 */
	@Override
	public void updateRefundReturn(RefundReturn refundReturn){
		refundReturnMapper.updateRefundReturn(refundReturn);
	}
	/**
	 * 保存退款退货数据
	 * 
	 * @param refundReturn
	 */
	@Override
	public void saveRefundReturn(RefundReturn refundReturn){
		refundReturnMapper.saveRefundReturn(refundReturn);
	}
	/**
	 * 获取所有退款退货数据
	 * 
	 * @return
	 */
	@Override
	public List<RefundReturn> findRefundReturnAllList(){
		return refundReturnMapper.findRefundReturnAllList();
	}
	
	/**
	 * 查询退款退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 * @param refundReturn
	 * @return
	 */
	public ReturnDetailVo findRefundReturnDetail(RefundReturn refundReturn){
		return refundReturnMapper.findRefundReturnDetail(refundReturn);
	}
	
	/**
	 * 根据退款批次号修改管理员审核状态(只限支付宝)
	 * @param refundReturn
	 */
	public void updateRefundReturnByBatchNo(RefundReturn refundReturn){
		refundReturnMapper.updateRefundReturnByBatchNo(refundReturn);
	}
	
}
