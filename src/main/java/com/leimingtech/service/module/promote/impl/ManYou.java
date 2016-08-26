package com.leimingtech.service.module.promote.impl;

import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.promote.IPromote;
import com.leimingtech.service.module.promote.common.PromoteCondition;
/**
 * 满免邮
 * @author linjianmao
 *
 */
public class ManYou implements IPromote {

	@Override
	public void calculate(OrderVo orderVo, PromoteCondition condition) {
		//计算自己优惠的逻辑
	}

}
