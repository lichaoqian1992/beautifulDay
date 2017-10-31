package com.manji.mlife.controller;

import com.manji.mlife.service.DBService;
import com.manji.mlife.service.GkServiceImpl;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.common.QmcsMessage;
import com.qianmi.open.api.request.QmcsMessagesConfirmRequest;
import com.qianmi.open.api.request.QmcsMessagesConsumeRequest;
import com.qianmi.open.api.request.QmcsUserPermitRequest;
import com.qianmi.open.api.response.QmcsMessagesConsumeResponse;
import com.qianmi.open.api.response.QmcsUserPermitResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class QmcsController {

	@Autowired
	private DBService service;

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	
    private static final Logger logger =Logger.getLogger(GkServiceImpl.class);

    /**
     * 为已授权的用户开通消息服务
     * @author gaochao
     * @param model
     * @return
     * @throws ApiException
     * 2016年8月31日上午11:22:08
     * String
     *
     */
	@RequestMapping(value="/qmcs/start",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	public String startQmcs(Model model) throws ApiException {
		System.out.println("------------进入授权服务...");
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		QmcsUserPermitRequest request = new QmcsUserPermitRequest();
		QmcsUserPermitResponse response = client.execute(request, map.get("accessToken"));
		
		logger.info("QmcsUserPermitRequest"+JSONObject.fromObject(response).toString());

		if (response.isSuccess()) {
			
			// 启动定时任务，定时消费消息
			startConsume();
			
			return "platform/qmcs-success";
		} else {
			model.addAttribute("errMsg", response.getErrorCode() + ": " + response.getMsg());
			return "platform/qmcs-fail";
		}
		
	}
	private void startConsume() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    // 调用消费消息接口，指定消费数量为100
                	Map<String, String> map = service.getBasePara();
            		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
                    QmcsMessagesConsumeRequest consumeRequest = new QmcsMessagesConsumeRequest();
                    consumeRequest.setQuantity(100);
                    QmcsMessagesConsumeResponse consumeResponse = client.execute(consumeRequest, map.get("accessToken"));
                    
            		logger.info("QmcsMessagesConsumeRequest"+JSONObject.fromObject(consumeResponse).toString());

                    if (CollectionUtils.isEmpty(consumeResponse.getQmcsMessages())) {
                    	System.out.println("---------暂无消费信息----------");
                        return;
                    }
                    final List<QmcsMessage> qmcsMessages = consumeResponse.getQmcsMessages();
                    // 启动线程处理消息
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                        	System.out.println("---------+启动线程处理消息...."+qmcsMessages);
                            handleMessages(qmcsMessages);
                        }
                    });
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        };
        // 启动定时任务，每10秒钟消费一次
        new Timer("qmcs-timer").schedule(task, 1 * 1000, 30 * 1000);
    }
	
	
	 private void handleMessages(List<QmcsMessage> qmcsMessages) {
	        // 处理消息，并将处理完成的消息id加入到handledMessageIds
	        StringBuilder handledMessageIds = new StringBuilder();
	        boolean handleComplete;
	        for (QmcsMessage message : qmcsMessages) {
	            handleComplete = false;
	            // 处理订单充值消息
	            if ("qianmi_elife_rechargeStateChange".equals(message.getTopic())) {
	                // 处理消息内容
            		logger.info("-------------+ 处理订单充值消息0......"+message.getTopic());

	            	System.out.println("-------------+ 处理订单充值消息0......"+message.getTopic());
	                if (handleOrder(message.getContent())) {
	                	
	                	System.out.println("--------------处理订单充值消息1"+message.getContent());
	                	
	                    handleComplete = true;
	                }
	            }
	            // 处理票务消息
	            else if ("qianmi_elife_ticketStateChange".equals(message.getTopic())) {
	            	
            		logger.info("-------------+ 处理订单处理票务消息0......"+message.getTopic());

	            	System.out.println("-------------+ 处理订单处理票务消息0......"+message.getTopic());

	            	if (handleTrafficOrder(message.getContent())) {
	            		System.out.println("--------------处理票务充值消息1"+message.getContent());
	                    handleComplete = true;
	                }
	            }
	            // 只加入已处理完成的消息id
	            if (handleComplete) {
	                handledMessageIds.append(message.getId()).append(",");
	            }
	        }
	        // 对已处理完的消息进行消费确认
	        try {
	        	System.out.println("-------------------对已处理完的消息进行消费确认------------------");
	        	Map<String, String> map = service.getBasePara();
        		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
                QmcsMessagesConfirmRequest confirmRequest = new QmcsMessagesConfirmRequest();
	            confirmRequest.setsMessageIds(handledMessageIds.deleteCharAt(handledMessageIds.length() - 1).toString());
	            client.execute(confirmRequest, map.get("accessToken"));
	        } catch (ApiException e) {
	            e.printStackTrace();
	        }
	    }
	
	 
	    private boolean handleOrder(String content) {
	        // TODO 处理消息内容，更新本地订单充值状态，根据充值状态做相应处理
	    	
	    	System.out.println("--------+更新本地订单充值状态，根据充值状态做相应处理3...."+content);
	    	JSONObject obj =JSONObject.fromObject(content);
	    	String tid =obj.getString("tid");
	    	String timestamp =obj.getString("timestamp");
	    	String recharge_state =obj.getString("recharge_state");

	    	System.out.println("----tid="+tid+"---timestamp="+timestamp+"------recharge_state="+recharge_state);
	    	service.comfirmBill(tid,recharge_state,timestamp);

	        return true;
	    }
	    

	    private boolean handleTrafficOrder(String content) {
	        // TODO 处理消息内容，更新本地订单充值状态，根据充值状态做相应处理
	    	
	    	JSONObject obj =JSONObject.fromObject(content);
	    	String trade_no =obj.getString("trade_no");
	    	String timestamp =obj.getString("timestamp");
	    	String ticket_state =obj.getString("ticket_state");
	    	String reason =obj.getString("reason");

	    	service.comfirmTrafficBill(trade_no,ticket_state,timestamp,reason);

	        return true;
	    }

}
