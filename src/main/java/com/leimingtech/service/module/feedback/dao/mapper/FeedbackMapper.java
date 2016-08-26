/**
 * 
 */
package com.leimingtech.service.module.feedback.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.Feedback;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>Title: FeedbackMapper.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年8月25日
 * @version 1.0
 */

@SqlMapper
public interface FeedbackMapper {
	
	/**
	 * 保存
	 */
	void save(Feedback feedback);
	/**
	 * 删除
	 * @param id
	 */
	void delete(int id);
	/**
	 * 修改
	 * @param feedback
	 */
	void update(Feedback feedback);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	Feedback findbyId(int id);
	/**
	 *  获取集合
	 * @return
	 */
	List<Feedback> findBylist();
	/**
	 * 分页集合
	 * @param pager
	 * @return
	 */
	List<Feedback> findPageList(Pager pager);
	/**
	 * 总条数
	 * @param feedback
	 * @return
	 */
	int findCount(Feedback feedback);

}
