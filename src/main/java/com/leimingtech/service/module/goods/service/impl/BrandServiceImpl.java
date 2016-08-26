package com.leimingtech.service.module.goods.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.goods.dao.BrandDao;
import com.leimingtech.service.module.goods.service.BrandService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：BrandServiceImpl
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

	@Resource
	private BrandDao brandDao;

	public void save(Brand brand) {
		brand.setCreateTime(System.currentTimeMillis());
		brandDao.save(brand);
	}

	public void delete(int id) {
		brandDao.delete(id);
	}

	public void update(Brand brand) {
		brand.setUpdateTime(System.currentTimeMillis());
		brandDao.update(brand);
	}

	public List<Brand> findPageList(Pager pager) {
		return brandDao.findPageList(pager);
	}

	public List<Brand> findList() {
		return brandDao.findList();
	}

	public Brand findById(long id) {
		return brandDao.findById(id);
	}

	public List<Brand> findByClassId(int classId) {
		return brandDao.findByClassId(classId);
	}

	public List<Brand> findBrandGroupByClassId() {
		return brandDao.findBrandGroupByClassId();
	}

	public Map<String, Object> saveStorebrand(String jsondata, Integer storeid) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			Brand brand = JsonUtils.fromJson(jsondata, Brand.class);
			brand.setStoreId(Long.valueOf(storeid));
			if (brand.getBrandId() != null) {
				brandDao.update(brand);
				map.put("success", true);
			} else {
				brand.setBrandApply(0);
				brand.setBrandRecommend(0);
				brandDao.save(brand);
				map.put("success", true);
			}
		} catch (Exception e) {
			log.error("保存品牌失败" + e.getMessage());
			map.put("success", false);
		}
		return map;
	}
	
	public Map<String, Object> saveStorebrand(Brand brand) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			if (brand.getBrandId() != null) {
				brandDao.update(brand);
				map.put("success", true);
			} else {
				brand.setBrandApply(0);
				brand.setBrandRecommend(0);
				brandDao.save(brand);
				map.put("success", true);
			}
		} catch (Exception e) {
			log.error("保存品牌失败" + e.getMessage());
			map.put("success", false);
		}
		return map;
	}

	public List<Brand> getBrandListByStoreId(int storeId) {
		return brandDao.getBrandListByStoreId(storeId);
	}

	@Override
	public List<Brand> getBrandListByTypeId(int typeId) {
		return brandDao.getBrandListByTypeId(typeId);
	}

	@Override
	public int countBrand(Brand brand) {
		return brandDao.countBrand(brand);
	}
	

}
