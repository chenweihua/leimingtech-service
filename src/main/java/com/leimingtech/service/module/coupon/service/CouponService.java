package com.leimingtech.service.module.coupon.service;

import java.math.BigDecimal;
import java.util.List;

import com.leimingtech.core.entity.base.Coupon;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.service.utils.page.Pager;

/**
 * 优惠券接口
 * @author kviuff
 * @date 2015-07-27 17:50:00
 */
public interface CouponService {
	
	/**
	 * 保存优惠券
	 * @param coupon
	 */
	void saveCoupon(Coupon coupon);
	
	/**
	 * 删除优惠券
	 * @param id
	 */
	void deleteCoupon(int id);
	
	/**
	 * 修改优惠券
	 * @param coupon
	 */
	void updateCoupon(Coupon coupon);
	
	/**
	 * 获取优惠券数量
	 * @param pager
	 * @return
	 */
	int findCouponCount(Coupon coupon);
	
	/**
	 * 获取优惠券分页列表
	 * @param pager
	 * @return
	 */
	List<Coupon> findCouponPagerList(Pager pager);
	
	/**
	 * 根据id获取优惠券
	 * @param id
	 * @return
	 */
	Coupon getCouponById(int id);
	
	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	List<Coupon> findCouponAllList(int storeId);
	
	/**
	 * 计算优惠价钱
	 * @param storeId 店铺id
	 * @param MemberId 会员id
	 * @return
	 */
	BigDecimal getCouponPrice(int storeId, int MemberId, int couponId);
	/**
	 * 通过条件获取优惠券列表
	 * @return
	 */
	List<Coupon>  findCouponbycolumList(Coupon coupon);
	/**
	 * 通过couponClassId获取优惠券
	 * @return
	 */
	List<Coupon> findCouponByClassId(Integer couponClassId);
	/**
	 * 获取优惠券列表
	 * @param coupon
	 * @return
	 */
	List<Coupon> findCoupons(Coupon coupon);
	/**
	 * 通过店铺和商品的列表获取优惠券列表
	 * @param storeIds 店铺id,以逗号分隔的字符串
	 * @param list 商品list，查询时以分类id查询的
	 * @return
	 */
	List<Coupon> findCouponsByCondition(String storeIds,List<Goods> list);
}
