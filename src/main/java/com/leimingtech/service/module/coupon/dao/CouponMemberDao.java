package com.leimingtech.service.module.coupon.dao;

import java.util.List;

import com.leimingtech.core.entity.base.CouponMember;
import com.leimingtech.core.entity.searchbean.CouponSearch;

/**
 * 会员领取优惠券接口dao
 * @author kviuff
 * @date 2015-07-31 16:00:00
 */
public interface CouponMemberDao {
	/**
	 * 保存领取优惠券
	 * @param couponMember
	 */
	void saveCouponMember(CouponMember couponMember);
	
	/**
	 * 根据会员id获取所领取的优惠券数量
	 * @param memberId
	 * @return
	 */
	int getCouponMemberCount(Integer couponId);
	
	/**
	 * 根据会员id和店铺id获取优惠券列表
	 * @param memberId
	 * @param storeId
	 * @return
	 */
	List<CouponMember> getCouponListByMemberIdAndStoreId(CouponSearch couponSearch);
	
	/**
	 * 修改是否已使用状态
	 * @param couponMember
	 */
	void updateCouponMember(CouponMember couponMember);
	/**
	 * 通过用户id、店铺和商品的列表获取优惠券列表
	 * @param memberId
	 * @param storeIds
	 * @param goodsClassIds
	 * @return
	 */
	public List<CouponMember> findCouponsByConditions(Integer memberId,String storeIds, String goodsClassIds);
	/**
	 * 根据条件获取会员优惠券
	 * @param memberId
	 * @param storeIds
	 * @param goodsClassIds
	 * @param currentTimeMillis
	 * @return
	 */
	List<CouponMember> findCouponMemberByConditions(Integer memberId,
			String storeIds, String goodsClassIds, long currentTimeMillis);
}
