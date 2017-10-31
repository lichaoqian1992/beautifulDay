package com.manji.datahost.controller.sqlserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.proxy.jdbc.JdbcParameter.TYPE;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.datahost.model.mysql.CallRegister;
import com.manji.datahost.model.mysql.OtherInfo;
import com.manji.datahost.model.mysql.SheetInfo;
import com.manji.datahost.model.sqlserver.agent.AgentInfo;
import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.client.ArticleInfo;
import com.manji.datahost.model.sqlserver.client.ArticleSpec;
import com.manji.datahost.model.sqlserver.client.SafeInfo;
import com.manji.datahost.model.sqlserver.client.ShopManage;
import com.manji.datahost.model.sqlserver.client.ShopReputation;
import com.manji.datahost.model.sqlserver.client.UserBack;
import com.manji.datahost.model.sqlserver.client.UserLogin;
import com.manji.datahost.model.sqlserver.client.UserManage;
import com.manji.datahost.model.sqlserver.client.UserSafe;
import com.manji.datahost.model.sqlserver.message.MessageObj;
import com.manji.datahost.model.sqlserver.message.SendMessage;
import com.manji.datahost.model.sqlserver.order.Order;
import com.manji.datahost.model.sqlserver.order.OrderBack;
import com.manji.datahost.model.sqlserver.order.OrderBackInfo;
import com.manji.datahost.model.sqlserver.order.OrderDetail;
import com.manji.datahost.model.sqlserver.order.OrderPrice;
import com.manji.datahost.model.sqlserver.order.OrderSpec;
import com.manji.datahost.model.sqlserver.shop.CommentManage;
import com.manji.datahost.model.sqlserver.shop.GoodsManage;
import com.manji.datahost.model.sqlserver.shop.ShopActivities;
import com.manji.datahost.model.sqlserver.shop.ShopInfo;
import com.manji.datahost.model.sqlserver.user.AgentRegister;
import com.manji.datahost.model.sqlserver.user.ShopRegister;
import com.manji.datahost.model.sqlserver.user.UserAccount;
import com.manji.datahost.model.sqlserver.user.UserAddress;
import com.manji.datahost.model.sqlserver.user.UserInfo;
import com.manji.datahost.model.sqlserver.user.UserOrder;
import com.manji.datahost.model.sqlserver.user.UserRegister;
import com.manji.datahost.model.sqlserver.user.UserVoucher;
import com.manji.datahost.service.mysql.MessageService;
import com.manji.datahost.service.mysql.OtherInfoService;
import com.manji.datahost.service.mysql.PermissionService;
import com.manji.datahost.service.mysql.UtilsService;
import com.manji.datahost.service.sqlserver.AgentInfoService;
import com.manji.datahost.service.sqlserver.ClientInfoService;
import com.manji.datahost.service.sqlserver.OrderService;
import com.manji.datahost.service.sqlserver.ShopInfoService;
import com.manji.datahost.service.sqlserver.UserInfoService;
import com.manji.datahost.utils.Message;
import com.manji.datahost.utils.OrderTypeEnums;
import com.manji.datahost.utils.ResultEmuns;
import com.manji.datahost.utils.TimeUtils;
import com.manji.datahost.vo.AccountVo;
import com.manji.datahost.vo.ActivitiesVo;
import com.manji.datahost.vo.ClientManageVo;
import com.manji.datahost.vo.GoodsManageVo;
import com.manji.datahost.vo.OrderBackVo;
import com.manji.datahost.vo.OrderVo;
import com.manji.datahost.vo.PageVo;
import com.manji.datahost.vo.UserLoginVo;
import com.manji.datahost.vo.UserOrderVo;
import com.manji.datahost.vo.PageVo.MOBILE;
import com.manji.datahost.vo.PageVo.PAGENUMBER;
import com.manji.datahost.vo.PageVo.PAGESIZE;
import com.manji.datahost.vo.PageVo.USERID;

import aj.org.objectweb.asm.Type;

@RestController
public class InnerController {
	
	@Autowired
	private UserInfoService service;
	@Autowired
	private ShopInfoService shopService;
	@Autowired
	private AgentInfoService agentService;
	@Autowired
	private OtherInfoService otherService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UtilsService utilsService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ClientInfoService clientService;
	@Autowired
	private PermissionService perService;
	
