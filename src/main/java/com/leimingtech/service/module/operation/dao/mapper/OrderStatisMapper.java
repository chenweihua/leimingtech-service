package com.leimingtech.service.module.operation.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.OrderStatis;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算管理
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：OrderStatisMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月14日 上午12:06:52   
 * 修改人：liuhao   
 * 修改时间：2014年11月14日 上午12:06:52   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface OrderStatisMapper {

	
	/**
	 * 
	 * @Title: countOrderStatis 
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
    public int countOrderStatis(Pager pager);
    
    /**
     * 
     * @Title: queryOrderStatisList 
     * @Description: TODO(带分页list 查询) 
     * @param @param pager
     * @param @return    设定文件 
     * @return List<AdminLog>    返回类型 
     * @throws
     */
    public List<OrderStatis> queryOrderStatisList(Pager pager);
    
}
