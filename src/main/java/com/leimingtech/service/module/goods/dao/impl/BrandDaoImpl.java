package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.service.module.goods.dao.BrandDao;
import com.leimingtech.service.module.goods.dao.mapper.BrandMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：BrandDaoImpl
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Repository
public class BrandDaoImpl implements BrandDao {
	@Resource
	private BrandMapper brandMapper;

	@Override
	public void save(Brand brand) {
		brandMapper.save(brand);
	}

	@Override
	public void delete(int id) {
		brandMapper.delete(id);
	}

	@Override
	public void update(Brand brand) {
		brandMapper.update(brand);
	}

	@Override
	public List<Brand> findPageList(Pager pager) {
		return brandMapper.findPageList(pager);
	}

	@Override
	public List<Brand> findList() {
		return brandMapper.findList();
	}

	@Override
	public Brand findById(long id) {
		return brandMapper.findById(id);
	}

	@Override
	public List<Brand> findByClassId(int classId) {
		return brandMapper.findByClassId(classId);
	}

	@Override
	public List<Brand> findBrandGroupByClassId() {
		return brandMapper.findBrandGroupByClassId();
	}

	@Override
	public List<Brand> getBrandListByStoreId(int storeId) {
		return brandMapper.getBrandListByStoreId(storeId);
	}

	@Override
	public List<Brand> getBrandListByTypeId(int typeId) {
		return brandMapper.getBrandListByTypeId(typeId);
	}

	@Override
	public List<Brand> findListByType(int typeId) {
		return brandMapper.findListByType(typeId);
	}
	 /**
     * 获取品牌数量
     * @param brand
     * @return
     */
	@Override
    public int countBrand(Brand brand){
    	return brandMapper.countBrand(brand);
    }

}
