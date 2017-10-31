package com.manji.mlife.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.mapper.AirMapper;
import com.manji.mlife.model.AirAppBean;
import com.manji.mlife.model.Airport;
import com.manji.mlife.model.Items;
import com.manji.mlife.model.State;
import com.manji.mlife.model.Trade;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.Airline;
import com.qianmi.open.api.domain.elife.TicketTrade;
import com.qianmi.open.api.request.AirLinesListRequest;
import com.qianmi.open.api.request.AirOrderCancelRequest;
import com.qianmi.open.api.request.AirOrderCreateRequest;
import com.qianmi.open.api.response.AirLinesListResponse;
import com.qianmi.open.api.response.AirOrderCancelResponse;
import com.qianmi.open.api.response.AirOrderCreateResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订购飞机票
 * @author gaochao
 * 2016年7月7日下午4:26:02
 * AirServiceImpl
 *
 */
@Service
public class AirServiceImpl implements AirService{
	

	 //平台公共service
	 @Autowired	
	 private DBService service;
	 @Autowired
	 private AirMapper airMapper;
	 private static final Logger logger =Logger.getLogger(GkServiceImpl.class);

	/**
	 * //查询航线列表
	 * @author gaochao
	 * @param from
	 * @param to
	 * @param date
	 * @return
	 * 2016年8月18日下午4:00:13
	 *
	 */
	@Override
	public String getAirLineslist(String from, String to, String date , String start , String end) {
		
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
	 	
		AirLinesListRequest req = new AirLinesListRequest();
		req.setFrom(from);
		req.setItemId("5500301");
		req.setDate(date);
		req.setTo(to);
		AirLinesListResponse response=null;
		List<Airline> airlines=null;
		String string="0";
		try {			
			response = client.execute(req, map.get("accessToken"));
			
			logger.info("AirLinesListRequest"+JSONObject.fromObject(response).toString());

			airlines = response.getAirlines();

		} catch (ApiException e) {
			
			
			response = client.execute(req, map.get("accessToken"));
			
			logger.info("AirLinesListRequest"+JSONObject.fromObject(response).toString());

			airlines = response.getAirlines();
			
			
			
			
		}finally{
			//飞机航线列表
			if(airlines!=null){
				//更新出发机场和到达机场的信息
				for(int i=0;i<airlines.size();i++){
					//1.先得到航站楼的信息
					String s1 = airlines.get(i).getOrgCityName();//出发城市
					String s2 = airlines.get(i).getDstCityName();//到达城市
					airlines.get(i).setOrgCityName(start+"("+s1.split("[(]")[1]);
					airlines.get(i).setDstCityName(end+"("+s2.split("[(]")[1]);
				}
				net.sf.json.JSONArray JSONArray = net.sf.json.JSONArray.fromObject(airlines);//list转化为json
				string = JSONArray.toString();
		    }	
			
			return string;
		}
		
	}
	
	//（飞机票订单生成）
	@Override
	public Map<String, String> getAirOrderCreate(Items ttems,String userName,HttpSession session) throws ApiException {
		System.out.println(JSONObject.fromObject(ttems));
		Map<String, String> map = service.getBasePara();
		
		Map<String, String> map1 = new HashMap<String, String>();
		
		try{
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		
		AirOrderCreateRequest req = new AirOrderCreateRequest();
		
		req.setItemId(ttems.getItemId());//选择的飞机票标准商品编号
		req.setContactName(ttems.getContactName());//订票联系人
		req.setContactTel(ttems.getContactTel());//订票联系人电话
		req.setDate(ttems.getDate());//出发日期
		req.setFrom(ttems.getFrom());//起飞站点(机场)三字码
		req.setTo(ttems.getTo());//抵达站点(机场)三字码
		req.setCompanyCode(ttems.getCompanyCode());//航空公司编码
		req.setFlightNo(ttems.getFlightNo());//航班号
		req.setSeatCode(ttems.getSeatCode());//舱位编码f
		
		String s="";
		String[] listName = ttems.getListName();//乘客姓名
		String[] listPhone = ttems.getListPhone();//乘客电话
		String[] listCertificates = ttems.getListCertificates();//乘客证件号码
		for (int i = 0; i < ttems.getListName().length; i++) {
			
			 s+=listName[i]+","+listPhone[i]+","+listCertificates[i]+";";
		}
		req.setPassagers(s.substring(0, s.length()-1));
		
		AirOrderCreateResponse response = client.execute(req, map.get("accessToken"));

		logger.info("AirOrderCreateRequest"+JSONObject.fromObject(response).toString());
		System.out.println(JSONObject.fromObject(response).toString());
		//订单详情
		TicketTrade info = response.getTicketTrade();
		
		if(info!=null){
			
			map1 = service.insertTrafficData(info, "Air", userName);
			map1.put("errCode", "0");
			session.setAttribute("airInfo", info);
		}else{
			map1.put("redirectURL","/errInfo");
			map1.put("errCode", "1");
			map1.put("errMsg", response.getSubMsg());
		}
		}catch(ApiException a){
			a.printStackTrace();
			logger.info(a.getErrCode()+a.getErrMsg());
			map1.put("errCode", "1");
			map1.put("errMsg", a.getErrMsg());
		}
		return map1;
	}
	
