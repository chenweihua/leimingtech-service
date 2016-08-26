package com.leimingtech.service.module.test.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Account;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.test.dao.TestDao;
import com.leimingtech.service.module.test.dao.mapper.TestMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * @author LH
 *
 */
@Service("testDao")
public class TestDaoImpl extends BaseDao implements TestDao {
    @Autowired
    private TestMapper testMapper;
   
    /**
     * 查询总数
     */
    public int findAcctCount(Pager pager) {
        return testMapper.findAcctCount(pager);
    }

    /**
     * 获取结果集
     */
    public List<Account> findAcctList(Pager pager) {
        return testMapper.findAcctList(pager);
    }

	@Override
	public Account findAcctById(Long id) {
		// TODO Auto-generated method stub
		return testMapper.findAcctById(id);
	}

	@Override
	public void save(Account account) {
		// TODO Auto-generated method stub
		testMapper.save(account);
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		testMapper.update(account);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		testMapper.delete(id);
	}

}
