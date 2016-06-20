package server.data.cache.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import server.data.cache.DataStorage;

@Controller
public class TestController {
	@Autowired
	private RedisTemplate redisTemplate;
	
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	@RequestMapping("getData")
	public @ResponseBody String getData(){
		return DataStorage.getGeneric(String.class, "myData");
	}
	/**
	 * 正式使用应该是在另外的工程中，比如管理端
	 */
	@RequestMapping("reload")
	public @ResponseBody void sendReloadMessage(){
		redisTemplate.convertAndSend("CacheExpireTopic", "myData");
	}
}
