package com.leimingtech.service.module.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.GoodsType;
import com.leimingtech.core.entity.vo.GoodsTypeVO;
import com.leimingtech.service.module.goods.vo.GoodsTypeVo;
import com.leimingtech.service.utils.page.Pager;

public interface GoodsTypeDao {
	void save(GoodsType goodsType);

    void update(GoodsType goodsType);

    /**
     * 删除
     * @param
     */
    void delete(@Param("typeId") Integer typeId);

    GoodsType findById(@Param("typeId") Integer typeId);
    
    /**
     * 查询所有的商品类型
     * @return
     */
    List<GoodsType> findList();
	
	/**
	 * 关联查询
	 * @param typeId
	 * @return
	 */
    GoodsTypeVO selectTypeFetchOther(@Param("typeId") Integer typeId);
	
	 /**
     * 查询条数
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    /**
     * 列表
     * @param pager
     * @return
     */
    List<GoodsType> findPageList(Pager pager);
    
    //List<GoodsType> findTypeByClassId();
    
    /**
     * 保存商品类型
     * @param vo
     */
    void saveGoodsType(GoodsTypeVo vo);
    /**
     * 修改商品类型
     * @param vo
     */
    void updateGoodsType(GoodsTypeVo vo);
    
    /**
     * 只修改type
     * @param type
     */
    void updateType(GoodsType type);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<GoodsType> findList2(Integer parentid);
    /**
     * 通过父id查询子分类
     * @param gtParentId
     * @return
     */
    List<GoodsType> findChild(Integer gtParentId);
}
