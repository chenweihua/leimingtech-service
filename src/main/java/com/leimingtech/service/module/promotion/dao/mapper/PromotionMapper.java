/**
 * 
 */
package com.leimingtech.service.module.promotion.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.Promotion;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>Title: PromotionMapper.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@SqlMapper
public interface PromotionMapper {
	
	
	/**
	 * 保存
	 * @param promotion
	 */
	public void save(Promotion promotion);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 更新
	 * @param promotion
	 */
	public void update(Promotion promotion);
	
	/**
	 * 获取列表
	 * @param pager
	 * @return
	 */
	public List<Promotion> findList(Pager pager);
	
	/**
	 * 获取总条数
	 * @param Promotion
	 * @return
	 */
	public int findCount(Promotion promotion);
	
	/**
	 * 单条纪录
	 * @param pcId
	 * @return
	 */
	public Promotion findById(Integer pcId);
	
	/**
	 * 根据类型id获取促销集合
	 * @param pcId
	 * @return
	 */
	public List<Promotion> fingBypcId(Integer pCId);

}
