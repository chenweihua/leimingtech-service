package com.leimingtech.service.module.dictionary.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.base.Dictionary;
import com.leimingtech.service.utils.page.Pager;

@SqlMapper
public interface DictionaryMapper {

	void save(Dictionary dictionary);
	void update(Dictionary dictionary);
	void delete(Integer dictionaryId);
	/**
	 * 根据字典id查询字典实体
	 * @param dictionaryId
	 * @return
	 */
	Dictionary findByDictionaryId(Integer dictionaryId);
	/**
	 * 总数查询
	 * @param pager
	 * @return
	 */
	public int countDictionaryidList(Dictionary dictionary);
	/**
	 * 分页列表
	 * @param pager
	 * @return
	 */
	public List<Dictionary> queryDictionaryidList(Pager pager);

	public List<Dictionary> findDictionaryByCode(String groupCode);
	
	public Dictionary findDictionaryByDictionaryId(Integer dictionaryId);
	
	public void updateAllDictionaryCodeByGroupId(Dictionary dictionary);

}
