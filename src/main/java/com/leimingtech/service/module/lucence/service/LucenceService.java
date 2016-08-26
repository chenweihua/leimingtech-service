package com.leimingtech.service.module.lucence.service;

import java.util.List;

import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.service.utils.lucene.LucenePager;

/**
 * 生成LUCENCE索引接口
 * @author KVIUFF
 * @version 2015-11-25 09:58:00
 *
 */
public interface LucenceService {
	/**
	 * 商品生成LUCENE索引 单个
	 * @param goods
	 */
	public void creatOneIndex(Goods goods);
	
	/**
	 * 商品生成LUCENE索引 多个
	 * @param list
	 */
	public void createMoreIndex(List<Goods> list);
	
	/**
	 * 商品删除索引 多个
	 * @param list
	 */
	public void deleteMoreIndex(List<Goods> list);
	
	/**
	 * 商品删除索引 单个
	 * @param goods
	 */
	public void deleteOneIndex(Goods goods);
	
	/**
	 * 商品删除指定商品索引
	 * @param field 商品的LUCENE某一个字段名称
	 * @param id 该字段名称对应的id为多少的商品将会被删除
	 */
	public void deleteOneIndex(String field,Integer id);
	
	/**
	 * 商品更新索引 多个
	 * @param list
	 */
	public void updateMoreIndex(List<Goods> list);
	
	/**
	 * 商品更新索引  单个
	 * @param goods
	 */
	public void updateOneIndex(Goods goods);
	
	/**
	 * 商品LUCENCE查询
	 * @param lucenePager
	 * @return
	 */
	public LucenePager searchGoodsIndex(LucenePager lucenePager);
	
	/**
	 * 店铺生成LUCENE索引 单个
	 * @param goods
	 */
	public void creatStoreOneIndex(Store store);
	
	/**
	 * 店铺生成LUCENE索引 多个
	 * @param list
	 */
	public void createStoreMoreIndex(List<Store> list);
	
	/**
	 * 店铺删除索引 单个
	 * @param goods
	 */
	public void deleteStoreOneIndex(Store store);
	
	/**
	 * 店铺删除索引 多个
	 * @param list
	 */
	public void deleteStoreMoreIndex(List<Store> list);
	
	/**
	 * 店铺更新索引 单个
	 * @param goods
	 */
	public void updateStoreOneIndex(Store store);
	
	/**
	 * 店铺更新索引 多个
	 * @param list
	 */
	public void updateStoreMoreIndex(List<Store> list);
	
	/**
	 * 店铺删除指定商品索引
	 * @param field 店铺的LUCENE某一个字段名称
	 * @param id 该字段名称对应的id为多少的店铺将会被删除
	 */
	public void deleteStoreOneIndex(String field,Integer id);
	
	/**
	 * 删除所有的店铺的索引
	 */
	public void deleteAllStoreIndex();
	
	/**
	 * 店铺LUCENCE查询
	 * @param lucenePager
	 * @return
	 */
	public LucenePager searchStoreIndex(LucenePager lucenePager);
	
	
}