	//用户信息
	@GetMapping("getUserInfo")
	public Message getUserInfo(UserInfo ui) throws Exception{//参数mobile(电话),user_name(用户名),name(店铺名)
		Message msg = new Message();
		Integer per = perService.getConnector("getUserInfo");
		
		if(per ==1){
			
			UserInfo userInfo = service.getUserInfo(ui);
			if(userInfo != null){
				List<Map<String,String>> map = service.getUserType(userInfo.getUser_id());
				userInfo.setUser_type(map);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(userInfo);
			}else{
				if(!"".equals(ui.getMobile())){
					OtherInfo otherInfo = otherService.getOtherInfo(ui.getMobile());
					if(otherInfo == null){
						msg.setCode(ResultEmuns.NODATA.getCode());
						msg.setMessage(ResultEmuns.NODATA.getMessage());
					}else{
						msg.setCode(ResultEmuns.SUCCESS.getCode());
						msg.setMessage("其他");
						msg.setResult(otherInfo);
					}
				}else{
					msg.setCode(ResultEmuns.PARAM.getCode());
					msg.setMessage(ResultEmuns.PARAM.getMessage());
				}
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//用户详情信息
	@GetMapping("getUserDetail")
	public Message getUserDetail(@Validated({PAGENUMBER.class,PAGESIZE.class,TYPE.class}) PageVo vo,BindingResult result){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getUserDetail");
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			if(per == 1){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				if("1".equals(vo.getType())){
					//账户流水
					Page<UserAccount> userAccount = service.getUserAccount(vo);
					List<UserAccount> userList = userAccount.getDataList();
					List<OrderTypeEnums> enums = OrderTypeEnums.getAllEnum();
					for(UserAccount user : userList){
						for(OrderTypeEnums en : enums){
							if(user.getType().equals(en.toString())){
								user.setType(OrderTypeEnums.getMsgByCode(en.toString()));
							}
						}
						Integer order_id = clientService.findOrderIdByOrderNo(user.getOrder_no());
						Integer order_back_id = orderService.getOrderIdByBackNo(user.getOrder_no());
						if(order_back_id != null){
							user.setOrder_type("2");
						}else if(order_back_id == null && order_id != null){
							user.setOrder_type("1");
						}else{
							user.setOrder_type("3");
						}
					}
					msg.setResult(userAccount);
				}else if("2".equals(vo.getType())){
					//满意券
					Page<UserVoucher> userVoucher = service.getUserVoucher(vo);
					List<UserVoucher> userList = userVoucher.getDataList();
					List<OrderTypeEnums> enums = OrderTypeEnums.getAllEnum();
					for(UserVoucher user : userList){
						for(OrderTypeEnums en : enums){
							if(user.getType().equals(en.toString())){
								user.setType(OrderTypeEnums.getMsgByCode(en.toString()));
							}
						}
						Integer order_id = clientService.findOrderIdByOrderNo(user.getOrder_no());
						Integer order_back_id = orderService.getOrderIdByBackNo(user.getOrder_no());
						if(order_back_id != null){
							user.setOrder_type("2");
						}else if(order_back_id == null && order_id != null){
							user.setOrder_type("1");
						}else{
							user.setOrder_type("3");
						}
					}
					msg.setResult(userVoucher);
				}else if("3".equals(vo.getType())){
					//订单详情
					Page<UserOrder> userOrder = service.getUserOrder(vo);
					msg.setResult(userOrder);
				}else if("4".equals(vo.getType())){
					//收货地址
					Page<UserAddress> userAddress = service.getUserAddress(vo);
					msg.setResult(userAddress);
				}else if("5".equals(vo.getType())){
					//来电记录
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					if(pageNumber == null){
						pageNumber = 1;
					}
					if(pageSize == null){
						pageSize = 10;
					}
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<CallRegister> callRegister = utilsService.callRegister(vo.getPageNumber(), vo.getPageSize(),vo.getMobile());
					msg.setResult(callRegister);
				}else if("6".equals(vo.getType())){
					//工单信息
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					if(pageNumber == null){
						pageNumber = 1;
					}
					if(pageSize == null){
						pageSize = 10;
					}
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<SheetInfo> sheetInfo = utilsService.sheetInfo(vo.getMobile(), vo.getPageNumber(), vo.getPageSize());
					msg.setResult(sheetInfo);
				}else{
					msg.setCode(ResultEmuns.PARAM.getCode());
					msg.setMessage("type范围为1-6");
				}
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
			
		}
		return msg;
	}
	
	
	//获取所有注册信息
	@GetMapping("getRegister")
	public Message getRegister(@RequestParam String user_name,String type){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getRegister");
		if(per == 1){
			if("Buyer".equals(type)){
				List<UserRegister> register = service.getUserRegister(user_name);
				if(register.size() == 0){
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}else{
					msg.setCode(ResultEmuns.SUCCESS.getCode());
					msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					msg.setResult(register);
				}
			}else if("Shopper".equals(type)){
				List<ShopRegister> register = service.getShopRegister(user_name);
				if(register.size() == 0){
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}else{
					msg.setCode(ResultEmuns.SUCCESS.getCode());
					msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					msg.setResult(register);
				}
			}else{
				List<AgentRegister> register = service.getAgentRegister(user_name);
				if(register.size() == 0){
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}else{
					msg.setCode(ResultEmuns.SUCCESS.getCode());
					msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					msg.setResult(register);
				}
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//根据真实姓名查询账号
	@GetMapping("getUserNameByPersonName")
	public Message getUserNameByPersonName(@RequestParam String person_name){
		Message msg = new Message();
		
		List<Object> user_name = service.getUserNameByPersonName(person_name);
		msg.setCode(ResultEmuns.SUCCESS.getCode());
		msg.setMessage(ResultEmuns.SUCCESS.getMessage());
		msg.setResult(user_name);
		
		return msg;
	}
	///////////////////////////////////商家信息////////////////////////////////////////
	
	//商家信息
	@GetMapping("getShopInfo")
	public Message getShopInfo(ShopInfo si){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getShopInfo");
		if(per == 1){
			ShopInfo shopInfo = shopService.getShopInfo(si);
			if(shopInfo != null){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(shopInfo);
			}else{
				msg.setCode(ResultEmuns.NODATA.getCode());
				msg.setMessage(ResultEmuns.NODATA.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//商家详细信息
	@GetMapping("getShopDetail")
	public Message getShopDetail(@Validated PageVo vo,BindingResult result){
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("getShopDetail");
			if(per == 1){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				if("1".equals(vo.getType())){
					//优惠活动
					Page<ShopActivities> shopActivities = shopService.getShopActivities(vo);
					msg.setResult(shopActivities);
				}else if("2".equals(vo.getType())){
					//账户信息
					Page<UserAccount> shopAccount = service.getUserAccount(vo);
					msg.setResult(shopAccount);
				}else if("3".equals(vo.getType())){
					//订单信息
					Page<UserOrder> shopOrder = service.getUserOrder(vo);
					msg.setResult(shopOrder);
				}else if("4".equals(vo.getType())){
					//商品管理
					if(vo.getPageSize() == null){
						vo.setPageSize(20);         
					}
					if(vo.getPageNumber() == null){
						vo.setPageNumber(1);
					}
					Page<GoodsManage> gm = shopService.getGoodsManage(vo);
					List<GoodsManage> gmList = gm.getDataList();
					for(GoodsManage good:gmList){
						String class_list = good.getClass_list();
						String title = "";
						if(class_list != null && !"".equals(class_list)){
							class_list = class_list.substring(1, class_list.length()-1);
							String [] classArr = class_list.split(",");
							for(String catetory_id:classArr){
								String catTitle = shopService.getCategoryTitle(catetory_id);
								title += catTitle + ">";
							}
							title = title.substring(0,title.length()-1);
						}
						title = title == "" ? "暂无分类":title;
						good.setCategory_title(title);
					}
					msg.setResult(gm);
				}else if("5".equals(vo.getType())){
					if(vo.getPageSize() == null){
						vo.setPageSize(20);         
					}
					if(vo.getPageNumber() == null){
						vo.setPageNumber(1);
					}
					Page<CommentManage> page = shopService.getCommentManage(vo);
					List<CommentManage> commentManage = page.getDataList();
					for(CommentManage cm:commentManage){
						
						switch(cm.getStatus()){
						case  0:
							cm.setState("待审核");
							break;
						case 1:
							cm.setState("已通过");
							break;
						case 2:
							cm.setState("未通过");
							break;
						default:
							cm.setState("");
						}
					}
					msg.setResult(page);
				}else if("6".equals(vo.getType())){
					//来电记录
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					if(pageNumber == null){
						pageNumber = 1;
					}
					if(pageSize == null){
						pageSize = 3;
					}
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<CallRegister> callRegister = utilsService.callRegister(vo.getPageNumber(), vo.getPageSize(),vo.getMobile());
					msg.setResult(callRegister);
				}else if("7".equals(vo.getType())){
					//工单信息
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					if(pageNumber == null){
						pageNumber = 1;
					}
					if(pageSize == null){
						pageSize = 10;
					}
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<SheetInfo> sheetInfo = utilsService.sheetInfo(vo.getMobile(), vo.getPageNumber(), vo.getPageSize());
					msg.setResult(sheetInfo);
				}
				
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		
		return msg;
	}
	
	//商家商品数量统计（0正常，1未审核，2锁定，3拒绝，4草稿）不传代表总数
	@GetMapping("countGoods")
	public Message countGoods(@RequestParam Integer user_id,Integer status){
		
		Message msg = new Message();
		Integer per = perService.getConnector("countGoods");
		if(per == 1){
			Integer count = shopService.countGoods(user_id,status);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(count);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//商品上架下架数量（0下架，1上架）
	@GetMapping("countPutaway")
	public Message countPutaway(@RequestParam Integer user_id,@RequestParam Integer is_show){
		
		Message msg = new Message();
		Integer per = perService.getConnector("countPutaway");
		if(per == 1){
			Integer count = shopService.countPutaway(user_id, is_show);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(count);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//商品数量统计
	@GetMapping("totalArticle")
	public Message totalArticle(@RequestParam Integer user_id){
		Message msg = new Message();
		Integer per = perService.getConnector("totalArticle");
		Map<String,Object> map = new HashMap<String,Object>();
		if(per == 1){
			//商品总量
			Integer allArticle = shopService.countGoods(user_id,null);
			//未通过审核数量
			Integer refuseArticle = shopService.countGoods(user_id, 3);
			//下架数量
			Integer downCount = shopService.countPutaway(user_id, 0);
			//上架数量
			Integer upCount = shopService.countPutaway(user_id, 1);
			map.put("allCount", allArticle);
			map.put("refuseArticle", refuseArticle);
			map.put("downCount", downCount);
			map.put("upCount", upCount);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	
	////////////////////代理商///////////////////////
	
	//代理商信息
	@GetMapping("getAgentInfo")
	public Message getAgentInfo(AgentInfo ai){//参数user_name;telephone
		
		Message msg = new Message();
		Integer per = perService.getConnector("getAgentInfo");
		if(per == 1){
			AgentInfo agentInfo = agentService.getAgentInfo(ai);
			if(agentInfo != null){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(agentInfo);
			}else{
				msg.setCode(ResultEmuns.NODATA.getCode());
				msg.setMessage(ResultEmuns.NODATA.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//代理商详情
	@GetMapping("getAgentDetail")
	public Message getAgentDetail(@Validated({PAGENUMBER.class,PAGESIZE.class,MOBILE.class,TYPE.class}) PageVo vo,BindingResult result){
		
		Message msg = new Message();
		
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("getAgentDetail");
			if(per == 1){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				if("1".equals(vo.getType())){
					//来电记录
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<CallRegister> callRegister = utilsService.callRegister(vo.getPageNumber(), vo.getPageSize(),vo.getMobile());
					msg.setResult(callRegister);
				}else if("2".equals(vo.getType())){
					//工单信息
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					if(pageNumber == null){
						pageNumber = 1;
					}
					if(pageSize == null){
						pageSize = 10;
					}
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<SheetInfo> sheetInfo = utilsService.sheetInfo(vo.getMobile(), vo.getPageNumber(), vo.getPageSize());
					msg.setResult(sheetInfo);
				}else{
					msg.setCode(ResultEmuns.PARAM.getCode());
					msg.setMessage(ResultEmuns.PARAM.getMessage());
				}
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		
		return msg;
	}
	
	/////////////////////其他/////////////////////////
	
	@GetMapping("getOtherInfo")
	public Message getOtherInfo(@RequestParam String mobile){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getOtherInfo");
		if(per == 1){
			OtherInfo otherInfo = otherService.getOtherInfo(mobile);
			if(otherInfo != null){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(otherInfo);
			}else{
				msg.setCode(ResultEmuns.NODATA.getCode());
				msg.setMessage(ResultEmuns.NODATA.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//其他的来电记录
	@GetMapping("getOtherDetail")
	public Message getOthserDetail(@Validated({PAGENUMBER.class,PAGESIZE.class,MOBILE.class,Type.class}) PageVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("getOtherDetail");
			if(per == 1){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				if("1".equals(vo.getType())){
					//其他来电记录
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					if(pageNumber == null){
						pageNumber = 1;
					}
					if(pageSize == null){
						pageSize = 10;
					}
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<CallRegister> callRegister = utilsService.callRegister( vo.getPageNumber(), vo.getPageSize(),vo.getMobile());
					msg.setResult(callRegister);
				}else if("2".equals(vo.getType())){
					//工单信息
					Integer pageNumber = vo.getPageNumber();
					Integer pageSize = vo.getPageSize();
					if(pageNumber == null){
						pageNumber = 1;
					}
					if(pageSize == null){
						pageSize = 10;
					}
					vo.setPageNumber((pageNumber-1)*pageSize);
					vo.setPageSize(pageSize);
					Page<SheetInfo> sheetInfo = utilsService.sheetInfo(vo.getMobile(), vo.getPageNumber(), vo.getPageSize());
					msg.setResult(sheetInfo);
				}else{
					msg.setCode(ResultEmuns.PARAM.getCode());
					msg.setMessage(ResultEmuns.PARAM.getMessage());
				}
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		
		return msg;
	}
	
	//////////////订单外呼/////////////
	
	@GetMapping("getOrderInfo")
	public Message getOrders(@RequestParam String jsonToString){//参数（status,start_time,end_time,keyword,pageSize,pageNumber）
		Message msg = new Message();
		
		JSONObject obj = JSONObject.parseObject(jsonToString);
		
		OrderVo vo = new OrderVo();
		if(obj.get("pageNumber") == null){
			vo.setPageNumber(1);
		}else{
			vo.setPageNumber(obj.getInteger("pageNumber"));
		}
		if(obj.get("pageSize").toString() == null){
			vo.setPageSize(10);
		}else{
			vo.setPageSize(obj.getInteger("pageSize"));
		}
		if(obj.get("keyword") != null){
			
			vo.setKeyword(obj.getString("keyword"));
		}
		if(obj.get("start_time") != null){
			
			vo.setStart_time(obj.getString("start_time"));
		}
		if(obj.get("end_time") != null){
			
			vo.setEnd_time(obj.getString("end_time"));
		}
		Integer per = perService.getConnector("getOrderInfo");
		
		if(per == 1){
			
			if("1".equals(obj.get("type").toString())){
			
				Page<Order> page = orderService.getOrders(vo);
				if(page.getPageCount() != 0){
					List<Order> orderList = page.getDataList();
					for(Order order:orderList){
//						if("1".equals(order.getIs_deliver())){
//							String diff_time = String.valueOf(Integer.parseInt(order.getDiff_time())+172800);
//							order.setDiff_time(diff_time);
//						}
						if(order.getBusiness_status() == 201){
							order.setDiff_time(Integer.valueOf(order.getDiff_time())+ 345600);
						}
						Integer count = utilsService.isInvolved(order.getOrder_id());
						if(count == 0){
							order.setIs_involved("否");
							order.setCount_involved(0);
						}else{
							order.setIs_involved("是");
							order.setCount_involved(count);
						}
					}
				}else{
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}
				msg.setCode(ResultEmuns.SUCCESS.getCode());
		     	msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(page);
			}else if("2".equals(obj.get("type").toString())){
				Page<OrderBack> page = orderService.getOrderBack(vo);
				if(page.getPageCount() != 0){
					List<OrderBack> orderBack = page.getDataList();
					for(OrderBack order:orderBack){
						Integer count = utilsService.isInvolved(order.getOrder_id());
						if(count == 0){
							order.setIs_involved("否");
							order.setCount_involved(0);
						}else{
							order.setIs_involved("是");
							order.setCount_involved(count);
						}
					}
				}else{
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}
				msg.setCode(ResultEmuns.SUCCESS.getCode());
		     	msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(page);
			}else{
				msg.setCode(ResultEmuns.PARAM.getCode());
				msg.setMessage(ResultEmuns.PARAM.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//订单详情
	@GetMapping("getOrderDetail")
	public Message getOrderDetail(@RequestParam Integer order_id){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getOtherDetail");
		if(per == 1){
			OrderDetail orderDetail = orderService.getOrderDetail(order_id);
			List<OrderSpec> orderSpec = orderService.getOrderSpec(order_id);
			Double money = 0d;
			for(OrderSpec order:orderSpec){
				money += order.getAll_money();
			}
			
			OrderPrice orderPrice = orderService.getOrderPrice(order_id);
			Map<String,Object> map = new HashMap<String,Object>();
			if(orderPrice != null){
				orderPrice.setGoods_price(money);
			}
			map.put("orderPrice", orderPrice);
			map.put("userShop", orderDetail);
			map.put("orderSpec", orderSpec);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//退单详情
	@GetMapping("getOrderBackDetail")
	public Message getOrderBackDetail(String order_no) throws Exception{
		
		Message msg = new Message();
		Integer per = perService.getConnector("getOrderBackDetail");
		if(per == 1){
			int order_id = orderService.getOrderIdByBackNo(order_no);
			OrderDetail orderDetail = orderService.getOrderDetail(order_id);
			List<OrderSpec> orderSpec = orderService.getOrderSpec(order_id);
			OrderBackInfo obi = orderService.getOrderBackInfo(order_no);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userShop", orderDetail);
			map.put("orderSpec", orderSpec);
			map.put("orderBack", obi);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		  
		return msg;
	}
	
	//传入Buyer,Shop,Agent查询对应数量
	@GetMapping("getCount")
	public Message getCount(@RequestParam String acceptType,@RequestParam String type){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getCount");
		if(per == 1){
			
			if(acceptType == null){
				msg.setCode(ResultEmuns.PARAM.getCode());
				msg.setMessage(ResultEmuns.PARAM.getMessage());
			}else{
				Integer count = messageService.getCount(acceptType,type);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(count);
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//发送短信
	@GetMapping("sendMessage")
	public Message sendMessage(@RequestParam String type,@RequestParam String mobile,@RequestParam String content){
		
		Message msg = new Message();
		Integer per = perService.getConnector("sendMessage");
		if(per == 1){
			if(mobile == null){
				msg.setCode(ResultEmuns.PARAM.getCode());
				msg.setMessage(ResultEmuns.PARAM.getMessage());
				return msg;
			}
			if(mobile.indexOf("，")>0){
				mobile = mobile.replace("，", ",");
			}
			String [] mobileArr = mobile.split(",");
			List<MessageObj> list = new ArrayList<MessageObj>();
			for(String tel:mobileArr){
				MessageObj messageObj = messageService.getMessageObj(tel);
				if(messageObj != null){
					list.add(messageObj);
				}else{
					MessageObj message = new MessageObj();
					message.setMobile(tel);
					list.add(message);
				}
			}	
			
			int score = 0;
			try{
				for(MessageObj mo:list){
					SendMessage sm = new SendMessage();
					sm.setType(type);
					sm.setContent(content);
					sm.setSend_status(0);
					sm.setAdd_time(TimeUtils.getTimeUtilSecond());
					sm.setSend_time(TimeUtils.getTimeUtilSecond());
					sm.setUser_ip(mo.getReg_ip());
					sm.setUser_id(mo.getUser_id());
					sm.setMobile(mo.getMobile());
					sm.setUser_role_type(mo.getRole_type());
					sm.setUser_role_value(mo.getRole_value());
					int count = messageService.addMessage(sm);
					score += count;
				}
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(score);
			}catch(Exception e){
				msg.setCode(ResultEmuns.ERROR.getCode());
				msg.setMessage(ResultEmuns.ERROR.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//获取所有电话
	@GetMapping("getMobileInfo")
	public Message getMobileInfo(@RequestParam String user_role,@RequestParam String type){//参数Buyer,Shop,Agent
		
		Message msg = new Message();
		List<Object> ms = messageService.getMobileInfo(user_role,type);
		Integer per = perService.getConnector("getMobileInfo");
		if(per == 1){
			if(ms.size()>0){
				
				//JSONArray json = JSONArray.fromObject(ms);
				//Object json = JSONArray.toJSON(ms);
				JSONArray json = JSONArray.parseArray(JSON.toJSONString(ms));
				if("mobile".equals(type)){
					String mobile = "";
					for(int i=0;i<json.size();i++){
						mobile += json.getJSONObject(i).getString("mobile")+",";
					}
					mobile = mobile.substring(0,mobile.length()-1);
					msg.setResult(mobile);
				}else if("email".equals(type)){
					String email = "";
					for(int j=0;j<json.size();j++){
						email += json.getJSONObject(j).getString("email")+",";
					}
					email = email.substring(0,email.length()-1);
					msg.setResult(email);
				}
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			}else{
				msg.setCode(ResultEmuns.NODATA.getCode());
				msg.setMessage(ResultEmuns.NODATA.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//商家信息中的资质图片
	@GetMapping("getShopCards")
	public Message getShopCards(@RequestParam Integer user_id){
		Message msg = new Message();
		List<Map<String,String>> map = shopService.getShopCards(user_id);
		if(map.size()>0){
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NODATA.getCode());
			msg.setMessage(ResultEmuns.NODATA.getMessage());
		}
		return msg;
	}
	
	///////////////////客户管理////////////////////////
	
	//用户管理
	@GetMapping("getUserManage")
	public Message getUserManage(@Validated ClientManageVo vo,BindingResult result){
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("getUserManage");
			if(per == 1){
				if(vo.getPageNumber()==null){
					vo.setPageNumber(1);
				}
				if(vo.getPageSize()==null){
					vo.setPageSize(20);
				}
				Page<UserManage> page = clientService.getUserManage(vo);
				if(page != null){
					msg.setCode(ResultEmuns.SUCCESS.getCode());
					msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					msg.setResult(page);
				}else{
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//更改账户状态
	@GetMapping("updAccountState")//state=0冻结,1正常，9异常
	public Message updAccountState(@RequestParam Integer user_id,@RequestParam int state) throws Exception{
		Message msg = new Message();
		Integer per = perService.getConnector("updAccountState");
		if(per == 1){
			int count = clientService.updAccountState(user_id, state);
			if(count>0){
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(count);
			}else{
				msg.setCode(ResultEmuns.ERROR.getCode());
				msg.setMessage(ResultEmuns.ERROR.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//商家管理
	@GetMapping("getShopManage")
	public Message getShopManage(@Validated ClientManageVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("getShopManage");
			if(per == 1){
				if(vo.getPageNumber()==null){
					vo.setPageNumber(1);
				}
				if(vo.getPageSize()==null){
					vo.setPageSize(20);
				}
				Page<ShopManage> page = clientService.getShopManage(vo);
				if(page != null){
					msg.setCode(ResultEmuns.SUCCESS.getCode());
					msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					msg.setResult(page);
				}else{
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//用户详情
	@GetMapping("getUserManageDetail")
	public Message getUserManageDetail(@RequestParam Integer user_id){
		
		Message msg = new Message();
		//String times = clientService.getPasswordTime(user_id, time,type);
		
		Integer per = perService.getConnector("getUserManageDetail");
		if(per == 1){
			//安全资料
			UserSafe us = clientService.getUserSafe(user_id);
			
			SafeInfo safeInfo = clientService.findSafeInfo(user_id);
			if(safeInfo != null){
				int a = 0;
				//登录密码
				if(safeInfo.getPassword() != null && !"".equals(safeInfo.getPassword())
						&& clientService.getPasswordTime(user_id, -3,"PASSWORD") != null){
					a += 1;
				}else{
					a -= 1;
				}
				//支付密码
				if(safeInfo.getPay_password() != null && !"".equals(safeInfo.getPay_password())
						&& clientService.getPasswordTime(user_id, -6, "PAYPASSWORD") != null){
					a += 2;
				}else{
					a += 1;
				}
				//邮箱绑定
				if(safeInfo.getEmail() != null && !"".equals(safeInfo.getEmail())){
					a += 2;
				}
				//手机号绑定
				if(safeInfo.getMobile() != null && !"".equals(safeInfo.getMobile())){
					a += 2;
				}
				//实名认证
				if(safeInfo.getPerson_name() != null && !"".equals(safeInfo.getPerson_name())){
					a += 3;
				}
				//账户安全等级
				if(a <= 4){
					us.setAccount_safe("低");
				}else if(a >4 && a <= 7){
					us.setAccount_safe("中");
				}else{
					us.setAccount_safe("高");
				}
				UserInfo userInfo = clientService.findUserInfo(user_id);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("safeInfo", us);
				map.put("userInfo", userInfo);
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(map);
				
			}
		
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	
	//用户管理详情信息
	@GetMapping("findUserAccount")
	public Message findUserAccount(@Validated AccountVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("findUserAccount");
			if(per == 1){
				if(vo.getUser_type() == 1){
					Page<UserAccount> list = clientService.findUserAccount(vo);
					if(list.getDataList().size()>0){
						List<UserAccount> userList = list.getDataList();
						List<OrderTypeEnums> enums = OrderTypeEnums.getAllEnum();
						
						for(UserAccount user : userList){
							for(OrderTypeEnums en : enums){
								if(user.getType().equals(en.toString())){
									user.setType(OrderTypeEnums.getMsgByCode(en.toString()));
								}
							}
							
							Integer order_id = clientService.findOrderIdByOrderNo(user.getOrder_no());
							Integer order_back_id = orderService.getOrderIdByBackNo(user.getOrder_no());
							if(order_back_id != null){
								user.setOrder_type("2");
							}else if(order_back_id == null && order_id != null){
								user.setOrder_type("1");
							}else{
								user.setOrder_type("3");
							}
						}
					}
					msg.setResult(list);
				}else if(vo.getUser_type() == 2){
					Page<UserVoucher> list = clientService.findUserVoucher(vo);
					if(list.getDataList().size()>0){
						List<UserVoucher> userList = list.getDataList();
						List<OrderTypeEnums> enums = OrderTypeEnums.getAllEnum();
						for(UserVoucher user : userList){
							for(OrderTypeEnums en : enums){
								if(user.getType().equals(en.toString())){
									user.setType(OrderTypeEnums.getMsgByCode(en.toString()));
								}
							}
							
							Integer order_id = clientService.findOrderIdByOrderNo(user.getOrder_no());
							Integer order_back_id = orderService.getOrderIdByBackNo(user.getOrder_no());
							if(order_back_id != null){
								user.setOrder_type("2");
							}else if(order_back_id == null && order_id != null){
								user.setOrder_type("1");
							}else{
								user.setOrder_type("3");
							}
						}
					}
					msg.setResult(list);
				}
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//用户管理的订单
	@GetMapping("findUserOrder")
	public Message findUserOrder(@Validated UserOrderVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("findUserOrder");
			if(per == 1){
				Page<UserOrder> page = clientService.findUserOrder(vo);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(page);
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//用户管理的退单
	@GetMapping("findOrderBack")
	public Message findOrderBack(@Validated OrderBackVo vo,BindingResult result){//多传个type，如果type=1查最近三个月的
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("findOrderBack");
			if(per == 1){
				Page<UserBack> page = clientService.findOrderBack(vo);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(page);
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//用户管理的登录记录
	@GetMapping("findUserLogin")
	public Message findUserLogin(@Validated UserLoginVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("findUserLogin");
			if(per == 1){
				Page<UserLogin> page = clientService.findUserLogin(vo);
				if(page.getDataList().size()>0){
					msg.setCode(ResultEmuns.SUCCESS.getCode());
					msg.setMessage(ResultEmuns.SUCCESS.getMessage());
					msg.setResult(page);
				}else{
					msg.setCode(ResultEmuns.NODATA.getCode());
					msg.setMessage(ResultEmuns.NODATA.getMessage());
				}
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//根据订单号查询订单id
	@GetMapping("findOrderIdByOrderNo")
	public Message findOrderIdByOrderNo(@RequestParam String order_no){
		Message msg = new Message();
		Integer per = perService.getConnector("findOrderIdByOrderNo");
		if(per == 1){
			if(order_no != null){
				Integer order_id = clientService.findOrderIdByOrderNo(order_no);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(order_id);
			}else{
				msg.setCode(ResultEmuns.PARAM.getCode());
				msg.setMessage(ResultEmuns.PARAM.getMessage());
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//商家详情
	@GetMapping("getShopManageDetail")
	public Message getShopManageDetail(@RequestParam Integer shop_id){
		
		Message msg = new Message();
		Integer per = perService.getConnector("getShopManageDetail");
		if(per == 1){
			Integer user_id = clientService.findShopIdByUserId(shop_id);
			ShopReputation shop = clientService.findReputationInfo(user_id);
			if(shop == null){
				msg.setCode(ResultEmuns.NODATA.getCode());
				msg.setMessage(ResultEmuns.NODATA.getMessage());
			}else{
				Integer score = clientService.getShopReputation(shop.getShop_id());
				shop.setScore(score);
				if(score < 100){
					shop.setGrade("E");
				}else if(score >= 100 && score < 1000){
					shop.setGrade("D");
				}else if(score >= 1000 && score < 4000){
					shop.setGrade("C");
				}else if(score >= 4000 && score < 12000){
					shop.setGrade("B");
				}else{
					shop.setGrade("A");
				}
				ShopInfo si = new ShopInfo();
				si.setUser_id(user_id);
				ShopInfo shopInfo = shopService.getShopInfo(si);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("shopScore", shop);
				map.put("shopInfo", shopInfo);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(map);
			}
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//商家活动
	@GetMapping("findShopActivities")
	public Message findShopActivities(@Validated ActivitiesVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("findShopActivities");
			if(per == 1){
				Page<ShopActivities> page = clientService.findShopActivities(vo);
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(page);
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//商品管理
	@GetMapping("findGoodsManage")
	public Message findGoodsManage(GoodsManageVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("findGoodsManage");
			if(per == 1){
				Page<GoodsManage> page = clientService.findGoodsManage(vo);
				
				List<GoodsManage> gmList = page.getDataList();
				for(GoodsManage good:gmList){
					String class_list = good.getClass_list();
					String title = "";
					if(class_list != null && !"".equals(class_list)){
						class_list = class_list.substring(1, class_list.length()-1);
						String [] classArr = class_list.split(",");
						for(String catetory_id:classArr){
							String catTitle = shopService.getCategoryTitle(catetory_id);
							title += catTitle + ">";
						}
						title = title.substring(0,title.length()-1);
					}
					title = title == "" ? "暂无分类":title;
					good.setCategory_title(title);
				}
				
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(page);
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//自定义分类
	@GetMapping("findSelfCategory")
	public Message findSelfCategory(@RequestParam Integer user_id){
		Message msg = new Message();
		Integer per = perService.getConnector("findSelfCategory");
		if(per == 1){
			List<String> list = clientService.findSelfCategory(user_id);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(list);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//根据分类parent_id查title
	@GetMapping("findTitleByParentId")
	public Message findTitleByParentId(@RequestParam Integer parent_id){
		Message msg = new Message();
		Integer per = perService.getConnector("findTitleByParentId");
		if(per == 1){
			List<Object> list = clientService.findTitleByParentId(parent_id);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(list);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//店铺评分
	@GetMapping("findShopScore")
	public Message findShopScore(@RequestParam Integer user_id){
		Message msg = new Message();
		Integer per = perService.getConnector("findShopScore");
		if(per == 1){
			Object list = clientService.findShopScore(user_id);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(list);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//评价管理
	@GetMapping("findCommentManage")
	public Message findCommentManage(@Validated({PAGENUMBER.class,PAGESIZE.class,USERID.class}) PageVo vo,BindingResult result){
		
		Message msg = new Message();
		if(result.hasErrors()){
			msg.setCode(ResultEmuns.PARAM.getCode());
			msg.setMessage(ResultEmuns.PARAM.getMessage());
		}else{
			
			Integer per = perService.getConnector("findCommentManage");
			if(per == 1){
				Page<CommentManage> page = shopService.getCommentManage(vo);
				List<CommentManage> commentManage = page.getDataList();
				for(CommentManage cm:commentManage){
					
					switch(cm.getStatus()){
					case  0:
						cm.setState("待审核");
						break;
					case 1:
						cm.setState("已通过");
						break;
					case 2:
						cm.setState("未通过");
						break;
					default:
						cm.setState("");
					}
				}
				msg.setCode(ResultEmuns.SUCCESS.getCode());
				msg.setMessage(ResultEmuns.SUCCESS.getMessage());
				msg.setResult(page);
			}else{
				msg.setCode(ResultEmuns.NOACCESS.getCode());
				msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			}
		}
		return msg;
	}
	
	//商品详情
	@GetMapping("findGoodsDetail")
	public Message findGoodsDetail(@RequestParam Integer article_id){
		
		Message msg = new Message();
		Integer per = perService.getConnector("findGoodsDetail");
		if(per == 1){
			ArticleInfo articleInfo = clientService.findArticleInfo(article_id);
			try{
				Map<String,Object> map = new HashMap<String,Object>();
				String class_list = articleInfo.getClass_list();
				if(class_list != null && !"".equals(class_list)){
					
					class_list = class_list.substring(1,class_list.length()-1);
					String[] list = class_list.split(",");
					List<Map<String, Object>> goodsCategory = clientService.findGoodsCategory(list);
					String category = "";
					for(Map<String, Object> xx : goodsCategory){
						category += xx.get("title")+">";
					}
					category = category.substring(0,category.length()-1);
					map.put("category", category);
				}else{
					map.put("category", null);
				}
				String selfTitle = clientService.findDefaultCategory(article_id);
				if(selfTitle != null && !"".equals(selfTitle)){
					map.put("selfTitle", selfTitle);
				}else{
					map.put("selfTitle", "默认分类");
				}
				Map<String, Object> goodsDetial = clientService.findGoodsDetail(article_id);
				String goods_expenses = goodsDetial.get("goods_expenses").toString();
				JSONObject goodsObj = JSON.parseObject(goods_expenses);
				Integer kg = goodsObj.getInteger("kg");
				Double cm = goodsObj.getDouble("cm");
				Integer expenses_companyId = goodsObj.getInteger("expenses_companyId");
				Integer expenses_tempId = goodsObj.getInteger("expenses_tempId");
				Object express_company = clientService.findExpress(expenses_companyId);
				String expenses_temp = clientService.findExpensesTemp(expenses_tempId);
				List<ArticleSpec> articleSpec = clientService.findArticleSpec(article_id);
				List<Object> list = new ArrayList<>();
				Double sell_price;
				Integer stock_quantity;
				String spec_text;
				String goods_no;
				Integer goods_id;
				for(int i = 0;i < articleSpec.size();i++){
					
					spec_text = articleSpec.get(i).getSpec_text();
					String [] specArr = spec_text.split("，");
					Map<String, Object> specMap = new HashMap<>();
					List<Object> list1 = new ArrayList<>();
					for(int j = 0;j<specArr.length;j++){
						String [] spec = specArr[j].split("：");
						Map<String,Object> map1 = new HashMap<>();
						for(int k = 0;k < spec.length; k++){
							map1.put("value", spec[1]);
							map1.put("key", spec[0]);
						}
						list1.add(map1);
					}
					specMap.put("goods_spec", list1);
					sell_price = articleSpec.get(i).getSell_price();
					stock_quantity = articleSpec.get(i).getStock_quantity();
					goods_no = articleSpec.get(i).getGoods_no();
					goods_id = articleSpec.get(i).getGoods_id();
					int count = clientService.countHavingSell(goods_id);
					specMap.put("sell_count", count);
					specMap.put("sell_price", sell_price);
					specMap.put("goods_no", goods_no);
					specMap.put("stock_quantity", stock_quantity);
					list.add(specMap);
				}
				map.put("spec_info", list);
				map.put("kg", kg);//重量
				map.put("cm", cm);//体积
				map.put("express_company", express_company);//快递信息
				map.put("expenses_temp", expenses_temp);//模板
				map.put("sub_title", goodsDetial.get("sub_title"));//卖点
				map.put("goods_describe", goodsDetial.get("goods_describe"));//属性
				map.put("brand", goodsDetial.get("name"));//商标
				//商品标题
				map.put("shopTitle", articleInfo.getTitle());
				
				msg.setResult(map);
				
			}catch(NullPointerException e){
				msg.setCode(ResultEmuns.ERROR.getCode());
				msg.setMessage(ResultEmuns.ERROR.getMessage());
			}
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		
		return msg;
	}
	
	//图文详情
	@GetMapping("findGoodsPics")
	public Message findGoodsPics(@RequestParam Integer article_id){
		
		Message msg = new Message();
		
		Integer per = perService.getConnector("findGoodsPics");
		if(per == 1){
			String img_url = clientService.findGoodsPics(article_id);
			List<Map<String,String>> thumb_path = clientService.findGoodsAlbums(article_id);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("img_url", img_url);
			map.put("photo_thumb", thumb_path);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//客户管理评价详情
	@GetMapping("findCommentInfo")
	public Message findCommentInfo(@RequestParam Integer order_id){
		Message msg = new Message();
		
		Integer per = perService.getConnector("findCommentInfo");
		if(per == 1){
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,String> baseInfo = clientService.findCommentInfo(order_id);
			JSONObject obj = JSONObject.parseObject(JSON.toJSONString(baseInfo));
			Integer user_id = obj.getInteger("user_id");
			Object list = clientService.findShopScore(user_id);
			List<Map<String, Object>> commentScore = clientService.findCommentScore(order_id);
			for(Map<String, Object> cs : commentScore){
				Integer id = Integer.parseInt(cs.get("id").toString());
				List<Map<String,Object>> comment = clientService.findCommentContent(id);
				cs.put("comment", comment);
				System.out.println(comment);
			}
			
			map.put("commentScore", commentScore);
			map.put("shop_comment", list);
			map.put("baseInfo", baseInfo);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//公司
	@GetMapping("findAptitudeInfo")
	public Message findAptitudeInfo(@RequestParam Integer shop_id){
		Message msg = new Message();
		
		Integer per = perService.getConnector("findAptitudeInfo");
		if(per == 1){
			Integer user_id = clientService.findShopIdByUserId(shop_id);
			
			Map<String,String> companyInfo = clientService.findCompanyInfo(user_id);
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String,String>> cardInfo = clientService.findCardInfo(shop_id);
			map.put("companyInfo", companyInfo);
			map.put("cardInfo", cardInfo);
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
	
	//店铺信息
	@GetMapping("findShopCard")
	public Message findShopCard(@RequestParam Integer shop_id){
		
		Message msg = new Message();
		
		Integer per = perService.getConnector("findShopCard");
		if(per == 1){
			Map<String, String> shopCard = clientService.findShopCard(shop_id);
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String,String>> cardInfo = clientService.findCardInfo(shop_id);
			map.put("shopCard", shopCard);
			map.put("cardInfo", cardInfo);
			
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(map);
		}else{
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
		}
		return msg;
	}
}
