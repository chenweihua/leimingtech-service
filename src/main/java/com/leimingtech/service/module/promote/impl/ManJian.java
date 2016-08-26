package com.leimingtech.service.module.promote.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.leimingtech.core.common.SpringContextUtil;
import com.leimingtech.core.entity.base.ShopPMansongRule;
import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.mansongrule.service.ShopPMansongRuleService;
import com.leimingtech.service.module.promote.IPromote;
import com.leimingtech.service.module.promote.common.PromoteCondition;

/**
 * 满免减
 * @author linjianmao
 */
public class ManJian implements IPromote {
	
	private ShopPMansongRuleService mansongRuleService = SpringContextUtil.getBean(ShopPMansongRuleService.class);
	
	@Override
	public void calculate(OrderVo orderVo, PromoteCondition condition) {
		ShopPMansongRule manSongRule = mansongRuleService.findCurrSingleRule(orderVo.getStoreId(), orderVo.getGoodsAmount());
		if(manSongRule==null){
			orderVo.setPromoPrice(BigDecimal.valueOf(0.00));
		}else{
			orderVo.setPromoPrice(manSongRule.getDiscount());
		}
	}

}
