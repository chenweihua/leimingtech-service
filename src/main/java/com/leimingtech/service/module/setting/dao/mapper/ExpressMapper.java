package com.leimingtech.service.module.setting.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.Express;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

@SqlMapper
public interface ExpressMapper {

	public int findExpressCount(Express express);

	public List<Express> findExpressList(Pager pager);

	public void updateState(Express express);

	public void updateOrder(Express express);

	public void delete(@Param("id") Long id);
	
	public List<Express> findList();
	
	/**
	 * 根据显示状态和是否常用状态查询物流公司信息
	 * @param eState
	 * @param eOrder
	 * @return
	 */
	public List<Express> findExpressListByState(@Param("eState") Integer eState,@Param("eOrder") Integer eOrder);
	
	public Express findById(Integer id);

}