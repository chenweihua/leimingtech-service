package com.leimingtech.service.module.goods.dao;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.GoodsExcel;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.vo.GoodsTradeVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-seller   
 * 类名称：GoodsDao   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月7日 下午10:44:25   
 * 修改人：liuhao   
 * 修改时间：2014年12月7日 下午10:44:25   
 * 修改备注：   
 * @version    
 *
 */
public interface GoodsDao {
	
	/**
	 * 通过goodsid查找goods
	 * @param goodsId
	 * @return
	 */
	Goods findGoodById(Integer goodsId);
	
	/**
	 * 分页查询获得list
	 * @param pager
	 * @return
	 */
	List<Goods> findGoodPagerList(Pager pager);
	
	/**
	 * 保存
	 * @param goods
	 */
	void saveGoods(Goods goods);
	
	/**
	 * 修改商品
	 * @param goods
	 */
	void updateGoods(Goods goods);
	
	/**
	 * 删除商品
	 * @param goodsId
	 */
	void deleteGoods(Integer goodsId);
	
	/**
	 * 通过一定条件的条件,查找某个商品,
	 * 这个方法只会返回一个商品,
	 * 使用方法:
	 * 新建一个goods对象,在这个对象中
	 * 一定要设置goodsid这个属性
	 * 可以选择set属性:storeId,goodsState
	 * 使用这个方法会根据你所设置的条件去查询,
	 * 如果没有返回null
	 */
	Goods findOneGoodByCondition(Goods goods);
	
	/**
	 * 根据商品字段获取商品的数量
	 * @param goods
	 * @return
	 */
	int countGoods(Goods goods);
	
	/**
	 * 分页查询获得findTradeGoodlist
	 * @param pager
	 * @return
	 */
	List<GoodsTradeVo> findTradeGoodPagerList(Pager pager);
	
	/**
	 * 根据商品字段获取商品的数量
	 * @param goods
	 * @return
	 */
	int findTradeGoodcount(GoodsTradeVo goodsTradeVo);
	
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	List<Goods> findGoodListbystoreid(Integer storeId);
	
	/**
	 * 根据店铺id获取商品流量
	 * @param storeId
	 * @return
	 */
	List<Map<String,Object>> countGoodsClick(Integer storeId);
	
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	List<GoodsExcel> findGoodListbystoreid2(Integer storeId);

	List<Goods> findGoodListByGcId(Integer gcId);
}
