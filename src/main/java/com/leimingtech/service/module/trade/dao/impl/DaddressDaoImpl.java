package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Daddress;
import com.leimingtech.service.module.trade.dao.DaddressDao;
import com.leimingtech.service.module.trade.dao.mapper.DaddressMapper;

/**
 * Created by rabook on 2014/12/20.
 */

@Repository
public class DaddressDaoImpl implements DaddressDao{

    @Resource
    private DaddressMapper daddressMapper;
    
    /**
	 * 保存发货地址
	 * @param daddress
	 */
	public void saveDaddress(Daddress daddress){
		daddressMapper.saveDaddress(daddress);
	}
	
	/**
	 * 修改发货地址,条件可传两个值,发货地址id和店铺id
	 * @param daddress
	 */
	public void updateDaddress(Daddress daddress){
		daddressMapper.updateDaddress(daddress);
	}
	
	/**
	 * 根据id删除发货地址
	 * @param addressId
	 */
	public void deleteDaddress(Integer addressId){
		daddressMapper.deleteDaddress(addressId);
	}
	
	/**
	 * 根据id查询发货地址
	 * @param addressId
	 * @return
	 */
	public Daddress findDaddressById(Integer addressId){
		return daddressMapper.findDaddressById(addressId);
	}
	
	/**
	 * 根据店铺id查询发货地址列表
	 * @param storeId
	 * @return
	 */
	public List<Daddress> findDaddressByStoreId(Integer storeId){
		return daddressMapper.findDaddressByStoreId(storeId);
	}
	
	/**
	 * 根据店铺id查询店铺下的默认发货地址数量
	 * @param storeId
	 * @return
	 */
	public int findDefaultCountByStoreId(Integer storeId){
		return daddressMapper.findDefaultCountByStoreId(storeId);
	}
	
}
