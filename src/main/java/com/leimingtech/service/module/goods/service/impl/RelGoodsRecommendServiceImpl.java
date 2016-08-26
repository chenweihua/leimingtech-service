package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.RelGoodsRecommend;
import com.leimingtech.service.module.goods.dao.RelGoodsRecommendDao;
import com.leimingtech.service.module.goods.service.RelGoodsRecommendService;

@Slf4j
@Service
public class RelGoodsRecommendServiceImpl implements RelGoodsRecommendService{
	@Resource
    private RelGoodsRecommendDao relGoodsRecommendDao;
	
	@Override
	public void save(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommendDao.save(relGoodsRecommend);
	}
	@Override
	public void delete(int id) {
		relGoodsRecommendDao.delete(id);
	}
	@Override
	public List<RelGoodsRecommend> findgoodsList(RelGoodsRecommend relGoodsRecommend) {
		return relGoodsRecommendDao.findgoodsList(relGoodsRecommend);
	}
	@Override
	public List<RelGoodsRecommend> findgoodsids(Integer reCommendId) {
		return relGoodsRecommendDao.findgoodsids(reCommendId);
	}
	/**
     * 修改
     * @param RelGoodsRecommend
     */
	@Override
	public void updaterel(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommendDao.updaterel(relGoodsRecommend);
	}
}
