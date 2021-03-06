package com.leimingtech.service.module.goods.service;

import java.util.List;

import com.leimingtech.core.entity.base.Spec;
import com.leimingtech.core.entity.base.SpecValue;
import com.leimingtech.core.entity.vo.SpecVo;
import com.leimingtech.service.utils.page.Pager;

public interface SpecService {
	
    /**
     * 保存
     * @param Spec
     */
    void save(Spec spec, String specValues);
    
    /**
     * 修改
     * @param Spec
     */
    void update(Spec spec, String specValues);
    
    /**
     * 修改
     * @param Spec
     */
    void update(Spec spec);

	public Spec findById(Integer spId);
	
    public List<Spec> findAllList(Spec spec);

    public List<Spec> findPageList(Pager pager);
    
    public Integer findPageListCount(Pager pager);
    
    public List<Spec> findListBySpId(Integer spId);

    public List<SpecVo> findSpecListBySpId(Integer spId);
    
    public List<SpecVo> findListByType(Integer typeId);
    
    public void deleteSpecBySpId(Integer spId);

	public void insert(SpecValue specValue);
}
