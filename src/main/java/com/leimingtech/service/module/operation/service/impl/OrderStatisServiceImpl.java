package com.leimingtech.service.module.operation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.OrderStatis;
import com.leimingtech.service.module.operation.dao.OrderStatisDao;
import com.leimingtech.service.module.operation.service.OrderStatisService;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：AdminLogServiceImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月5日 下午10:43:18   
 * 修改人：liuhao   
 * 修改时间：2014年11月5日 下午10:43:18   
 * 修改备注：   
 * @version    
 *
 */
@Service("orderStatisService")
@Slf4j
public class OrderStatisServiceImpl implements OrderStatisService {

	@Resource
    private OrderStatisDao orderStatisDao;

	@Override
	public int countOrderStatis(Pager pager) {
		// TODO Auto-generated method stub
		return orderStatisDao.countOrderStatis(pager);
	}

	@Override
	public List<OrderStatis> queryOrderStatisList(Pager pager) {
		// TODO Auto-generated method stub
		return orderStatisDao.queryOrderStatisList(pager);
	}

	
}

