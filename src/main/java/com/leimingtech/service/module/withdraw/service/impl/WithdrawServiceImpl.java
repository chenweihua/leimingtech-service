package com.leimingtech.service.module.withdraw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Withdraw;
import com.leimingtech.service.module.withdraw.dao.WithdrawDao;
import com.leimingtech.service.module.withdraw.service.WithdrawService;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：WithdrawEntityServiceImpl   
 * 类描述：文档实体管理
 * 创建人：lkang   
 * 创建时间：2015年5月04日 10:52:19   
 */
@Service("withdrawService")
public class WithdrawServiceImpl implements WithdrawService {

	@Autowired
	private WithdrawDao withdrawDao;
	/**
	 * 获取引用实体总数
	 * @param pager
	 * @return
	 */
	public int getWithdrawTotal(Withdraw withdraw) {
		return withdrawDao.getWithdrawTotal(withdraw);
	}

	/**
	 * 获取引用实体分页数据
	 * @param pager
	 * @return
	 */
	public List<Withdraw> getWithdrawList(Pager pager) {
		return withdrawDao.getWithdrawList(pager);
	}

	/**
	 * 根据id获取引用实体
	 * @param entityId
	 * @return
	 */
	public Withdraw getWithdrawById(int entityId) {
		return withdrawDao.getWithdrawById(entityId);
	}

	/**
	 * 保存引用实体
	 * @param withdraw
	 */
	public void saveWithdraw(Withdraw withdraw) {
		withdraw.setCreateTime(System.currentTimeMillis());
		withdrawDao.saveWithdraw(withdraw);
	}

	/**
	 * 修改引用实体
	 * @param withdraw
	 */
	public void updateWithdraw(Withdraw withdraw) {
		withdraw.setUpdateTime(System.currentTimeMillis());
		withdrawDao.updateWithdraw(withdraw);
	}

	/**
	 *根据id删除引用实体
	 * @param entityId
	 */
	public void deleteWithdraw(int entityId) {
		withdrawDao.deleteWithdraw(entityId);
	}

	/**
	 * 获取所有的实体
	 * @return
	 */
	public List<Withdraw> getAllWithdrawList() {
		return withdrawDao.getAllWithdrawList();
	}

	/**
	 * 根据名字获取引用实体
	 * @param entityName
	 * @return
	 */
	public Withdraw getWithdrawByName(String entityName) {
		return withdrawDao.getWithdrawByName(entityName);
	}
	

}
