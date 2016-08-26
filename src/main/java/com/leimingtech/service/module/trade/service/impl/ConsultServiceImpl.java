package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Consult;
import com.leimingtech.service.module.trade.dao.ConsultDao;
import com.leimingtech.service.module.trade.service.ConsultService;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
@Service
public class ConsultServiceImpl implements ConsultService{

    @Resource
    private ConsultDao consultDao;
    /**
     * 查询条数
     *
     * @param pager
     * @return
     */
    @Override
    public int findCount(Consult consult) {
        return consultDao.findCount(consult);
    }

    /**
     * 分页列表
     *
     * @param pager
     * @return
     */
    @Override
    public List<Consult> findList(Pager pager) {
        return consultDao.findList(pager);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        consultDao.delete(id);
    }
    
    @Override
    public void updateReply(Consult consult) {
        consultDao.updateReply(consult);
    }

    @Override
    public Consult findById(int id) {
        return consultDao.findById(id);
    }

	@Override
	public void save(Consult consult) {
		long nowTime = System.currentTimeMillis();
		consult.setCreateTime(nowTime);
		consult.setConsultAddtime(nowTime);
		consultDao.save(consult);
	}

	@Override
	public int findMemberCount(Consult consult) {
		
		return consultDao.findMemberCount(consult);
	}

	@Override
	public List<Consult> findMemberList(Pager pager) {
		
		return consultDao.findMemberList(pager);
	}

	@Override
	public Consult findById(Integer consultId) {
		return consultDao.findById(consultId);
	}
    
}
