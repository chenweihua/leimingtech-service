package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.leimingtech.core.cache.jedis.JedisConfig;
import com.leimingtech.core.cache.jedis.JedisUtils;
import com.leimingtech.core.entity.GoodsClass;
import com.leimingtech.service.module.goods.dao.GoodsClassDao;
import com.leimingtech.service.module.goods.service.GoodsClassService;

/**
 * 商品分类接口实现
 * @author lkang
 *
 */
@Slf4j
@Service
public class GoodsClassServiceImpl implements GoodsClassService{
	
	@Resource
    private GoodsClassDao goodsClassDao;

	/**
     * 保存分类
     * @param goodsClass
     */
	public void save(GoodsClass goodsClass) {
		goodsClassDao.save(goodsClass);
	}

	/**
     * 修改分类
     * @param goodsClass
     */
	public void update(GoodsClass goodsClass) {
        goodsClassDao.update(goodsClass);
        if(goodsClass.getIsRelate()!=null && goodsClass.getIsRelate()==1){//是否关联子分类 0否, 1是
        	//修改子分类佣金比例类型
        	GoodsClass gclass=new GoodsClass();
        	gclass.setGcParentId(goodsClass.getGcId());
        	gclass.setExpenScale(goodsClass.getExpenScale());//佣金比例
        	goodsClassDao.updatebyparentid(gclass);
        }
      //更新redis缓存
      updateDicCache();
	}

	/**
     * 删除
     * @param id
     */
	public void delete(Integer id) {
		goodsClassDao.delete(id);
		//更新redis缓存
		delDicCache();
	}
	
	/**
     * 通过id查询分类
     * @param gcId
     * @return
     */
	public GoodsClass findById(Integer gcId) {
		return goodsClassDao.findById(gcId);
	}

	/**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
	public List<GoodsClass> findList(int parentid) {
		return goodsClassDao.findList(parentid);
	}
	
	/**
     * 查询所有的分类
     * @return
     */
	public List<GoodsClass> findAll() {
		return goodsClassDao.findAll();
	}

	/**
     * 根据不同条件查询条数，页面验证用
     * @param goodsClass
     * @return
     */
	public int findCount(GoodsClass goodsClass) {
		return goodsClassDao.findCount(goodsClass);
	}
   
	/**
     * 根据父goodsClass查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
	@Override
	public List<GoodsClass> findListbyishow(GoodsClass goodsClass) {
		return goodsClassDao.findListbyishow(goodsClass);
	}
    
	/**
     * 查询所有的分类
     * @return
     */
	@Override
	public List<GoodsClass> findAllbyisshow(GoodsClass goodsClass) {
		List<GoodsClass> gclzs = null;
		if(JedisConfig.JEDIS_STATUS){
		////需要安装redis
			Object obj = JedisUtils.getObject(JedisConfig.GCLZ_PREFIX + "goodsClass");
			if(obj == null){
				gclzs = goodsClassDao.findAllbyisshow(goodsClass);
				//10分钟
				JedisUtils.setObject(JedisConfig.GCLZ_PREFIX + "goodsClass", gclzs, JedisConfig.JEDIS_EXPIRE);
				log.debug(JedisConfig.GCLZ_PREFIX + "存入redis");
			}else{
				gclzs = (List<GoodsClass>)obj;
				log.debug(JedisConfig.GCLZ_PREFIX + "转化成功");
			}
		}else{
			gclzs = goodsClassDao.findAllbyisshow(goodsClass);
		}
		return gclzs;
	}
	
	/**
     * 商品分类api中设置一级分类下的gcLastChild
     * @param classList
     * @return
     */
    public List<GoodsClass> setApiGcLastChild(List<GoodsClass> classList){
    	for(GoodsClass goodsClass:classList){ //遍历第一级分类
    		List<GoodsClass> list = goodsClassDao.findChild(goodsClass.getGcId());
			//新建一个字符串,存储每个分类第三级分类下的第一个分类的名称
			String classStr = "";
			//判断一级分类下有没有二级分类
			if(list!=null){ 
				for(GoodsClass secondClass:list){
					//判断二级分类下有没有三级分类
					if(secondClass.getClassList()!=null){ 
						for(int i=0;i<secondClass.getClassList().size();i++){
							if(i==0){
								classStr += secondClass.getClassList().get(i).getGcName() + "";
							}
						}
					}
				}
			}
			goodsClass.setGcLastChild(classStr);
		}
    	return classList;
    }
    
    
    /**
     * 通过父id查询子分类
     * @param gcParentId
     * @return
     */
    public List<GoodsClass> findChild(Integer gcParentId){
    	return goodsClassDao.findChild(gcParentId);
    }
    
    /**
     * 修改分类
     * @param goodsClass
     */
	@Override
	public void updatebyparentid(GoodsClass goodsClass) {
		goodsClassDao.updatebyparentid(goodsClass);
	}
	
	
	
	/**
	 * 更新缓存数据
	 * 需要安装redis
	 * @param GoodsClass
	 */
	public void updateDicCache(){
		if(JedisConfig.JEDIS_STATUS){
			GoodsClass goodsclass = new GoodsClass();
			goodsclass.setGcshow(1);//是否显示1为显示0为不显示
			List<GoodsClass> goodsList = goodsClassDao.findAllbyisshow(goodsclass);
			//10分钟过期时间
			JedisUtils.setObject("gclz:goodsClass", goodsList, 600);
			log.debug("gclz:更新redis");
		}
	}
	
	/**
	 * 删除缓存
	 * 需要安装redis
	 * @param value
	 */
	public void delDicCache(){
		updateDicCache();
	}

	@Override
	public void updatelevelbyPidAndId(GoodsClass goodsClass) {
		// TODO Auto-generated method stub
		goodsClassDao.updatelevelbyPidAndId(goodsClass);
	}
	
	@Override
	public void updatelevelbyPidAndLevelForDel(GoodsClass goodsClass) {
		// TODO Auto-generated method stub
		goodsClassDao.updatelevelbyPidAndLevelForDel(goodsClass);
	}
}
