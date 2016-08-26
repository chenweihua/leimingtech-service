package com.leimingtech.service.module.store.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.vo.StoreVo;
import com.leimingtech.service.module.goods.service.GoodsService;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.search.service.StoreSearchService;
import com.leimingtech.service.module.store.dao.StoreDao;
import com.leimingtech.service.module.store.service.StoreService;
import com.leimingtech.service.utils.page.Pager;

/**
 * @项目名称：leimingtech-seller
 * @类名称：StoreServiceImpl
 * @类描述：
 * @修改备注：
 * @version
 */
@Service
public class StoreServiceImpl implements StoreService {
	@Resource
	private StoreDao storeDao;
	@Resource
    private  GoodsService goodsService;
	@Resource
    private StoreSearchService storeSearchService;
	@Resource
	private LucenceService lucenceService;

	public Store findById(Integer id) {
		return storeDao.findById(id);
	}
	
	public StoreVo findVoById(Integer id) {
		return storeDao.findVoById(id);
	}

	public void updateStore(Store store) {
		storeDao.updateStore(store);
		//当开启时才建立索引
		if(store.getStoreState()!=null && store.getStoreState()==1){
			lucenceService.updateStoreOneIndex(storeDao.findById(store.getStoreId()));
		}
		if(store.getStoreState()!=null && store.getStoreState()==0){
			storeSearchService.deleteStoreIndex(store.getStoreId());//关闭店铺时删除店铺索引
			goodsService.delserchgoods(store.getStoreId());//店铺关闭后下架商品和删除店铺下的商品索引
		}
	}

    public void updateStoreCus(Store store) {
        storeDao.updateStoreCus(store);
    }
	
	public Store findByMemberId(Integer id){
		return storeDao.findByMemberId(id);
	}

	public void save(Store store) {
		store.setCreateTime(System.currentTimeMillis());
		storeDao.save(store);
	}

	public Store findByStorename(String storename) {
		return storeDao.findByStorename(storename);
	}

	public int queryCount(Store store) {
		return storeDao.queryCount(store);
	};
	
	public List<Store> queryList(Pager pager) {
		return storeDao.queryList(pager);
	}

	@Override
	public void updateVerifyPass(Store store, String verifyType,
			Map<String, Object> mailmap) {
		
	}

	@Override
	public void updateStoreCount(Map<String, String> map) {
		storeDao.updateStoreCount(map);
	}

	@Override
	public Store findByIds(Store store) {
		return storeDao.findByIds(store);
	}

	@Override
	public void updateStore(Integer storeId) {
		Store store=storeDao.findById(storeId);//获取店铺信息
		Store store2=new Store();
		if(store!=null){
			store2.setStoreId(storeId);
			store2.setStoreLastLogintime(store.getStoreLogintime());
			store2.setStoreLogintime(System.currentTimeMillis());
			storeDao.updateStore(store2);
		}
	}
	 /**
	  * 
	  * @Title: delete 
	  * @Description: TODO(根据ID 删除) 
	  * @param @param id    设定文件 
	  * @return void    返回类型 
	  */
	@Override
	public void delete(Integer id) {
		storeDao.delete(id);
	}
}
