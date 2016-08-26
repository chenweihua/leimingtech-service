package com.leimingtech.service.module.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.common.Digests;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.MemberGrade;
import com.leimingtech.core.entity.base.ShopPointsLog;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.member.common.PointsLogType;
import com.leimingtech.service.module.member.dao.MemberDao;
import com.leimingtech.service.module.member.service.MemberGradeService;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.member.service.ShopPointsLogService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：MemberServiceImpl   
 * 类描述：service实现类
 * 修改备注：   
 * @version    
 *
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
    
	@Resource
	private MemberGradeService memberGradeService;
	
	@Resource
	private SettingService settingService;
	
	@Resource
	private ShopPointsLogService shopPointsLogService;
	

	/**
	 * 
	 * @Title: findMemberList
	 * @Description: TODO (查询所有的会员信息，用分页显示)
	 * @param @param pager
	 * @param @return    设定文件
	 * @return List<Account>    返回类型
	 * @throws
	 */
	public List<Member> findMemberList(Pager pager) {
		return memberDao.findMemberList(pager);
	}

	/**
     * 保存member信息
     * @param member
     */
	@Override
	public void save(Member member) {
		String code = DateUtils.getRandomString(8);
		member.setSignCode(code);
        member.setSignCodeState(0);
        member.setMemberState(1);//会员的开启状态 1为开启 0为关闭
        //member.setMemberTruename("");//会员真实姓名
        if(StringUtils.isEmpty(member.getMemberAvatar())){
           member.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
        }
        member.setMemberPasswd(Digests.entryptPassword(member.getMemberPasswd()));
        member.setCreateTime(System.currentTimeMillis());//会员创建时间
        
        
        //获取成功注册等级积分
		String rankSettingPoints = settingService.findByNameAndCode("points", "register_rank");
		//获取成功注册消费积分
		String consSettingPoints = settingService.findByNameAndCode("points", "register_cons");
		if(!StringUtils.isNotBlank(rankSettingPoints)){
			rankSettingPoints = "0";
		}
		member.setMemberRankPoints(Integer.valueOf(rankSettingPoints));
		if(!StringUtils.isNotBlank(consSettingPoints)){
			consSettingPoints = "0";
		}
		member.setMemberConsumePoints(Integer.valueOf(consSettingPoints));
        MemberGrade memberGrade=memberGradeService.findDefaultGrade();
        if(memberGrade!=null){
        	 member.setMemberGradeId(memberGrade.getGradeId());//默认会员等级
        }
		memberDao.save(member);
		
		/**
		 * 积分日志
		 */
		ShopPointsLog shopPointsLog = new ShopPointsLog();
		shopPointsLog.setMemberid(member.getMemberId());
		shopPointsLog.setMembername(member.getMemberName());
		shopPointsLog.setAdminid(1);
		shopPointsLog.setAdminname("admin");
		shopPointsLog.setPoints(Integer.valueOf(consSettingPoints));
		shopPointsLog.setCreateTime(System.currentTimeMillis());
		shopPointsLog.setType(PointsLogType.POINTS_TYPE_REGISTER); //积分操作类型
		shopPointsLog.setDesc("注册成功");
		shopPointsLog.setStage("注册完成,增加会员积分");
		//保存会员积分日志表
		shopPointsLogService.save(shopPointsLog);
		
//		ObjectNode node = EasemobIMUsers.createNewIMUserSingle(member.getMemberName(), "lmshopb2b2c");
//		if (null != node) {
//            System.out.println("EASEMOBIMUSERS-注册IM用户[单个]: " + node.toString());
//        }else{
//        	System.out.println("EASEMOBIMUSERS-注册IM用户[单个]:失败");
//        }
	}
	/**
     * 修改member信息
     * @param member
     */
	@Override
	public void update(Member member) {
		memberDao.update(member);
	}
    
	/**
	 * 
	 * @Title: delete
	 * @Description: TODO (删除)
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void delete(Long id) {
		memberDao.delete(id);
	}

    /**
     * 根据会员id获取会员信息
     * @param memberId
     * @return
     */
	@Override
	public Member findById(Integer memberId) {
		return memberDao.findById(memberId);
	}
    
	/**
     * 根据会员名查询会员信息
     * @param memberName
     * @return
     */
	@Override
	public Member findMemberByName(String memberName) {
		return memberDao.findMemberByName(memberName);
	}
    
	/**
    * 根据Member修改信息
    * @param member
    */
   public void updateMember(Member member){
	   if(StringUtils.isNotEmpty(member.getMemberPasswd())){
		   member.setMemberPasswd(Digests.entryptPassword(member.getMemberPasswd()));//修改密码  
	   }
	   memberDao.updateMember(member);
   }

	@Override
	public Member findMemberById(Integer id) {
		return memberDao.findMemberById(id);
	}


	@Override
	public int updatePass(String newPasswd, Integer memberId) {
		//Member member =  memberDao.findMemberById(memberId);
		Member member=new Member();
		member.setMemberId(memberId);
		member.setMemberPasswd(newPasswd);
		try {
			 member.setMemberPasswd(Digests.entryptPassword(newPasswd));
			 memberDao.updateMember(member);
			return 1;
		} catch (Exception e) {
			System.out.println("更新密码失败"+e.getMessage());
			return 0;
		}
	}

	@Override
	public int updateMember(String data) {
		int result = 0 ;
		try {
			Member member = JsonUtils.fromJson(data, Member.class);
			if(member.getMemberId()!=null){
				memberDao.updateMember(member);
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("保存失败"+e.getMessage());
		}
		return result;
	}
	
   /**
    * 根据memberId修改密码
    * @param newPasswd
    * @param memberId
    */
	@Override
	public int updatePass(String memberPasswd,String newPasswd,Integer memberId){
		 Member member =  memberDao.findMemberById(memberId);
		 //判断原密码是否正确
		 if(!Digests.validatePassword(String.valueOf(memberPasswd), member.getMemberPasswd())){
			 return 2;
		 }else{
			 try {
				 member.setMemberPasswd(Digests.entryptPassword(newPasswd).toString());
				 memberDao.updateMember(member);
				return 1;
			} catch (Exception e) {
				System.out.println("更新密码失败"+e.getMessage());
				return 0;
			}
		 }
	 }
    
  /**
    * 修改头像
    * @param path
    * @param memberId
    * @return
    */
	@Override
	public int updateFace(String path, Integer memberId) {
		//Member member =  memberDao.findMemberById(memberId);
		Member member=new Member();
		member.setMemberId(memberId);
		try {
			member.setMemberAvatar(path);
			memberDao.updateMember(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("更新密码失败"+e.getMessage());
			System.out.println("更新头像失败"+e.getMessage());
			return 0;
		}
	}
    
   /**
    * 根据email查询会员
    * @param email
    * @return
    */
	@Override
	public Member findMemberByEmail(String email) {
		return memberDao.findMemberByEmail(email);
	}
    
  /**
    * 根据memberMobile查询member表
    * @param memberMobile
    * @return
    */
	@Override
	public Member findMemberByMobile(String memberMobile) {
		return memberDao.findMemberByMobile(memberMobile);
	}
    
   /**
    * 仅仅修改当前登陆人的
    * @param memberMobile
    * @return
    */
	@Override
	public void updateweiMember(Integer memberId) {
		Member member=memberDao.findById(memberId);
		Member member9=new Member();
		if(member!=null){
			member9.setMemberId(member.getMemberId());
			member9.setMemberOldLoginTime(member.getMemberLoginTime());//上次登陆时间
			member9.setMemberLoginTime(System.currentTimeMillis());//最后登陆时间
			member9.setMemberLoginNum(1);//登陆次数
			memberDao.updateMember(member9);
		}
	}

	
	/**
	 * 获取总记录数
	 * @param member
	 * @return
	 */
	public int findMemberCount(Member member) {
		return memberDao.findMemberCount(member);
	}
	/**
     * 根据会员信息查找
     * @param
     * @return
     */
	@Override
	public Member findMember(Member member) {
		return memberDao.findMember(member);
	}
    
	/**
     * 根据memberphone查询member表
     * @param memberPhone
     * @return
     */
	@Override
	public int findMemberCountByMobile(String memberMobile) {
		return memberDao.findMemberCountByMobile(memberMobile);
	}
}
