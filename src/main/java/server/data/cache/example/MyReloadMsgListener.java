package server.data.cache.example;

import java.util.List;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;


import server.data.cache.IDataLoader;
import server.data.cache.IReloadMessageListener;

public class MyReloadMsgListener implements IReloadMessageListener, MessageListener {

	//redis模板
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	//数据加载器
	private List<IDataLoader> dataLoaders;
	
	@SuppressWarnings("rawtypes")
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setDataLoaders(List<IDataLoader> dataLoaders) {
		this.dataLoaders = dataLoaders;
	}
	
    @Override  
    public void onMessage(Message message, byte[] pattern) {  
        byte[] body = message.getBody();//请使用valueSerializer  
        byte[] channel = message.getChannel();  
        //请参考配置文件，本例中key，value的序列化方式均为string。  
        //其中key必须为stringSerializer。和redisTemplate.convertAndSend对应  
        String msg = (String)redisTemplate.getValueSerializer().deserialize(body);  
        String topic = (String)redisTemplate.getStringSerializer().deserialize(channel);
        
        //触发消息处理
        this.onMessage(msg);
    }  
	
	/**
	 * 处理过期重新加载消息
	 */
	@Override
	public void onMessage(String message) {
		if(this.dataLoaders == null){
			return;
		}
		
		for(IDataLoader loader : this.dataLoaders){
			if(loader.messageTypeMatch(message)){
				loader.loadData();
			}
		}
	}

}
