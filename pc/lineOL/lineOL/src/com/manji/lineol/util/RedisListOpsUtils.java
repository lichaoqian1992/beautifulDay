package com.manji.lineol.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.manji.lineol.test.Student;

/**
 * redis-List队列操作
 * 
 * @author pudding
 *
 */
@Component
public class RedisListOpsUtils<T> {

	@Autowired
	private RedisTemplate<String, T> template;

	/**
	 * 压栈
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long leftPush(String key, T value) {
		return template.opsForList().leftPush(key, value);
	}

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */
	public T leftPop(String key) {
		return template.opsForList().leftPop(key);
	}

	/**
	 * 入队
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long rightPush(String key, T value) {
		return template.opsForList().rightPush(key, value);
	}

	/**
	 * 出队
	 * 
	 * @param key
	 * @return
	 */
	public T rightPop(String key) {
		return template.opsForList().rightPop(key);
	}

	/**
	 * 栈/队列长
	 * 
	 * @param key
	 * @return
	 */
	public Long size(String key) {
		return template.opsForList().size(key);
	}

	/**
	 * 范围检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<T> range(String key, int start, int end) {
		return template.opsForList().range(key, start, end);
	}

	/**
	 * 移除
	 * 
	 * @param key
	 * @param i
	 * @param index
	 */
	public void remove(String key, long i, T index) {
		template.opsForList().remove(key, i, index);
	}

	/**
	 * 检索 获取指定位置的元素
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public T index(String key, long index) {
		return template.opsForList().index(key, index);
	}

	/**
	 * 置值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, T value) {
		template.opsForList().set(key, index, value);
	}

	/**
	 * 裁剪
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) {
		template.opsForList().trim(key, start, end);
	}

	/**
	 * 删除指定队列
	 * 
	 * @param key
	 */
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				template.delete(key[0]);
			} else {
				template.delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	
	
	public void del(Collection coll){
		if (coll != null && coll.size() > 0) {
			template.delete(coll);
		}
	}

	/**
	 * 模糊查询keys
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {
		return template.keys(pattern);
	}

}
