package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.vo.ReturnDetailVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款退货mapper接口
 * 
 * @author liukai
 * @version 2015-11-02
 */
@SqlMapper
public interface RefundReturnMapper {

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
	 * 修改退款退货数据
	 * 
	 * @param refundReturn
	 */
	public void updateRefundReturn(RefundReturn refundReturn);

	/**
	 * 保存退款退货数据
	 * 
	 * @param refundReturn
	 */
	public void saveRefundReturn(RefundReturn refundReturn);

	/**
	 * 获取所有退款退货数据
	 * @return
	 */
	public List<RefundReturn> findRefundReturnAllList();
	
	/**
	 * 查询退款退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 * @param refundReturn
	 * @return
	 */
	public ReturnDetailVo findRefundReturnDetail(RefundReturn refundReturn);
	
	/**
	 * 根据退款批次号修改管理员审核状态(只限支付宝)
	 * @param refundReturn
	 */
	public void updateRefundReturnByBatchNo(RefundReturn refundReturn);
	
	/**
	 * 根据退款批次号查询退货退款信息
	 * @param batchNo
	 * @return
	 */
	public List<RefundReturn> findRefundReturnByBatchNo(String batchNo);

}