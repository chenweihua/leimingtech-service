package com.leimingtech.service.module.cart.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.Cart;
import com.leimingtech.core.orm.mybatis.SqlMapper;


/**
 * 
 * @author WY
 *
 */
@SqlMapper
public interface CartMapper {
	
	/**
	 * 通过用户id查询购物车,同时可以传入商品的id和规格id查询存在相同商品
	 * @param cart
	 * @return
	 */
	List<Cart> queryBuyCart(Cart cart);
	
	/**
	 * 根据用户id和店铺id查询购物车
	 * @param memberId
	 * @param StoreId
	 * @return
	 */
	List<Cart> queryCartByStoreId(Integer memberId, Integer StoreId);
	
	/**
	 * 保存购物车
	 * @param cart
	 * @return
	 */
	int saveCart(Cart cart);
	
	/**
	 * 修改购物车
	 * @param cart
	 */
	void updateCart(Cart cart);
	
	/**
	 * 删除购物车
	 * @param cartId 购物车id
	 */
	void deleteCart(Integer cartId);
	
	/**
	 * 根据用户id,商品id,商品规格id删除购物车
	 * @param memberId 用户id
	 * @param goodsId 商品id 
	 * @param specId 规格id
	 */
	void deleteByMGS(Integer memberId, Integer goodsId, Integer specId);
	
	/**
	 * 修改购物车数量
	 * @param cart
	 */
	void updateCartNum(Cart cart);
	
	/**
	 * 根据cartid 查询商品
	 * @Title: queryCartById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param cart
	 * @param @return    设定文件 
	 * @return List<Cart>    返回类型 
	 * @throws
	 */
	Cart queryCartById(Integer cartId);
	
	/**
	 * 购物车下单  根据用户id和cartids 查询商品 根据店铺id,和商品一级分类id排序
	 * @param cartIds 需提供购物车id(可多条,用","隔开),用户id
	 * @param memberId 用户id
	 * @return
	 */
	List<Cart> queryByCartIds(Cart cart);
	
	/**
	 * 查询用户购物车数量
	 * @param memberId
	 * @return
	 */
	Integer queryCountByMemberId(Integer memberId);
	
	/**
	 * 清空购物车
	 * @param memberId 用户id
	 */
	void deleteAllCartByMemberId(Integer memberId);
    
}
