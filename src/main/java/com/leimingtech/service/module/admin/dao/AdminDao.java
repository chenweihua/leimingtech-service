package com.leimingtech.service.module.admin.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.Admin;
import com.leimingtech.core.entity.vo.AdminVo;
import com.leimingtech.service.utils.page.Pager;


/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：AdminDao   
 * 类描述：接口
 * 修改备注：   
 * @version    
 *
 */
public interface AdminDao {
    
	/**
     * 根据登录名查找用户
     * @return
     */
    Admin findByAdminName(String adminName);
    
	public int findAdminCount(Admin admin);
    
    public List<AdminVo> findAdminList(Pager pager);
    
    public Admin findAdminById(@Param("id") Integer id);
    
    public void save(Admin admin);
    
    public void update(Admin admin);
    
    public void delete(Integer id);
    
}