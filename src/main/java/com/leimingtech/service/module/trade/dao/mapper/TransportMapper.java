package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.Transport;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：TransportMapper
 * @类描述： 运费模板
 * @创建人：shining
 * @创建时间：2014年12月7日 下午10:48:46
 * @修改人：shining
 * @修改时间：2014年12月7日 下午10:48:46
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface TransportMapper {
	/**
	 * 
	 * @Title: queryList
	 * @Description: 查询列表
	 * @param @param pager
	 * @param @return 设定文件
	 * @return List<Transport> 返回类型
	 * @throws
	 */
	public List<Transport> queryList(Pager pager);

	public Transport findById(Integer id);

	public void save(Transport transport);

	public void delete(Integer id);
	
	public void update(Transport transport);
	
	/**
	 * 通过店铺id 获得当前的默认运费模板
	 * @param storeId
	 * @return
	 */
	public Transport getDefTransportByStoreId(Integer storeId);
}
