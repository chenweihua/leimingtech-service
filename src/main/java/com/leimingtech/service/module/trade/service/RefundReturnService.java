package com.leimingtech.service.module.trade.service;

import java.util.List;

import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.vo.ReturnDetailVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款退货Service接口
 *
 * @author liukai
 * @version 2015-11-02
 */
public interface RefundReturnService {

	/**
	 * 查询分页退款退货数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<RefundReturn> findRefundReturnPagerList(Pager pager);

	/**
	 * 通过id获取单条退款退货数据
	 * 
	 * @param id
	 * @return
	 */
	public RefundReturn findRefundReturnById(int id);
	
	/**
	 * 条件查询退款退货总条数
	 * @param refundReturn
	 * @return
	 */
	public int findRefundReturnCount(RefundReturn refundReturn);

	/**
	 * 通过id删除退款退货数据
	 * 
	 * @param id
	 */
	public void deleteRefundReturnById(int id);

	/**
	 * 获取所有退款退货数据
	 * 
	 * @return
	 */
	public List<RefundReturn> findRefundReturnAllList();
	
	/**
	 * 查询退款退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 * @param refundId
	 * @param buyerId
	 * @param storeId
	 * @return
	 */
	public ReturnDetailVo findRefundReturnDetail(Integer refundId,Integer buyerId,Integer storeId);
	
	/**
	 * 退货买家发货
	 * @param refundId
	 * @param expressName
	 * @param invoiceNo
	 */
	public void updaterefundReturnDelivery(Integer refundId,String expressName,String invoiceNo);
	
	/**
	 * 卖家退货审核
	 * @param refundId 记录ID
	 * @param sellerState 卖家处理状态
	 * @param sellerMessage 卖家备注
	 * @param operator 操作人
	 */
	public void updateRefundReturnSeller(Integer refundId,Integer sellerState,String sellerMessage,String operator);
	
	/**
	 * 退货卖家收货
	 * @param refundId 记录ID
	 * @param receiveMessage 收货备注
	 * @param operator 操作人
	 */
	public void updateRefundReturnConfirm(Integer refundId,String receiveMessage,String operator);

	/**
	 * 退款退货管理员审核退款
	 * @param refundId 记录ID
	 * @param adminMessage 管理员备注
	 */
	public void updateRefundReturnAudiReturn(Integer refundId,String adminMessage);
	
	/**
	 * 根据退款批次号修改管理员审核状态(只限支付宝)
	 * @param batchNo 退款批次号
	 * @param adminMessage 管理员备注
	 */
	public void updateRefundReturnByBatchNo(String batchNo,String adminMessage);
	
	/**
	 * 退款退货保存退款批次号
	 * @param refundId 记录ID
	 * @param batchNo 退款批次号
	 */
	public void saveBatchNo(Integer refundId, String batchNo);
}
