package com.leimingtech.service.module.withdraw.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Withdraw;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.withdraw.dao.WithdrawDao;
import com.leimingtech.service.module.withdraw.dao.mapper.WithdrawMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：WithdrawServiceImpl   
 * 类描述：提现实体管理
 */
@Service("withdrawDao")
public class WithdrawDaoImpl extends BaseDao implements WithdrawDao {

	@Autowired
	private WithdrawMapper withdrawMapper;
	/**
	 * 获取引用实体总数
	 * @param pager
	 * @return
	 */
	public int getWithdrawTotal(Withdraw withdraw) {
		return withdrawMapper.getWithdrawTotal(withdraw);
	}

	/**
	 * 获取引用实体分页数据
	 * @param pager
	 * @return
	 */
	public List<Withdraw> getWithdrawList(Pager pager) {
		return withdrawMapper.getWithdrawList(pager);
	}

	/**
	 * 根据id获取引用实体
	 * @param entityId
	 * @return
	 */
	public Withdraw getWithdrawById(int entityId) {
		return withdrawMapper.getWithdrawById(entityId);
	}

	/**
	 * 保存引用实体
	 * @param withdraw
	 */
	public void saveWithdraw(Withdraw withdraw) {
		withdrawMapper.saveWithdraw(withdraw);
	}

	/**
	 * 修改引用实体
	 * @param withdraw
	 */
	public void updateWithdraw(Withdraw withdraw) {
		withdrawMapper.updateWithdraw(withdraw);
	}

	/**
	 *根据id删除引用实体
	 * @param entityId
	 */
	public void deleteWithdraw(int entityId) {
		withdrawMapper.deleteWithdraw(entityId);
	}

	/**
	 * 获取所有的实体
	 * @return
	 */
	public List<Withdraw> getAllWithdrawList() {
		return withdrawMapper.getAllWithdrawList();
	}

	/**
	 * 根据名字获取引用实体
	 * @param entityName
	 * @return
	 */
	public Withdraw getWithdrawByName(String entityName) {
		return withdrawMapper.getWithdrawByName(entityName);
	}
	
	

}
