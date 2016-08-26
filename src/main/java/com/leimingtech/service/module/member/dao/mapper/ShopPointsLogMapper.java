package com.leimingtech.service.module.member.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.ShopPointsLog;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：ShopPointsLogMapper
 * @类描述：
 * @创建人：gyh
 * @创建时间：2015年7月24日 下午5:22:15
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface ShopPointsLogMapper {
	/**
	 * 保存会员积分记录
	 * @param shopStatGoods
	 */
	void save(ShopPointsLog shopPointsLog);
	/**
	 * 获取条数
	 * @param shopPointsLog
	 * @return
	 */
	int findCount(ShopPointsLog shopPointsLog);
	
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<ShopPointsLog> findPageList(Pager pager);

	/**
	 * 获取全部数据
	 * @return
	 */
	List<ShopPointsLog> findList();
    
}
