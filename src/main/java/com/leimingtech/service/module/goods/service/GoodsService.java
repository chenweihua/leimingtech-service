package com.leimingtech.service.module.goods.service;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.GoodsExcel;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.vo.GoodsTradeVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * @author cgl
 * 商品service<br>
 * 创建时间: 2015年08月14日11:00:36
 */
public interface GoodsService {
	
	/**
	 * 根据商品字段获取商品的数量
	 * @param goods
	 * @return
	 */
	int countGoods(Goods goods);
	
	/**
	 * 通过goodsid查找goods
	 * @param goodsId 商品主键id
	 * @return goods 商品实体类
	 */
	Goods findGoodById(Integer goodsId);
	
	/**
	 * 查询当前页码的商品集合接口
	 * @param pager 实体类pager
	 * @return List<Goods> goods实体类的集合
	 */
	List<Goods> findGoodPagerList(Pager pager);
	
	/**
	 * 保存商品接口
	 * @param goods 商品实体类
	 */
	void saveGoods(Goods goods);
	
	/**
	 * 修改商品接口
	 * @param goods 商品实体类
	 */
	void updateGoods(Goods goods);
	
	/**
	 * 删除商品接口
	 * @param goodsId 商品主键id
	 */
	void deleteGoods(Integer goodsId);
	
	/**
	 * 通过一定条件的条件,查找某个商品,<br>
	 * 这个方法只会返回一个商品,<br>
	 * 使用方法:<br>
	 * 新建一个goods对象,在这个对象中<br>
	 * 一定要设置goodsid这个属性<br>
	 * 可以选择set属性:storeId,goodsState<br>
	 * 使用这个方法会根据你所设置的条件去查询,<br>
	 * 如果没有返回null<br>
	 * @param goods 商品查询条件实体类
	 * @return goods 商品实体类
	 */
	Goods findOneGoodByCondition(Goods goods);
	
    /**
     * 获取商品的所有图片路径
     * @param goodsid 商品主键id
     * @return List<String> 图片路径
     */
    List<String> getGoodsImgList(int goodsid);
    
    /**
     * 获取商品的规格
     * @param goodsId 商品主键id
     * @return Map<String, Object> <br>
     * map中存着4组数据,他们的键分别是:goodsColImg,	specname,	specvalue,	goodsSpecs<br>
     * goodsColImg所对应的值为: map <string,string> 这个map的键为规格值id. 值为所对应的图片 <br>
     * specname所对应的值为:	map <string,string> 这个map的键为规格名称id. 值为所对应的规格名称<br>
     * specvalue所对应的值为: Map<String, List<GoodsSpecVo>> 这个map的键为规格名称id. 值为所对应的GoodsSpecVo实体类的集合,注意:这个实体类中spName和colImg这2个属性都都为null<br>
     * goodsSpecs所对应的值为:  List<GoodsSpec> 实体GoodsSpec的集合<br>
     */
    Map<String, Object> getGoodsSpec(int goodsId);
    
    /**
     * 获取商品的属性
     * @param goodsId 商品主键id
     * @return Map<String, Object> <br>
     * map中存着3组数据,他们的键分别是:goodsattr,	goodsbody,	goodsbrandname<br>
     * goodsattr所对应的值为: List<GoodsAttrVo> 实体GoodsAttrVo的集合 <br>
     * goodsbody所对应的值为:	String goodsBody对应商品详情<br>
     * goodsbrandname所对应的值为: String 商品对应的品牌名称<br>
     */
    Map<String, Object> getGoodsAttr(int goodsId);
    
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
	 * 根据店铺id,下架商品，并删除索引
	 * @param goods
	 * @return
	 */
	void delserchgoods(Integer storeId);
	
	
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	List<Goods> findGoodListbystoreid(Integer storeId);
	/**
	 * 根据分类id查询商品
	 * @param gcId
	 * @return
	 */
	List<Goods> findGoodListByGcId(Integer gcId);
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	List<GoodsExcel> findGoodListbystoreid2(Integer storeId);
	
	/**
	 * 根据店铺id获取商品流量
	 * @param storeId
	 * @return
	 */
	List<Map<String,Object>> countGoodsClick(Integer storeId);

	void updateGoodOutEdit(Goods goods);

}
