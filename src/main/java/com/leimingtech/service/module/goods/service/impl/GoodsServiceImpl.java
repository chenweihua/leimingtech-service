package com.leimingtech.service.module.goods.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.Encodes;
import com.leimingtech.core.entity.GoodsExcel;
import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.vo.GoodsAttrVo;
import com.leimingtech.core.entity.vo.GoodsSpecVo;
import com.leimingtech.core.entity.vo.GoodsTradeVo;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.core.state.goods.GoodsState;
import com.leimingtech.service.module.goods.dao.GoodsAttrIndexDao;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.dao.GoodsSpecDao;
import com.leimingtech.service.module.goods.dao.GoodsSpecIndexDao;
import com.leimingtech.service.module.goods.service.GoodsService;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.search.service.GoodsSearchService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.module.tostatic.service.ToStaticService;
import com.leimingtech.service.utils.goods.GoodsUtils;
import com.leimingtech.service.utils.page.Pager;

@Service
public class GoodsServiceImpl implements GoodsService{
    
    @Resource
    private GoodsDao goodsDao;
    
    @Resource
    private GoodsSpecDao goodsSpecDao;

    @Resource
    private GoodsSpecIndexDao goodsSpecIndexDao;
    
    @Resource
    private GoodsAttrIndexDao goodsAttrIndexDao;
    
    @Resource
    private GoodsSpecService goodsSpecService;
    
    @Resource
    private GoodsSearchService goodsSearchService;
    
    @Autowired
    private ToStaticService toStaticService;
    
    @Autowired
    private SettingService settingService;
    
    @Resource
    private LucenceService lucenceService;
    
	public Goods findGoodById(Integer goodsId) {
		return goodsDao.findGoodById(goodsId);
	}

	public List<Goods> findGoodPagerList(Pager pager) {
		return goodsDao.findGoodPagerList(pager);
	}

	public void saveGoods(Goods goods){
		goods = enGoods(goods);
		goods.setCreateTime(System.currentTimeMillis());
		goodsDao.saveGoods(goods);
	}
	
	/**
	 * 对商品的个别字段进行解码
	 * @param goods
	 * @return
	 */
	private Goods enGoods(Goods goods){
		goods.setSpecName(Encodes.unescapeHtml(goods.getSpecName()));
		goods.setGoodsBody(Encodes.unescapeHtml(goods.getGoodsBody()));
		goods.setGoodsSpec(Encodes.unescapeHtml(goods.getGoodsSpec()));
		goods.setGoodsAttr(Encodes.unescapeHtml(goods.getGoodsAttr()));
		goods.setGoodsColImg(Encodes.unescapeHtml(goods.getGoodsColImg()));
		return goods;
	}

	public void updateGoods(Goods goods){
		//获取商品审核状态设置值
		Map<String,String> map = settingService.findByNameResultMap("goods_isApply");
		//收藏商品时更新商品的收藏数量
		if(goods.getGoodsCollect()!=null&&(goods.getGoodsCollect()==1||goods.getGoodsCollect()==-1)){
			goodsDao.updateGoods(goods);
		}else{
			int goodsApply = Integer.valueOf(map.get("goods_isApply"));
			if(0 == goodsApply){ // 审核状态关闭
				//商品状态值 30:审核通过,40:违规下架,50:审核未通过,60:待审核
				goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
			}else{ // 审核状态开启
				goods.setGoodsState(GoodsState.GOODS_APPLY_PREPARE);
			}
			//修改上架则增量索引
			goods = enGoods(goods);
			goods.setUpdateTime(System.currentTimeMillis());
			goodsDao.updateGoods(goods);
			//修改下架等操作,删除索引
			if(goods.getGoodsId() != null){
				Integer goodsId = goods.getGoodsId();
				// 需要删除索引
				lucenceService.deleteOneIndex(goods);
				//goodsSearchService.deleteGoodsIndex("goodsId",goodsId); 
				// 商品审核通过且上架生成索引
				if(goods.getGoodsState()==GoodsState.GOODS_OPEN_STATE && goods.getGoodsShow()==GoodsState.GOODS_ON_SHOW){
					//保存到2涨索引表(goodsAttrIndex&goodsSpecIndex)
					goodsSearchService.saveOneGoodsIndex(goodsId);
				}
			}
		}

	}
	
	/**
	 * 商品下架，通过商品，拒绝商品
	 */
	public void updateGoodOutEdit(Goods goods){
		Integer goodsState = goods.getGoodsState();
		if(goodsState!=null){
			//审核状态关闭
			//商品状态值 30:审核通过,40:违规下架,50:审核未通过,60:待审核
			goods.setGoodsState(goodsState);
		}
		//修改上架则增量索引
		goods = enGoods(goods);
		goodsDao.updateGoods(goods);
		//修改下架等操作,删除索引
		if(goods.getGoodsId() != null){
			Integer goodsId = goods.getGoodsId();
			// 需要删除索引
			lucenceService.deleteOneIndex(goods);
			//goodsSearchService.deleteGoodsIndex("goodsId",goodsId); 
			// 商品审核通过且上架生成索引
			if(goods.getGoodsState()==GoodsState.GOODS_OPEN_STATE && goods.getGoodsShow()==GoodsState.GOODS_ON_SHOW){
				//保存到2涨索引表(goodsAttrIndex&goodsSpecIndex)
				goodsSearchService.saveOneGoodsIndex(goodsId);
			}
		}
	}
	
