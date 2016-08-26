package com.leimingtech.service.module.website.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.ArticleClass;
import com.leimingtech.core.entity.ArticleClassVo;
import com.leimingtech.service.module.website.dao.ArticleClassDao;
import com.leimingtech.service.module.website.dao.mapper.ArticleClassMapper;

/**
 * Created by ss on 2014/11/4.
 */
@Repository
public class ArticleClassDaoImpl implements ArticleClassDao{

	@Resource
    private ArticleClassMapper articleClassMapper;
    @Override
    public void save(ArticleClass articleClass) {
        articleClassMapper.save(articleClass);
    }

    @Override
    public void update(ArticleClass articleClass) {
        articleClassMapper.update(articleClass);
    }

    @Override
    public void delete(int id) {
        articleClassMapper.delete(id);
    }

    @Override
    public ArticleClass findById(int id) {
        return articleClassMapper.findById(id);
    }

    @Override
    public List<ArticleClass> findList() {
        return articleClassMapper.findList();
    }

    @Override
    public List<ArticleClassVo> hasChildren(int id) {
        return articleClassMapper.findChildren(id);
    }

    @Override
    public int findCount() {
        return articleClassMapper.findCount();
    }

    @Override
    public List<ArticleClassVo> findForPage() {
        return articleClassMapper.findPageList();
    }

    @Override
    public List<ArticleClass> findAllList() {
        return articleClassMapper.findAllList();
    }

    @Override
    public int duplicate(ArticleClass articleClass) {
        return articleClassMapper.duplicate(articleClass);
    }

	@Override
	public List<ArticleClassVo> findList(ArticleClassVo articleClass) {
		return articleClassMapper.findArticleList(articleClass);
	}
    
    
}
