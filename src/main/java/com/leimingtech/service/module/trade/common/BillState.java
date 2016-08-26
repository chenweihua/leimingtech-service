package com.leimingtech.service.module.trade.common;

/**
 * 结算相关状态
 * @author liukai
 */
public final class BillState {
	
	/**
	 * 结算状态:默认
	 */
	public final static int OB_STATE_DEFAULT = 10;
	
	/**
	 * 结算状态:店家已确认
	 */
	public final static int OB_STATE_SELLER_CONFIRM = 20;
	
	/**
	 * 结算状态:平台已审核
	 */
	public final static int OB_STATE_ADMIN_AUDITED= 30;
	
	/**
	 * 结算状态:结算完成
	 */
	public final static int OB_STATE_FINISH = 40;
}