	public void deleteGoods(Integer goodsId) {
    	//删除商品表(shop_goods)
		goodsDao.deleteGoods(goodsId);
		lucenceService.deleteOneIndex("goodsId",goodsId);
		//shop_goods_spec
		//goodsSpecDao.deleteGoodsSpecByGoodsId(goodsId);
		//shop_goods_spec_index
		//goodsSpecIndexDao.deleteByGoodsId(goodsId);
		//shou_goods_attr_index
		//goodsAttrIndexDao.deleteByGoodsId(goodsId);
		//goodsSearchService.deleteGoodsIndex("goodsId",goodsId);
	}

	public Goods findOneGoodByCondition(Goods goods) {
		return goodsDao.findOneGoodByCondition(goods);
	}

	public List<String> getGoodsImgList(int goodsId) {
		Goods goods = goodsDao.findGoodById(goodsId);
		List<String> imageList = null;
				if(goods!=null){
					if(goods.getGoodsImageMore()!=null && !"".equals(goods.getGoodsImageMore())){
						imageList=Arrays.asList(goods.getGoodsImageMore().split(","));
					}
				}
		return imageList;
	}

	public Map<String, Object> getGoodsSpec(int goodsId) {
		Goods goods = goodsDao.findGoodById(goodsId);
		String goodsSpec = goods.getGoodsSpec();
		String specName = goods.getSpecName();
		if(specName == null || specName.equals("")){
			return null;
		}
		Map<String, String> specNameMap = JsonUtils.readJsonToMap(specName);
		Map<String, List<GoodsSpecVo>> goodsSpecMap = GoodsUtils.goodsSpecStrToMapList(goodsSpec);
		List<GoodsSpec> goodsSpecs = goodsSpecService.findListByGoodsId(goodsId);
        //规格颜色对应的图片
        Map<String, String> goodsColImg = GoodsUtils.goodsColImgStrToMap(goods.getGoodsColImg());
        //得到该商品的所有goodsvalueId的String,以逗号分割
        for(int i = 0; i < goodsSpecs.size(); i++){
        	goodsSpecs.get(i).setSpecValueIdStr(
        			GoodsUtils.getThisGoodsAllSpecValueId(
        					goodsSpecs.get(i).getSpecGoodsSpec()
        			)
        	);
        }
		Map<String, Object> specmap = new HashMap<String, Object>();
		specmap.put("goodsColImg", goodsColImg);
		specmap.put("specname", specNameMap);
		specmap.put("specvalue", goodsSpecMap);
		specmap.put("goodsSpecs", goodsSpecs);
		return specmap;
	}

	public Map<String, Object> getGoodsAttr(int goodsId) {
		Goods goods = goodsDao.findGoodById(goodsId);
		String attr = goods.getGoodsAttr();
		List<GoodsAttrVo> goodsAttrVos = GoodsUtils.goodsAttrStrToGoodsAttrVoClass(attr);
		String goodsBody = goods.getGoodsBody();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsattr", goodsAttrVos);
		map.put("goodsbody", goodsBody);
		map.put("goodsbrandname", goods.getBrandName());
		return map;
	}

	public int countGoods(Goods goods) {
		goods.setCreateTime(System.currentTimeMillis());
		return goodsDao.countGoods(goods);
	}
	/**
	 * 分页查询获得findTradeGoodlist
	 * @param pager
	 * @return
	 */
	@Override
	public List<GoodsTradeVo> findTradeGoodPagerList(Pager pager) {
		return goodsDao.findTradeGoodPagerList(pager);
	}
	/**
	 * 根据商品字段获取商品的数量
	 * @param goods
	 * @return
	 */
	@Override
	public int findTradeGoodcount(GoodsTradeVo goodsTradeVo) {
		// TODO Auto-generated method stub
		return goodsDao.findTradeGoodcount(goodsTradeVo);
	}

	@Override
	public void delserchgoods(Integer storeId) {
		List<Goods> goodslist=goodsDao.findGoodListbystoreid(storeId);
		for (Goods goodst : goodslist) {
			if(goodst.getGoodsId()!=null){
            Goods goods = new Goods();
            goods.setGoodsId(goodst.getGoodsId());
            goods.setGoodsState(GoodsState.GOODS_CLOSE_STATE);
            goods.setGoodsShow(GoodsState.GOODS_OFF_SHOW);
            goods.setGoodsCloseReason("店铺关闭");
            try {
				updateGoods(goods);
				toStaticService.deleteGoodsDetailStaticPage(goodst.getGoodsId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		}	
	}

	@Override
	public List<Map<String,Object>> countGoodsClick(Integer storeId) {
		return goodsDao.countGoodsClick(storeId);
	}

	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	@Override
	public List<Goods> findGoodListbystoreid(Integer storeId) {
		return goodsDao.findGoodListbystoreid(storeId);
	}

	@Override
	public List<GoodsExcel> findGoodListbystoreid2(Integer storeId) {
		return goodsDao.findGoodListbystoreid2(storeId);
	}
	/**
	 * 根据分类id查询商品
	 * @param gcId
	 * @return
	 */
	@Override
	public List<Goods> findGoodListByGcId(Integer gcId) {
		return goodsDao.findGoodListByGcId(gcId);
	}
	
	
}
