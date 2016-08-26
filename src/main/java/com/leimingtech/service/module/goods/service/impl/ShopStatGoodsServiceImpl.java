package com.leimingtech.service.module.goods.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.GoodsStatCount;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.entity.base.ShopStatGoods;
import com.leimingtech.service.module.goods.dao.ShopStatGoodsDao;
import com.leimingtech.service.module.goods.service.ShopStatGoodsService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：ShopStatGoodsServiceImpl
 * @类描述：
 * @创建人：gyh
 * @创建时间：2015年7月23日 下午16:54
 * @修改备注：
 * @version
 * 
 */
@Slf4j
@Service
public class ShopStatGoodsServiceImpl implements ShopStatGoodsService {

	@Resource
	private ShopStatGoodsDao shopStatGoodsDao;

	@Override
	public void save(ShopStatGoods shopStatGoods) {
		shopStatGoodsDao.save(shopStatGoods);		
	}

	@Override
	public void delete(int sId) {
		shopStatGoodsDao.delete(sId);		
	}

	@Override
	public int findCount(Pager pager) {
		return shopStatGoodsDao.findCount(pager);
	}

	@Override
	public List<ShopStatGoods> findPageList(Pager pager) {
		return shopStatGoodsDao.findPageList(pager);
	}

	@Override
	public List<ShopStatGoods> findList() {
		return shopStatGoodsDao.findList();
	}
	@Override
	public Brand findbyIds(ShopStatGoods shopStatGoods) {
		return shopStatGoodsDao.findbyIds(shopStatGoods);
	}
	
	/**
	 * 时间段，店铺id查找内容
	 * @param map
	 * @return
	 */
	@Override
	public List<GoodsStatCount> findStatbytime(Map<String, Object> map) {
		return shopStatGoodsDao.findStatbytime(map);
	}

}
