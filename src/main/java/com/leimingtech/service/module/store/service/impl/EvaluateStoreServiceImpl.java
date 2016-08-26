package com.leimingtech.service.module.store.service.impl;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.entity.base.EvaluateGoods;
import com.leimingtech.core.entity.base.EvaluateStore;
import com.leimingtech.service.module.store.dao.EvaluateStoreDao;
import com.leimingtech.service.module.store.service.EvaluateStoreService;
import com.leimingtech.service.module.store.util.Util;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * @项目名称：leimingtech-seller   
 * @类名称：StoreBindClassServiceImpl   
 * @类描述：   
 * @创建人：shining   
 * @创建时间：2014年12月3日 上午11:56:42   
 * @修改人：shining   
 * @修改时间：2014年12月3日 上午11:56:42   
 * @修改备注：   
 * @version    
 *
 */
@Service
public class EvaluateStoreServiceImpl implements EvaluateStoreService {
	
	@Autowired
	private EvaluateStoreDao evaluateStoreDao;

	@Override
	public EvaluateStore findEvaluateStore(Integer id) {
		return evaluateStoreDao.findEvaluateStore(id);
	}

	@Override
	public List<EvaluateGoods> findPageList(Pager pager,
			EvaluateGoods evaluateGoods) {
		return null;
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EvaluateGoods evaluateGoods) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findCount(EvaluateStore evaluateStore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EvaluateStore> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
     * 根据店铺id查询店铺评分信息
     * @param storeId
     * @return
     */
    public EvaluateStore findEvaluateStoreByStoreId(Integer storeId){
    	EvaluateStore evaluateStore= new EvaluateStore();
		evaluateStore=evaluateStoreDao.findEvaluateStore(storeId);
		//保留一位小数
		NumberFormat numberFormat= NumberFormat.getNumberInstance() ; 
		numberFormat.setMaximumFractionDigits(1);
		if(evaluateStore!=null){
			//发货速度评分
			if(evaluateStore.getSevalDeliverycredit()!=null){
				evaluateStore.setSevalDeliverycredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDeliverycredit())));
			}else{
				evaluateStore.setSevalDeliverycredit(Double.valueOf("3.0"));
			}
			//描述相符评分
			if(evaluateStore.getSevalDesccredit()!=null){
				evaluateStore.setSevalDesccredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDesccredit())));
			}else{
				evaluateStore.setSevalDesccredit(Double.valueOf("3.0"));
			}
			//服务态度评分
			if(evaluateStore.getSevalServicecredit()!=null){
				evaluateStore.setSevalServicecredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalServicecredit())));
			}else{
				evaluateStore.setSevalServicecredit(Double.valueOf("3.0"));
			}
		}
		 if(!evaluateStore.getCount().equals("0")){
		    evaluateStore.setAverageCredit(Util.getAverageCreditFormat(evaluateStore));
		 }
		return evaluateStore;
    }

	@Override
	public  Map<String,Integer> findEvaluate(Map<Object, Object> map) {
		List<EvaluateStore> evaluateStorelist=evaluateStoreDao.findEvaluate(map);
		Map<String,Integer> evamap=new HashMap<String, Integer>();
		Long nowtime=System.currentTimeMillis();//获取当前的时间戳
		Long  oneweektime=DateUtils.getweektime(1);//一周前的时间戳
		Long  onemonthtime=DateUtils.getmonthtime(1);//一月前的时间戳
		Long  sixmonthtimeh=DateUtils.getmonthtime(6);//一月前的时间戳
		//一周内的评论
		Integer oneweekgoodMarkNum= 0;
		Integer oneweekmediumMarkNum=0;
		Integer oneweekbadMarkNum=0;
		//Integer oneweeksumMakNum=0;
		
		//一月内的评论
		Integer onemonthgoodMarkNum= 0;
		Integer onemonthmediumMarkNum=0;
		Integer onemonthbadMarkNum=0;
		//Integer onemonthsumMakNum=0;
		
		//六个月内的评论
		Integer sixmonthgoodMarkNum= 0;
		Integer sixmonthmediumMarkNum=0;
		Integer sixmonthbadMarkNum=0;
		//Integer sixmonthsumMakNum=0;
		if(evaluateStorelist.size()!=0){//判断list不为空
			for(EvaluateStore evaluateStore:evaluateStorelist){
				if(evaluateStore!=null && evaluateStore.getCreateTime()!=null && evaluateStore.getAverageCredits()!=null){
						//一周内评价
						if(evaluateStore.getCreateTime()>oneweektime && evaluateStore.getCreateTime()<nowtime){
							//判断好评
						    if(evaluateStore.getAverageCredits()>=3){
						    	++oneweekgoodMarkNum;
						    }
						    //判断中评
							if(evaluateStore.getAverageCredits()>=2 && evaluateStore.getAverageCredits()<3){
								++oneweekmediumMarkNum;
							}
							//判断差评
		                    if(evaluateStore.getAverageCredits()<2){
		                    	++oneweekbadMarkNum;
		                    }
						}
						//一月内的评价数量
						if(evaluateStore.getCreateTime()>sixmonthtimeh && evaluateStore.getCreateTime()<nowtime){
							//判断好评
						    if(evaluateStore.getAverageCredits()>=3){
						    	++onemonthgoodMarkNum;
						    }
						    //判断中评
							if(evaluateStore.getAverageCredits()>=2 && evaluateStore.getAverageCredits()<3){
								++onemonthmediumMarkNum;
							}
							//判断差评
		                    if(evaluateStore.getAverageCredits()<2){
		                    	++onemonthbadMarkNum;
		                    }
						}
						//六个月内的好评数量
						if(evaluateStore.getCreateTime()>sixmonthtimeh && evaluateStore.getCreateTime()<nowtime){
							//判断好评
						    if(evaluateStore.getAverageCredits()>=3){
						    	++sixmonthgoodMarkNum;
						    }
						    //判断中评
							if(evaluateStore.getAverageCredits()>=2 && evaluateStore.getAverageCredits()<3){
								++sixmonthmediumMarkNum;
							}
							//判断差评
		                    if(evaluateStore.getAverageCredits()<2){
		                    	++sixmonthbadMarkNum;
		                    }
						}
				}
			}
			//存储一周内的评伦数量
			evamap.put("oneweekgoodMarkNum",oneweekgoodMarkNum);
			evamap.put("oneweekmediumMarkNum",oneweekmediumMarkNum);
			evamap.put("oneweekbadMarkNum",oneweekbadMarkNum);
			evamap.put("oneweeksumMakNum",oneweekgoodMarkNum+oneweekmediumMarkNum+oneweekbadMarkNum);
			//存储一周内的评伦数量
			evamap.put("onemonthgoodMarkNum",onemonthgoodMarkNum);
			evamap.put("onemonthmediumMarkNum",onemonthmediumMarkNum);
			evamap.put("onemonthbadMarkNum",onemonthbadMarkNum);
			evamap.put("onemonthsumMakNum",onemonthgoodMarkNum+onemonthmediumMarkNum+onemonthbadMarkNum);
			//存储六个月内的评伦数量
			evamap.put("sixmonthgoodMarkNum",sixmonthgoodMarkNum);
			evamap.put("sixmonthmediumMarkNum",sixmonthmediumMarkNum);
			evamap.put("sixmonthbadMarkNum",sixmonthbadMarkNum);
			evamap.put("sixmonthsumMakNum",sixmonthgoodMarkNum+sixmonthmediumMarkNum+sixmonthbadMarkNum);
		}
		return evamap;
	}
}