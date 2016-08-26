package com.leimingtech.service.module.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.StringUtils;
import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.GoodsAttrIndex;
import com.leimingtech.core.entity.base.GoodsSpecIndex;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.core.state.goods.GoodsState;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.service.GoodsAttrIndexService;
import com.leimingtech.service.module.goods.service.GoodsService;
import com.leimingtech.service.module.goods.service.GoodsSpecIndexService;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.product.service.ProductService;
import com.leimingtech.service.module.search.service.GoodsSearchService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.utils.ImageSetUtils;
import com.leimingtech.service.utils.goods.GoodsUtils;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsSpecService goodsSpecService;
	
	@Autowired
	private GoodsSpecIndexService goodsSpecIndexService;
	
	@Autowired
	private GoodsAttrIndexService goodsAttrIndexService;
	
	@Autowired
    private SettingService settingService;
	
	@Resource
    private GoodsSearchService goodsSearchService;
	
	@Resource
	private GoodsDao goodsDao;
	
	@Resource
	private LucenceService lucenceService;
	
	
	/**
	 * 保存goods
	 * cgl
	 * 2015年06月17日11:50:08
	 * 返回 0 则保存失败
	 * 否则返回goodsId
	 */
	@Override
	public Integer saveGoods(Goods goods, String goodsSpecJson) {
		//获取商品审核状态设置值
		Map<String,String> map = settingService.findByNameResultMap("goods_isApply");
		int goodsApply = Integer.valueOf(map.get("goods_isApply"));
		if(0 == goodsApply){
			//审核状态关闭
			//商品状态值 30:审核通过,40:违规下架,50:审核未通过,60:待审核
			goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		}else{
			//审核状态开启
			goods.setGoodsState(GoodsState.GOODS_APPLY_PREPARE);
		}
		//保存goods
		goodsService.saveGoods(goods);
		//保存至goodsspec
		saveToGoodsSpec(goods, goodsSpecJson);
		if(goods.getGoodsState()==GoodsState.GOODS_OPEN_STATE && goods.getGoodsShow()==GoodsState.GOODS_ON_SHOW){
			//保存到2涨索引表(goodsAttrIndex&goodsSpecIndex)
			//审核状态关闭
			saveToSpecAndAttrIndex(goods);
		}
		Integer goodsId = goods.getGoodsId();
		// 生成缩略图
		String imagesMore = goods.getGoodsImageMore();
		if(StringUtils.isNotEmpty(imagesMore)){
			ImageSetUtils.setImages(imagesMore);
		}
		return goodsId;
	}
	
	/**
	 * 修改goods
	 * cgl
	 * 2015年06月30日19:18:44
	 * 返回 0 修改失败
	 * 返回 1 修改成功
	 */
	@Override
	public Integer updateGoods(Goods goods, String goodsSpecJson) {
		try{
			//获取商品审核状态设置值
			Map<String,String> map = settingService.findByNameResultMap("goods_isApply");
			int goodsApply = Integer.valueOf(map.get("goods_isApply"));
			if(0 == goodsApply){
				//审核状态关闭
				//商品状态值 30:审核通过,40:违规下架,50:审核未通过,60:待审核
				goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
			}else{
				//审核状态开启
				goods.setGoodsState(GoodsState.GOODS_APPLY_PREPARE);
			}
			//商品所在店铺状态 0开启 1关闭
			goods.setGoodsStoreState(0);
			goods.setSalenum(0);
			//保存goods
			goodsDao.updateGoods(goods);
			// 生成缩略图
			String imagesMore = goods.getGoodsImageMore();
			if(StringUtils.isNotEmpty(imagesMore)){
				ImageSetUtils.setImages(imagesMore);
			}
			// 保存至goodsspec
			saveToGoodsSpec(goods, goodsSpecJson);
			
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
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//存入goodsspec表
	private void saveToGoodsSpec(Goods goods, String goodsSpecJson){
		//在保存之前首先删除goodsSpec表中关于这个goodsId的数据
		goodsSpecService.deleteGoodsSpecByGoodsId(goods.getGoodsId());
		if(goodsSpecJson != null && !goodsSpecJson.trim().equals("")){
			//准备创建表shop_goods_sepc的实体类对象
			List<Object> goodsSpecs = JsonUtils.readJsonList(goodsSpecJson, GoodsSpec.class);
			//循环获得goodsspec
			for(int i = 0; i < goodsSpecs.size(); i++){
				GoodsSpec goodsSpec = (GoodsSpec) goodsSpecs.get(i);
				//设置商品id
				goodsSpec.setGoodsId(goods.getGoodsId());
				//设置销售量为0
				goodsSpec.setSpecSalenum(0);
				//保存goodsspecs
				goodsSpecService.saveGoodsSpec(goodsSpec);
				if(i == 0){
					//设置商品specid
					goods.setSpecId(goodsSpec.getGoodsSpecId());
				}
			}
		}else{
			GoodsSpec goodsSpec = new GoodsSpec();
			//设置商品id
			goodsSpec.setGoodsId(goods.getGoodsId());
			//价格
			goodsSpec.setSpecGoodsPrice(goods.getGoodsStorePrice());
			//库存
			goodsSpec.setSpecGoodsStorage(goods.getGoodsTotalStorage());
			//货号
			goodsSpec.setSpecGoodsSerial(goods.getGoodsSerial());
			//设置销售量为0
			goodsSpec.setSpecSalenum(0);
			//保存goodsspecs
			goodsSpecService.saveGoodsSpec(goodsSpec);
			//设置商品specid
			goods.setSpecId(goodsSpec.getGoodsSpecId());
		}

		//再次修改goods表
		//判断是否存有goodsspec
		if(goods.getSpecId() != null){
			Goods tagGoods = new Goods();
			tagGoods.setGoodsId(goods.getGoodsId());
			tagGoods.setSpecId(goods.getSpecId());
			goodsService.updateGoods(goods);
		}
	}
	
	private Integer saveToSpecAndAttrIndex(Goods goods){
		//商品的id
		Integer goodsId = goods.getGoodsId();
		//商品分类id
		Integer gcId = goods.getGcId();
		//商品类型id
		Integer typeId = goods.getTypeId();
		
		//存入goodsSpecIndex,在数据库中与goods表为多对一,
		//一个oods会对应多个goodsspecIndex
		//判断是否有规格
		if(goods.getSpecId() != null && !goods.getGoodsSpec().trim().equals("")){
			saveToGoodsSpecIndex(goods.getGoodsSpec(), goodsId, gcId, typeId);
		}
		
		//存入goodsAttrIndex
		if(goods.getGoodsAttr() != null &&  !goods.getGoodsAttr().trim().equals("")){
			saveToGoodsAttrIndex(goods.getGoodsAttr(), goodsId, gcId, typeId);
		}
		return goodsId;

	}
	
	//存入GoodsSpecIndex表
	private void saveToGoodsSpecIndex(String spec, Integer goodsId, Integer gcId, Integer typeId){
		//在保存之前,首先删除关于这个商品所有的数据
		goodsSpecIndexService.deleteByGoodsId(goodsId);
		//利用工具类将字符串 转换成实体类,获得的list遍历存入数据库
		List<GoodsSpecIndex> list = GoodsUtils.goodsSpecStrToGoodsSpecIndexClass(spec, goodsId, gcId, typeId);
		//循环放入数据库
		for(int p = 0; p < list.size(); p++){
			goodsSpecIndexService.save(list.get(p));
		}
	}
	
	//存入saveToGoodsAttrIndex表
	private void saveToGoodsAttrIndex(String attr, Integer goodsId, Integer gcId, Integer typeId){
		//在保存之前,首先删除关于这个商品所有的数据
		goodsAttrIndexService.deleteByGoodsId(goodsId);
		//利用工具类将字符串 转换成实体类,获得的list遍历存入数据库
		List<GoodsAttrIndex> list = GoodsUtils.goodsAttrStrToGoodsSpecIndexClass(attr, goodsId, gcId, typeId);
		//循环放入数据库
		for(int p = 0; p < list.size(); p++){
			goodsAttrIndexService.save(list.get(p));
		}
	}


    /**
     * 修改库存
     * @parm GoodsSpec 需要2个参数 specId 以及出售数量 specSalenum(这个出售数量是本次的出售数量)
     * 返回 0 则保存失败
	 * 返回 1 则保存成功
     */
	@Override
	public Integer updateStorage(GoodsSpec goodsSpec) {
		if(goodsSpec != null){
			if(goodsSpec.getGoodsId() != null && goodsSpec.getSpecSalenum() != null){
				goodsSpecService.updateGoodsSpecStorage(goodsSpec);
				Integer goodsId = goodsSpec.getGoodsId();
				Goods goods = new Goods();
				goods.setGoodsId(goodsId);
				goods.setSalenum(goodsSpec.getSpecSalenum());
				goodsDao.updateGoods(goods);
				return 1;
			}
		}
		return 0;
	}

}
