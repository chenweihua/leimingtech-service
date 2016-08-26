//package com.leimingtech.service.module.store.dao.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Repository;
//
//import com.leimingtech.core.entity.StoreDetail;
//import com.leimingtech.service.module.store.dao.StoreDetailDao;
//import com.leimingtech.service.module.store.dao.mapper.StoreDetailMapper;
//import com.leimingtech.service.utils.page.Pager;
//
///**
// * 项目名称：leimingtech-admin   
// * 类名称：StoreDetailDaoImpl   
// * 类描述：   
// * 修改备注：   
// * @version    
// *
// */
//@Repository
//public class StoreDetailDaoImpl implements StoreDetailDao {
//
//    @Resource
//    private StoreDetailMapper storeDetailMapper;
//    @Override
//    public int countStoreDetail(Pager pager) {
//		return storeDetailMapper.countStoreDetail(pager);
//	}
//    @Override
//	public List<StoreDetail> queryStoreDetailList(Pager pager) {
//		return storeDetailMapper.queryStoreDetailList(pager);
//	}
//    @Override
//	public void delete(Long id) {
//		storeDetailMapper.delete(id);
//	}
//    @Override
//	public StoreDetail findById(Integer id) {
//		return storeDetailMapper.findById(id);
//	}
//
//    @Override
//    public StoreDetail findByIdUinonGrade(Integer id) {
//        return storeDetailMapper.findByIdUinonGrade(id);
//    }
//    @Override
//	public void updateDetail(StoreDetail storeDetail){
//		storeDetailMapper.updateDetail(storeDetail);
//	}
//	@Override
//	public void updateDomain(StoreDetail storeDetail) {
//		storeDetailMapper.updateDomain(storeDetail);
//		
//	}
//
//
//	@Override
//	public int countStoreDetail(StoreDetail storeDetail) {
//		
//		return storeDetailMapper.countStoreDetail(storeDetail);
//	}
//    
//}
