/**
 * 
 */
package com.leimingtech.service.module.promote.context;

import com.leimingtech.core.common.NumberUtils;
import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.promote.IPromote;
import com.leimingtech.service.module.promote.common.PromoteCondition;

/**
 * <p>Description: 促销上下文</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
public class PromoteContext {
	
	private IPromote promote;
	
	
	public void setPromote(IPromote promote){
		this.promote  = promote;
	}
	
	public Double calculate(OrderVo orderVo , PromoteCondition condition){
		this.promote.calculate(orderVo, condition);
		//NumberUtils.round(v, scale)
		return null;
	}

}
