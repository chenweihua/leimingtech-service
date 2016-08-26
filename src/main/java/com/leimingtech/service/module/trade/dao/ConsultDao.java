package com.leimingtech.service.module.trade.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Consult;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
public interface ConsultDao{

    /**
     * 查询条数
     * @param pager
     * @return
     */
    int findCount(Consult consult);

    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<Consult> findList(Pager pager);

    /**
     * 删除
     * @param id
     */
    void delete(int id);
    
    void updateReply(Consult consult);

    Consult findById(int id);
    
    public void save(Consult consult);
    
    
    
    /**
     * 查询条数
     * @param pager
     * @return
     */
    int findMemberCount(Consult consult);

    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<Consult> findMemberList(Pager pager);
    
    /**
     * 根据id获取对象
     * @param consultId
     * @return
     */
    Consult findById(Integer consultId);
}
