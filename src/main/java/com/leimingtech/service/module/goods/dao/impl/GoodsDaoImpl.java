package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.GoodsExcel;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.vo.GoodsTradeVo;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-seller   
 * 类名称：GoodsDaoImpl   
 * 类描述：   
 * 创建人：cgl
 * 创建时间：2015年06月29日10:01:27
 * 修改人：liuhao   
 * 修改时间：2015年06月29日10:01:27
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class GoodsDaoImpl implements GoodsDao{

    @Resource
    private GoodsMapper goodsMapper;

	public Goods findGoodById(Integer goodsId) {
		return goodsMapper.findGoodById(goodsId);
	}

	public List<Goods> findGoodPagerList(Pager pager) {
		return goodsMapper.findGoodPagerList(pager);
	}

	public void saveGoods(Goods goods) {
		goodsMapper.saveGoods(goods);
	}

	public void updateGoods(Goods goods) {
		goodsMapper.updateGoods(goods);
	}

	public void deleteGoods(Integer goodsId) {
		goodsMapper.deleteGoods(goodsId);
	}

	public Goods findOneGoodByCondition(Goods goods) {
		return goodsMapper.findOneGoodByCondition(goods);
	}

	public int countGoods(Goods goods) {
		return goodsMapper.countGoods(goods);
	}
	
	/**
	 * 分页查询获得findTradeGoodlist
	 * @param pager
	 * @return
	 */
	@Override
	public List<GoodsTradeVo> findTradeGoodPagerList(Pager pager) {
		return goodsMapper.findTradeGoodPagerList(pager);
	}
	/**
	 * 根据商品字段获取商品的数量
	 * @param GoodsTradeVo
	 * @return
	 */
	@Override
	public int findTradeGoodcount(GoodsTradeVo goodsTradeVo) {
		return goodsMapper.findTradeGoodcount(goodsTradeVo);
	}
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	@Override
	public List<Goods> findGoodListbystoreid(Integer storeId) {
		return goodsMapper.findGoodListbystoreid(storeId);
	}

	@Override
	public List<Map<String,Object>> countGoodsClick(Integer storeId) {
		return goodsMapper.countGoodsClick(storeId);
	}
    
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	@Override
	public List<GoodsExcel> findGoodListbystoreid2(Integer storeId) {
		return goodsMapper.findGoodListbystoreid2(storeId);
	}

	@Override
	public List<Goods> findGoodListByGcId(Integer gcId) {
		return goodsMapper.findGoodListByGcId(gcId);
	}

}
