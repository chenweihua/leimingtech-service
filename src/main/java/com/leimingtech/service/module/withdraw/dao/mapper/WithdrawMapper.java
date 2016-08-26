package com.leimingtech.service.module.withdraw.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.Withdraw;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：WithdrawMapper   
 * 类描述：提现实体管理
 */
@SqlMapper
public interface WithdrawMapper {
	/**
	 * 获取引用实体总数
	 * @param pager
	 * @return
	 */
	int getWithdrawTotal(Withdraw withdraw);
	
	/**
	 * 获取引用实体分页数据
	 * @param pager
	 * @return
	 */
	List<Withdraw> getWithdrawList(Pager pager);
	
	/**
	 * 根据id获取引用实体
	 * @param entityId
	 * @return
	 */
	Withdraw getWithdrawById(int entityId);
	
	/**
	 * 保存引用实体
	 * @param withdraw
	 */
	void saveWithdraw(Withdraw withdraw);
	
	/**
	 * 修改引用实体
	 * @param withdraw
	 */
	void updateWithdraw(Withdraw withdraw);
	
	/**
	 *根据id删除引用实体
	 * @param entityId
	 */
	void deleteWithdraw(int entityId);
	
	/**
	 * 获取所有的实体
	 * @return
	 */
	List<Withdraw> getAllWithdrawList();
	
	/**
	 * 根据名字获取引用实体
	 * @param entityName
	 * @return
	 */
	Withdraw getWithdrawByName(String entityName);
}
