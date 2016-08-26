package com.leimingtech.service.module.goods.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.RelGoodsRecommend;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * 
 * 项目名称：   
 * 类名称：RelGoodsRecommendMapper   
 * 类描述：   
 * 创建人：gyh   
 * 创建时间：2015年08月24日09:23:26
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface RelGoodsRecommendMapper {
	/**
     * 保存
     * @param RelGoodsRecommend
     */
    void save(RelGoodsRecommend relGoodsRecommend);
    /**
	 * 删除
	 * @param id
	 */
	void delete(int id);
	/**
     * 根据商品栏目查询商品
     * @param relGoodsRecommend
     */
	List<RelGoodsRecommend> findgoodsList(RelGoodsRecommend relGoodsRecommend);
	
	/**
     * 根据商品栏目id查询商品id
     * @param reCommendId
     */
	List<RelGoodsRecommend> findgoodsids(Integer reCommendId);
	/**
     * 修改
     * @param RelGoodsRecommend
     */
	void updaterel(RelGoodsRecommend relGoodsRecommend);
}
