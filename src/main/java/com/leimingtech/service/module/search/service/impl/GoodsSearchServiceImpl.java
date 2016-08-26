package com.leimingtech.service.module.search.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.vo.SpecVo;
import com.leimingtech.core.state.goods.GoodsState;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.service.GoodsSpecIndexService;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.search.dao.GoodsSearchDao;
import com.leimingtech.service.module.search.service.GoodsSearchService;
import com.leimingtech.service.module.search.service.SearchService;
import com.leimingtech.service.utils.lucene.LucenePager;
import com.leimingtech.service.utils.page.Pager;

/**
 * 商品搜索
 * @author cgl
 */
@Service
public class GoodsSearchServiceImpl implements GoodsSearchService{
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private GoodsSpecIndexService goodsSpecIndexService;
	
	@Autowired
	private GoodsSearchDao goodsSearchDao;
	
	@Resource
	private LucenceService lucenceService;
	
	/**
	 * 批量建立商品索引
	 * @return Integer 
	 */
	@Override
	public Integer saveGoodsIndex(Goods goodsCondition) {
		//准备分页,这里的分页是为了在建立索引的时候防止内存溢出
		Pager pager = new Pager();
		//设置每次索引2000条,暂时写死
		int pageSize = 2000;
		pager.setPageSize(pageSize);
		//设置商品状态
		goodsCondition.setGoodsShow(GoodsState.GOODS_ON_SHOW);
		goodsCondition.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		//设置店铺状态
		goodsCondition.setGoodsStoreState(GoodsState.GOODS_STORE_OPEN);
		pager.setCondition(goodsCondition);
		//得到总条数
		int count = goodsSearchDao.findGoodPagerListCount(goodsCondition);
		//设置总条数
		pager.setTotalRows(count);
		try {
			//准备list
			List<Goods> goodsList = null;
			// 设置一共有几页
			int pageCount = 0;
			pageCount = count%pager.getPageSize()==0?count/pager.getPageSize():count/pager.getPageSize()+1;
			// 开始循环分页取出
			for(int j = 1; j <= pageCount; j++ ){
				// 这里分页循环取出goods
				pager.setPageNo(j);
				// 获得到当前页的数据
				goodsList = goodsSearchDao.findGoodPagerList(pager);
				// 生成索引
				lucenceService.createMoreIndex(goodsList);
			}
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 增量索引
	 */
	@Override
	public Integer saveOneGoodsIndex(Integer goodsId) {
		Goods goodsCondition = new Goods();
		goodsCondition.setGoodsId(goodsId);
		//设置商品状态
		goodsCondition.setGoodsShow(GoodsState.GOODS_ON_SHOW);
		goodsCondition.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		//设置店铺状态
		goodsCondition.setGoodsStoreState(GoodsState.GOODS_STORE_OPEN);
		Goods goods = goodsSearchDao.findOneGoodByCondition(goodsCondition);
		if(goods == null){
			return 0;
		}
		// 生成索引
		lucenceService.creatOneIndex(goods);
		return 1;
	}
	
	/**
	 * 删除索引
	 */
	/*@Override
	public void deleteGoodsIndex(String field,Integer id) {
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try{
		File indexDir = new File(GOODS_INDEX_PATH);
		directory = FSDirectory.open(indexDir);
		indexWriterConfig = new IndexWriterConfig(luceneVersion, analyzer);
		indexWriter = new IndexWriter(directory, indexWriterConfig);
		BooleanQuery booleanQuery = new BooleanQuery(); 
		NumericRangeQuery<Integer> rangeQuery2 = NumericRangeQuery.newIntRange(field, id, id, true, true);
		booleanQuery.add(rangeQuery2, BooleanClause.Occur.MUST);
		indexWriter.deleteDocuments(rangeQuery2);
		}catch(Exception e){
		}finally{
			if(indexWriter != null){
				try {
					indexWriter.close();
					indexWriter = null;
				} catch (IOException e) {
				}
			}
		}
	}*/

	
	/**
	 * 搜索商品
	 */
	@Override
	public LucenePager searchGoods(LucenePager lucenePager) {
		
		try{
			//获得该关键词下的规格list和brandList
			String keyword = lucenePager.getKeyword();
			if(lucenePager.getSearchType().equals("keywordSearch")){
				//对应规格
				lucenePager.setSpecList(goodsSearchDao.getSpecListByKeyword(keyword));
				//对应品牌
				lucenePager.setBrandList(goodsSearchDao.getBrandListByKeyword(keyword));;
				
			}else if(lucenePager.getSearchType().equals("allSearch")){
				
			}else if(lucenePager.getSearchType().equals("gcIdSearch")){
				//对应规格
				lucenePager.setSpecList(goodsSearchDao.getSpecListByGcId(Integer.parseInt(keyword)));
				//对应品牌
				lucenePager.setBrandList(goodsSearchDao.getBrandListByGcId(Integer.parseInt(keyword)));
				
			}else if(lucenePager.getSearchType().equals("typeIdSearch")){
				//对应规格
				lucenePager.setSpecList(goodsSearchDao.getSpecListByTypeId(Integer.parseInt(keyword)));
				//对应品牌
				lucenePager.setBrandList(goodsSearchDao.getBrandListByTypeId(Integer.parseInt(keyword)));
				
			}else if(lucenePager.getSearchType().equals("BrandIdSearch")){
				List<SpecVo> specList = goodsSearchDao.getSpecListByBrandId(Integer.parseInt(lucenePager.getKeyword()));
				lucenePager.setSpecList(specList);
			}
			
			lucenePager = lucenceService.searchGoodsIndex(lucenePager);
		}catch(Exception e){
			return null;
		}
		return lucenePager;
	}

	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	public List<Brand> getBrandListByKeyword(String keyword) {
		return goodsSearchDao.getBrandListByKeyword(keyword);
	}

	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	public List<Brand> getBrandListByGcId(Integer gcId) {
		return goodsSearchDao.getBrandListByGcId(gcId);
	}

	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	public List<Brand> getBrandListByTypeId(Integer typeId) {
		return goodsSearchDao.getBrandListByTypeId(typeId);
	}
	
	
}
