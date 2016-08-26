//package com.leimingtech.service.module.store.service.impl;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.leimingtech.core.entity.Seller;
//import com.leimingtech.service.module.store.dao.SellerDao;
//import com.leimingtech.service.module.store.service.SellerService;
//
//@Service
//public class SellerServiceImpl implements SellerService{
//
//    @Resource
//    private SellerDao sellerDao;
//
//    @Override
//    public Seller findBySellerName(String sellerName) {
//        return sellerDao.findBySellerName(sellerName);
//    }
//
//    @Override
//    public void updateLastLoginTime(String sellerName) {
//        sellerDao.updateLastLoginTime(sellerName);
//    }
//}
