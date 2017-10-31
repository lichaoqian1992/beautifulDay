package com.manji.lineol.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manji.lineol.service.ShopService;
import com.manji.lineol.util.RedisListOpsUtils;
import com.manji.lineol.vo.LineVo;

@RestController
public class HomeController {

	@Autowired
	private DriverManagerDataSource dataSource;

	@Autowired
	private RedisListOpsUtils<Student> RedisListOpsUtils;
	
	@Autowired
	private RedisListOpsUtils<LineVo> opt;
	
	@Autowired
	private ShopService shopService;
	
	
	@Value("#{configProperties['name']}")  
	private  String name;
	
    

	@RequestMapping("/hello")
	public String hello() {
		try {
			Connection connection = dataSource.getConnection();
			System.out.println(connection);
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}

		return "success";
	}

	@RequestMapping("/testListRedis")
	public String testListRedis() {

		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setId("100" + i);
			student.setAge(i);
			student.setName("张三" + i);
			RedisListOpsUtils.rightPush("queueStudent", student);
		}
		List<Student> range = RedisListOpsUtils.range("queueStudent", 0, -1);
		System.out.println(range);
		return "SUCCESS";
	}

	@RequestMapping("/testpop")
	public Student testpop() {
		Student index = RedisListOpsUtils.index("queueStudent", 0);
		return index;
	}

	@RequestMapping("/leftPop")
	public Student leftPop() {
		Student leftPop = RedisListOpsUtils.leftPop("queueStudent");
		return leftPop;
	}

	@RequestMapping("/remove")
	public List<Student> rightPush() {
		Student index = RedisListOpsUtils.index("queueStudent", 0);
		RedisListOpsUtils.remove("queueStudent", 0, index);
		List<Student> range = RedisListOpsUtils.range("queueStudent", 0, -1);
		System.out.println(range);
		return range;
	}

	@RequestMapping("/test")
	public Set<String> test() {
		
		RedisListOpsUtils.del("queueStudent");
		
		Set<String> keys = RedisListOpsUtils.keys("queueStudent");

		return keys;
	}
	
	@RequestMapping("/queryQueue/{key}")
	public Object queryQueue(@PathVariable String key){
		return opt.range(key, 0, -1);
	}
	
	@RequestMapping("/testaop")
	public String testAop(){
		
		return "SUCCESS";
	}
	
	
	
	@RequestMapping("/systemParamConfig")
	public String testConfig(){
		System.out.println(name);
		return "success";
		
	}

}
