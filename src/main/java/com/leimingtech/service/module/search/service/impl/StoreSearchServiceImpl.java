package com.leimingtech.service.module.search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.Constants;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.search.service.SearchService;
import com.leimingtech.service.module.search.service.StoreSearchService;
import com.leimingtech.service.module.store.dao.StoreDao;
import com.leimingtech.service.utils.lucene.LucenePager;
import com.leimingtech.service.utils.page.Pager;

/**
 * 店铺搜索
 * @author cgl
 * @author kviuff   修改
 * @version 2015-11-26 15:36:50  修改时间
 */
@Service
public class StoreSearchServiceImpl implements StoreSearchService{

	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private LucenceService lucenceService;

	/**
	 * 商品索引路径
	 */
	public static final String STORE_INDEX_PATH = CommonConstants.LUCENE_BASEPATH + Constants.STORE_SEARCH_INDEX_PATH;
	
	/**
	 * 批量建立商品索引
	 * @return 返回建立索引的商品个数
	 */
	@Override
	public Integer createStoreIndex(Store storeConditions) {
		//准备分页,这里的分页是为了在建立索引的时候防止内存溢出
		Pager pager = new Pager();
		//设置每次索引2000条,暂时写死
		int pageSize = 2000;
		pager.setPageSize(pageSize);
		//设置类
		Store storeCondition = null;
		if(storeConditions == null){
			storeCondition = new Store();
		}else{
			storeCondition = storeConditions;
		}
		//设置商品状态
		storeCondition.setStoreState(1);
		pager.setCondition(storeCondition);
		//得到总条数
		int count = storeDao.queryCount(storeCondition);
		//设置总条数
		pager.setTotalRows(count);
		try {
			
			//将原先的全部删除
			//lucenceService.deleteAllStoreIndex();
			//准备list
			List<Store> storeList = null;
			//设置一共有几页
			int pageCount = 0;
			pageCount = count%pager.getPageSize()==0?count/pager.getPageSize():count/pager.getPageSize()+1;
			//开始循环分页取出
			for(int j = 1; j <= pageCount; j++ ){
				//这里分页循环取出storeList
				pager.setPageNo(j);
				storeList = storeDao.queryList(pager);
				// 创建索引
				lucenceService.createStoreMoreIndex(storeList);
			}
				return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 增量索引
	 */
	@Override
	public Integer addStoreIndex(Integer storeId) {
		Store storeCondition = new Store();
		storeCondition.setStoreId(storeId);
		//设置商品状态
		storeCondition.setStoreState(1);
		Store store = storeDao.findByIds(storeCondition);
		if(store == null){
			return 0;
		}
		try {
			lucenceService.creatStoreOneIndex(store);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	
	/**
	 * 删除索引
	 */
	@Override
	public void deleteStoreIndex(Integer id) {
		try{
			lucenceService.deleteStoreOneIndex("storeId", id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public LucenePager searchStore(LucenePager lucenePager) {
		lucenePager = lucenceService.searchStoreIndex(lucenePager);
		return lucenePager;
	}
	
}
