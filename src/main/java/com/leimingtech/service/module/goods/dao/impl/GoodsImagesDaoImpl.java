package com.leimingtech.service.module.goods.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.GoodsImages;
import com.leimingtech.service.module.goods.dao.GoodsImagesDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsImagesMapper;

/**
 * 
 *    
 * 项目名称：leimingtech-seller   
 * 类名称：GoodsImagesDaoImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月20日 下午10:14:04   
 * 修改人：liuhao   
 * 修改时间：2014年12月20日 下午10:14:04   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class GoodsImagesDaoImpl implements GoodsImagesDao{

    @Resource
    private GoodsImagesMapper goodsImagesMapper;

	/**
	 * 保存图片信息
	 */
	public void saveGoodsImages(GoodsImages goodsImages) {
		// TODO Auto-generated method stub
		goodsImagesMapper.saveGoodsImages(goodsImages);
	}

	@Override
	public void deleteGoodsImage(GoodsImages goodsImages) {
		// TODO Auto-generated method stub
		goodsImagesMapper.deleteGoodsImage(goodsImages);
	}

    
}
