package com.leimingtech.service.module.goods.dao.mapper;

import com.leimingtech.core.entity.base.GoodsImages;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * 
 *    
 * 项目名称：leimingtech-seller   
 * 类名称：GoodsImagesMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月20日 下午10:11:59   
 * 修改人：liuhao   
 * 修改时间：2014年12月20日 下午10:11:59   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface GoodsImagesMapper {

	void saveGoodsImages(GoodsImages goodsImages);
	
	void deleteGoodsImage(GoodsImages goodsImages);
}
