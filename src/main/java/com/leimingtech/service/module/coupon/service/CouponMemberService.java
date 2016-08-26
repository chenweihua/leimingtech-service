package com.leimingtech.service.module.coupon.service;

import java.util.List;

import com.leimingtech.core.entity.base.CouponMember;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.searchbean.CouponSearch;
import com.leimingtech.core.entity.vo.CartVo;

/**
 * 会员领取优惠券接口
 * @author kviuff
 * @date 2015-07-31 16:00:00
 */
public interface CouponMemberService {
	
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
	 * @param couponSearch 查询实体
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
	 * @param list
	 * @return
	 */
	List<CouponMember> findCouponsByConditions(Integer memberId,String storeIds,List<Goods> list);

	/**
	 * 根据条件获取会员优惠券
	 * @param memberId
	 * @param storeIds
	 * @param goodsClassIds
	 * @param currentTimeMillis 查询时间戳,优惠券生效时间段查询
	 * @return
	 */
	List<CouponMember> findCouponMemberByConditions(Integer memberId,
			String storeIds, String goodsClassIds, long currentTimeMillis);
}
