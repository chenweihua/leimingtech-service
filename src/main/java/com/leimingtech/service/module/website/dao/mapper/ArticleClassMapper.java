package com.leimingtech.service.module.website.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.ArticleClass;
import com.leimingtech.core.entity.ArticleClassVo;

/**
 * Created by rabook on 2014/11/4.
 */

@SqlMapper
public interface ArticleClassMapper {

	void save(ArticleClass articleClass);

    void update(ArticleClass articleClass);

    void delete(int id);

    ArticleClass findById(int id);

    List<ArticleClass> findList();

    List<ArticleClassVo> findPageList();

    int findCount();

    List<ArticleClassVo> findChildren(int id);

    List<ArticleClass> findAllList();

    int duplicate(ArticleClass articleClass);
    
    List<ArticleClassVo> findArticleList(ArticleClassVo articleClassVo);

}
