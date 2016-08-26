package com.leimingtech.service.module.promote.service;

import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.promote.common.PromoteCondition;

public interface PromoteService {
	
	/**
	 * 促销价格计算
	 * @param orderVo
	 * @param condition
	 */
	public void calcuPrice(OrderVo orderVo , PromoteCondition condition);

}
