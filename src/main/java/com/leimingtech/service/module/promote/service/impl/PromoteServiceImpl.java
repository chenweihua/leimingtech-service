package com.leimingtech.service.module.promote.service.impl;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.promote.common.PromoteCondition;
import com.leimingtech.service.module.promote.context.PromoteContext;
import com.leimingtech.service.module.promote.impl.ManJian;
import com.leimingtech.service.module.promote.service.PromoteService;

@Service
public class PromoteServiceImpl implements PromoteService{

	@Override
	public void calcuPrice(OrderVo orderVo, PromoteCondition condition) {
		//实例化促销上下文
		PromoteContext context = new PromoteContext();
//		switch (1) {
//			case 1:
//				//满即减
//				context.setPromote(new ManJian());
//				break;
//			case 2:
//				//add other promote
//				break;
//			default:
//				break;
//		}
		//满即减
		context.setPromote(new ManJian());
		context.calculate(orderVo, condition);
	}

}
