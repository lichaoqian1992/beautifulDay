package com.manji.adds.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.adds.mapper.AddsMapper;
import com.manji.adds.model.Adds;
import com.manji.adds.model.Sequence;
import com.manji.adds.utils.DatesUtils;
import com.manji.adds.utils.JsonUtils;
import com.manji.adds.utils.PicUtils;
import com.manji.adds.utils.SequenceUtils;
@Service
public class AddsServiceImpl implements AddsService {
	
	@Autowired
	private AddsMapper mapper;
	
	@Override
	public String getSeqNo() {
		
		Sequence seq =mapper.getSequence();
		
		String today =SequenceUtils.today();
				
		int serial ;
		
		if(today.equals(seq.getDate()))
		{
			
			serial =seq.getSerial()+1;
			
			seq.setSerial(serial);
			
			mapper.updSequence(seq);
			
		}else
		{
			serial =1;
			
			seq.setDate(today);
			
			seq.setSerial(serial);
			
			mapper.updSequence(seq);
			
		}
		
		return SequenceUtils.getSerialNo(serial);
	}

	@Override
	public void addAdds(Adds adds) {

		
		mapper.insertAdds(adds);
		
		
	}

	@Override
	public Adds queryAdds(String ad_id) {

		Adds adds =mapper.queryAddsById(ad_id);
		
		List<String> list =PicUtils.getPics(ad_id);
		adds.setPic_url(JsonUtils.getArray(list));
		
		
		return adds;
	}

	@Override
	public boolean updAdds(Adds adds) {

		int s =mapper.updAdds(adds);
		
		if(s>0)
		{
			return true;
			
		}else
		{
			return false;
			

		}
		
	}

	@Override
	public List<Adds> queryAddsList(Adds queryMap) {

		List<Adds> list =mapper.queryAdds(queryMap);
		
		for(int i=0;i<list.size();i++){
			Adds adds =list.get(i);
			
			String ad_id=adds.getAd_id();
			
			String pic_url=PicUtils.getOnePic(ad_id);
			
			adds.setPic_url(pic_url);
			
		}
		
		return list;
	}

	@Override
	public boolean updState(String ad_id, String state) {

		Adds ad =new Adds();
		
		ad.setAd_id(ad_id);
		ad.setState(state);
		int s =mapper.updState(ad);
//		System.out.println(s);
		if(s>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Map<String, String> getCounts() {

		Map<String ,String> map =new HashMap<String,String>();
		
		
		String month =DatesUtils.thisMonth();
		System.out.println(month);
		String MCounts =""+mapper.getMCounts(month);
		
		
		String lastDay =DatesUtils.lastDay();
		String DCounts =""+ mapper.getDCounts(lastDay);
		
		
		String AllCounts =""+mapper.getAllCount();
		
		String revCounts =""+mapper.getRevCount();
		
		String clickCounts =""+mapper.getAllCounts();
		
		
		map.put("AllCounts",AllCounts);
		map.put("revCounts",revCounts);
		map.put("clickCounts",clickCounts);
		map.put("MCounts",MCounts);
		map.put("DCounts", DCounts);
		
		return map;
	}

}
