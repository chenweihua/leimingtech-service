package com.leimingtech.service.module.website.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.leimingtech.core.entity.base.Article;
import com.leimingtech.service.module.website.dao.ArticleDao;
import com.leimingtech.service.module.website.service.ArticleService;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/11/9.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;
    @Override
    public void save(Article article) {
    	article.setCreateTime(System.currentTimeMillis());
        articleDao.save(article);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public void delete(int id) {
        articleDao.delete(id);
    }

    @Override
    public Article findById(int id) {
        return articleDao.findById(id);
    }

    @Override
    public List<Article> findList() {
        return articleDao.findList();
    }

    @Override
    public List<Article> findListForPage(Pager pager, Article article) {

        Map<String,Object> map= Maps.newHashMap();
        map.put("page",pager);
        map.put("article",article);
        return articleDao.findPageList(map);
    }

    @Override
    public int findCount(Article article) {
        return articleDao.findCount(article);
    }

	@Override
	public List<Article> findListByArticle(Article article) {
		return articleDao.findListByArticle(article);
	}
}
