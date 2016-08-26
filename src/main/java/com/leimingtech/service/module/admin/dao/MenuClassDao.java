package com.leimingtech.service.module.admin.dao;


import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.MenuClass;
import com.leimingtech.core.entity.vo.MenuClassVo;

public interface MenuClassDao {

    /**
     * 保存分类
     * @param menuClass
     */
    void save(MenuClass menuClass);
    /**
     * 修改分类
     * @param menuClass
     */
    void update(MenuClass menuClass);
    /**
     * 删除
     * @param mid
     */
    void delete(Integer mid);
    /**
     * 通过id查询分类
     * @param mid
     * @return
     */
    MenuClass findById(Map pramap);
    /**
     * 查询一级分类
     * @return
     */
    List<MenuClassVo> findPageList();
    /**
     * 查询出所有级别的分类
     * @return
     */
    List<MenuClass> findList(Integer isshow);
    /**
     * 根据不同条件查询条数，页面验证用
     * @param goodsClass
     * @return
     */
    int findCount(MenuClass menuClass);
    /**
     * 查询子列表
     * @param id
     * @return
     */
    List<MenuClassVo> findChildList(int id);
    
    List<MenuClassVo> findChildListmap(Map pramap);

    /**
     * 修改子类分类
     * @param goodsClass
     */
    void updateChildType(MenuClass menuClass);

    /**
     * 递归查询所有
     * @return
     */
    List<MenuClass> findAll();

    /**
     * 查询下级分类
     * @param id
     * @return
     */
    List<MenuClass> findChild(int id);
    
    /**
     * 根据mparentid查询
     * @param id
     * @return
     */
    int findbyparentid(int mparentid);
    /**
     * 根据mid查询
     * @param menuClass
     * @return
     */
    int findparentidCount(int mid);
    
    /**
     * 根据角色ID获取菜单
     * @param 要查询的角色ids，例如：1,2,3
     * @return
     */
    List<MenuClass> findByRoleids(String roleids,Integer isshow);
}
