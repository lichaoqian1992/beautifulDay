package com.manji.lineol.aop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.manji.lineol.util.CollectionsUtils;
import com.manji.lineol.util.HttpUtils;
import com.manji.lineol.util.MD5Util;
import com.manji.lineol.util.RedisListOpsUtils;
import com.manji.lineol.util.StringUtils;
import com.manji.lineol.vo.LineVo;

@EnableAspectJAutoProxy
@Aspect
@Component
public class WebServiceAspect {
	
	private static Logger logger=Logger.getLogger(WebServiceAspect.class);

	@Autowired
	private RedisListOpsUtils<LineVo> redisOptList;

	@Autowired
	private RedisTemplate<String, Map<String, String>> redisTemplate;

	@Value("#{configProperties['pushUrl']}")  
	private String pushUrl;

	private static final String TITLE = "您有一条新消息。";

	private static final String CONTENT = "餐厅预约编号即将到您。请前往就餐。";

	public static void main(String[] args) {
		Map<String, String> param = new HashMap<>();
		long time = new Date().getTime() / 1000;
		// 时间戳
		param.put("currentDate", String.valueOf(time));
		StringBuilder roundNumber = new StringBuilder();
		roundNumber.append(time);
		roundNumber.append("1133837");
		roundNumber.append("Buyer");
		roundNumber.append("1133791");
		roundNumber.append(TITLE);
		roundNumber.append(CONTENT);
		roundNumber.append("");
		System.out.println(roundNumber);
		String md5Encrypt = DigestUtils.md5Hex(roundNumber.toString());
		// String md5Encrypt = MD5Util.md5Encrypt(roundNumber.toString());
		System.out.println(md5Encrypt);
		param.put("roundNumber", md5Encrypt);
		param.put("isResponseJson", "1");
		param.put("loginType", "Third");
		param.put("userId", "1133837");
		param.put("userRoleType", "Buyer");
		param.put("userRoleValue", "1133791");
		param.put("title", TITLE);
		param.put("content", CONTENT);
		param.put("sendType", "");
//		sendMsg(param);

	}

	/**
	 * 推送消息
	 * 
	 * @param publicParam
	 *            公共参数
	 * @param privateParam
	 *            业务参数
	 */
	public void sendMsg(Map<String, String> params) {
		String resultJson = HttpUtils.doGet(pushUrl, params);
		System.out.println(resultJson);

	}

	@AfterReturning("execution(* com.manji.lineol.service.ShopServiceImpl.nextNumber(..))")
	public void doAfter(JoinPoint joinPoint) {
		try {
			Object[] args = joinPoint.getArgs();
			StringBuilder builder = new StringBuilder(args[0].toString());
			builder.append(",");
			builder.append(args[1]);
			String key = builder.toString();
			if (StringUtils.isNotEmpty(key)) {
				Set<String> sets = redisOptList.keys(key);
				for (String k : sets) {
					List<LineVo> list = redisOptList.range(k, 0, -1);
					if (!CollectionsUtils.isEmptyCollection(list) && list.size() >= 3) {
						LineVo lineVo = redisOptList.index(key, 2);
						if (lineVo != null) {
							Map<String, String> map = redisTemplate.opsForValue().get(lineVo.getUserId());
							if (!CollectionsUtils.isEmptyMap(map)) {
								Map<String, String> param = new HashMap<>();
								long time = new Date().getTime() / 1000;
								// 时间戳
								param.put("currentDate", String.valueOf(time));
								StringBuilder roundNumber = new StringBuilder();
								roundNumber.append(time);
								roundNumber.append(map.get("user_id"));
								roundNumber.append(map.get("user_role_type"));
								roundNumber.append(map.get("user_role_value"));
								roundNumber.append(TITLE);
								roundNumber.append(CONTENT);
								roundNumber.append("");
								String md5Encrypt = DigestUtils.md5Hex(roundNumber.toString());
								param.put("roundNumber", md5Encrypt);
								param.put("isResponseJson", "1");
								param.put("loginType", "Third");
								param.put("userId", map.get("user_id"));
								param.put("userRoleType", map.get("user_role_type"));
								param.put("userRoleValue", map.get("user_role_value"));
								param.put("title", TITLE);
								param.put("content", CONTENT);
								param.put("sendType", "");
								sendMsg(param);
								break;
							}
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("忽略消息推送错误"+e.getMessage());
		}

	}

}
