package com.leimingtech.service.utils.sessionkey;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.leimingtech.core.common.Constants;
import com.leimingtech.core.common.SpringContextUtil;
import com.leimingtech.core.entity.base.Admin;
import com.leimingtech.service.module.admin.service.AdminService;


/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：CacheUser
 * 类描述：用户信息
 * 创建人：lkang   
 * 创建时间：2015年5月04日 03:00:00   
 */
public class CacheUtil {
	
	/**
     * 获取SessionUser
     * @return
     */
	public static CacheUser getCacheUser() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		if (null != session) {
            CacheUser cacheUser;//= initCacheUser("front")
            Subject currentUser = SecurityUtils.getSubject();
            cacheUser = (CacheUser) session.getAttribute(Constants.ADMIN_SESSION_KEY);
            if (cacheUser == null) {
                cacheUser = CacheUtil.initCacheUser(currentUser.getPrincipal().toString());
            }
            return cacheUser;
        } else {

            return null;
        }
	}
	
	/**
     * 初始化CacheUser对象
     *
     * @param memberName
     * @return
     */
    public static CacheUser initCacheUser(String adminName) {

        AdminService adminService = SpringContextUtil.getBean(AdminService.class);
        Admin admin = adminService.findByAdminName(adminName);
        CacheUser cacheUser = new CacheUser();
        cacheUser.setAdmin(admin);

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        if (null != session) {
            session.setAttribute(Constants.ADMIN_SESSION_KEY, cacheUser);
        } else {
            throw new RuntimeException("CacheUser初始化失败");
        }
        return cacheUser;
    }
	
	
}
