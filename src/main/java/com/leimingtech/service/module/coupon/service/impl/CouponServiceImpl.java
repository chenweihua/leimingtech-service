package com.leimingtech.service.module.coupon.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Coupon;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.service.module.coupon.dao.CouponDao;
import com.leimingtech.service.module.coupon.service.CouponService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 优惠券dao
 * @author kviuff
 * @date 2015-07-27 17:50:00
 */
@Service
public class CouponServiceImpl implements CouponService {

	@Resource
	private CouponDao couponDao;
	
	/**
	 * 保存优惠券
	 * @param coupon
	 */
	public void saveCoupon(Coupon coupon) {
		couponDao.saveCoupon(coupon);
	}

	/**
	 * 删除优惠券
	 * @param id
	 */
	public void deleteCoupon(int id) {
		couponDao.deleteCoupon(id);
	}

	/**
	 * 修改优惠券
	 * @param coupon
	 */
	public void updateCoupon(Coupon coupon) {
		couponDao.updateCoupon(coupon);
	}

	/**
	 * 获取优惠券数量
	 * @param pager
	 * @return
	 */
	public int findCouponCount(Coupon coupon) {
		return couponDao.findCouponCount(coupon);
	}

	/**
	 * 获取优惠券分页列表
	 * @param pager
	 * @return
	 */
	public List<Coupon> findCouponPagerList(Pager pager) {
		return couponDao.findCouponPagerList(pager);
	}

	/**
	 * 根据id获取优惠券
	 * @param id
	 * @return
	 */
	public Coupon getCouponById(int id) {
		return couponDao.getCouponById(id);
	}

	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	public List<Coupon> findCouponAllList(int storeId) {
		return couponDao.findCouponAllList(storeId);
	}

	/**
	 * 计算优惠价钱
	 * @param storeId  店铺id
	 * @param MemberId 会员id
	 * @param couponId 优惠券id
	 */
	public BigDecimal getCouponPrice(int storeId, int MemberId, int couponId) {
		BigDecimal price = new BigDecimal(0);
		Coupon coupon = couponDao.getCouponById(couponId);
		// 优惠券开始时间
		long sLong = coupon.getStartTime();
		// 优惠券结束时间
		long eLong = coupon.getEndTime();
		// 当前时间
		long nLong = System.currentTimeMillis();
		// 判断当前时间是否在优惠券开始时间与结束时间之间
		// 如果在时间段内返回优惠的价格，如果不在返回0
		if(nLong >= sLong && nLong <= eLong){
			price = coupon.getCouponPrice();
		}else{
			price = BigDecimal.valueOf(0.0);
		}
		return price;
	}
	/**
	 * 通过条件获取优惠券列表
	 * @return
	 */
	@Override
	public List<Coupon> findCouponbycolumList(Coupon coupon) {
		return couponDao.findCouponbycolumList(coupon);
	}
	/**
	 * 通过couponClassId获取优惠券
	 * @return
	 */
	@Override
	public List<Coupon> findCouponByClassId(Integer couponClassId) {
		return couponDao.findCouponByClassId(couponClassId);
	}
	/**
	 * 获取优惠券列表
	 */
	@Override
	public List<Coupon> findCoupons(Coupon coupon) {
		return couponDao.findCoupons(coupon);
	}
	/**
	 * 通过店铺和商品的列表获取优惠券列表
	 * @param storeIds 店铺id,以逗号分隔的字符串
	 * @param list 商品分类list
	 * @return
	 */
	@Override
	public List<Coupon> findCouponsByCondition(String storeIds,List<Goods> list){
		String goodsClassIds = "";
		String sp = "";
		for (Goods goods : list) {
			Integer gcId = goods.getGcId();
			goodsClassIds = sp + gcId;
			sp = ",";
		}
		return couponDao.findCouponsByCondition(storeIds,goodsClassIds);
	}
}