	//（飞机票订单生成）
		@Override
		public Map<String, String> getAirAppOrderCreate(AirAppBean bean,String userName,HttpSession session) throws ApiException {
			
			Map<String, String> map = service.getBasePara();
			
			Map<String, String> map1 = new HashMap<String, String>();
			
			
			
			JSONObject airLine=JSONObject.fromObject(bean.getAirLineJson());
			String passengers=bean.getPassengers();
			JSONObject contact=JSONObject.fromObject(bean.getContactJson());
			
			try{
			OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
			
			AirOrderCreateRequest req = new AirOrderCreateRequest();
			
			req.setItemId("5500301");//选择的飞机票标准商品编号
			req.setContactName(contact.getString("contactName"));//订票联系人
			req.setContactTel(contact.getString("contactTel"));//订票联系人电话
			req.setDate(airLine.getString("date"));//出发日期
			req.setFrom(airLine.getString("from"));//起飞站点(机场)三字码
			req.setTo(airLine.getString("to"));//抵达站点(机场)三字码
			req.setCompanyCode(airLine.getString("companyCode"));//航空公司编码
			req.setFlightNo(airLine.getString("flightNo"));//航班号
			req.setSeatCode(airLine.getString("seatCode"));//舱位编码f

			req.setPassagers(passengers);
			
			AirOrderCreateResponse response = client.execute(req, map.get("accessToken"));

			logger.info("AirOrderCreateRequest"+JSONObject.fromObject(response).toString());
			System.out.println(JSONObject.fromObject(response).toString());
			//订单详情
			TicketTrade info = response.getTicketTrade();
			
			if(info!=null){
				
				map1 = service.insertTrafficData(info, "Air", userName);
				map1.put("errCode", "0");
				session.setAttribute("airInfo", info);
			}else{
				map1.put("redirectURL","/errInfo");
				map1.put("errCode", "1");
				map1.put("errMsg", response.getSubMsg());
			}
			}catch(ApiException a){
				a.printStackTrace();
				logger.info(a.getErrCode()+a.getErrMsg());
				map1.put("errCode", "1");
				map1.put("errMsg", a.getErrMsg());
			}
			return map1;
		}
		
	
	
	
	
	
	/**
	 *    取消飞机票
	 *      //更改交易流水
			//订单状态中文描述：0：预定中,2：预定完成，1：已完成，9：已取消
	 * @author gaochao
	 * 2016年7月11日下午2:43:48
	 *
	 */
	@Override
	public void updateTrade(State s) {
		//取消飞机票(9)
	}

	@Override
	public String getAirOrderCancelRequest(String tradeNo) throws ApiException {
		
        Map<String, String> map = service.getBasePara();
        
		String s="0";	
        
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		AirOrderCancelRequest req = new AirOrderCancelRequest();
		req.setTradeNo(tradeNo);
		
		AirOrderCancelResponse response = client.execute(req, map.get("accessToken"));
		
		String result = response.getResult();
		
		if("1".equals(result)){
			s="1";
			//取消订单成功
			Trade trade = service.queryTradeByNoTradeNo(tradeNo);
			
			trade.setState("9");
			
			service.updateTradeSelective(trade);
		}
		return s;
		
	}
	/**
	 *输入城市查询机场信息
	 */
	@Override
	public String getAirport(String info) {
		//根据输入的信息是英文还是汉字进行查询
		List<Airport> airInfo;
		Pattern key = Pattern.compile("[a-zA-Z]+");
		Matcher mc = key.matcher(info);
		if(mc.matches()){//如果是字母,根据简码查询
			airInfo = airMapper.getInfoId(info);
		}else{//根据汉字查询
			airInfo = airMapper.getInfoName(info);
		}
		
		return JSONArray.fromObject(airInfo).toString();
	}
	
	/**
	 * 退订飞机票订单
	 * @author gaochao
	 * @param tradeNo
	 * @param returnType
	 * @param orderNos
	 * 2016年9月13日下午12:14:50
	 * @throws ApiException 
	 *
	 *//*
	@Override
	public String AirOrderRefundRequest(String tradeNo, String returnType, String[] orderNos) throws ApiException {
        Map<String, String> map = service.getBasePara();
       
        String msg=null;
        
        String Nos="";
        if(orderNos.length!=0){
        	for (int i = 0; i < orderNos.length; i++) {
        		Nos+=orderNos[i]+",";
			}
        	Nos=Nos.substring(0, Nos.length()-1);
        }
        
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		AirOrderRefundRequest req = new AirOrderRefundRequest();
		req.setTradeNo(tradeNo);
		req.setOrderNos(Nos);
		req.setReturnType(returnType);
		AirOrderRefundResponse response = client.execute(req, map.get("accessToken"));
	
		String result =  response.getResult();
		
		//退款成功
		if("1".equals(result)){
			msg="1";
		}else{
			
			 msg = response.getMsg();
			
		}
		return msg;
		
	}*/
	
}
