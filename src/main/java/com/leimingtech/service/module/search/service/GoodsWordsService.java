package com.leimingtech.service.module.search.service;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;

import com.leimingtech.core.entity.base.GoodsWords;

/**
 * 商品关键词
 * @author cgl
 * 2015年08月18日10:05:53
 */
public interface GoodsWordsService {
	
	/**
	 * 将商品名称,进行分词,存入数据库
	 * @param goodsName 商品名称
	 */
	void saveKeywordToDatabase(Analyzer analyzer, String goodsName);
	
	/**
	 * 关键词匹配
	 */
	public List<GoodsWords> keywordMatch(String keyword);
	
	/**
	 * 初始化
	 */
	public void deleteAndInitGoodsWord();
}
