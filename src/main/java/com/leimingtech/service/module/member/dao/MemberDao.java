package com.leimingtech.service.module.member.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.Member;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：MemberDao   
 * 类描述：接口
 * 修改备注：   
 * @version    
 *
 */
public interface MemberDao {
    
    
    public List<Member> findMemberList(Pager pager);
    
    
    public void save(Member member);
    
    public void update(Member member);
    
    public void delete(Long id);

    
    Member findById(Integer memberId);

    /**
     * 根据会员名查询会员信息
     * @param memberName
     * @return
     */
    public Member findMemberByName(String memberName);
    
    /**
     * 根据Member修改信息
     * @param member
     */
    public void updateMember(Member member);
    
    /**
     * 根据会员id查询会员信息
     * @param id
     * @return
     */
    public Member findMemberById(Integer id);
    
    Member findMemberByEmail(String memberEmail);
    
    Member findMemberByMobile(String memberMobile);
    
    /**
   	 * 获取总记录数
   	 * @return
   	 */
   	public int findMemberCount(Member member);
   	
    /**
     * 根据会员信息查找
     * @param
     * @return
     */
    Member findMember(Member member);
    /**
     * 根据memberphone查询member表
     * @param memberPhone
     * @return
     */
    public int findMemberCountByMobile(@Param("memberMobile") String memberMobile);
}
