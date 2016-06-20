package server.data.cache;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public class DataLoadTask {
	protected static Logger logger = Logger.getLogger(DataLoadTask.class);
	//数据加载器
	private List<IDataLoader> dataLoaders;
	
	public void setDataLoaders(List<IDataLoader> dataLoaders) {
		this.dataLoaders = dataLoaders;
	}

	/**
	 * 启动时加载
	 */	
	//@PostConstruct
	public void taskFirst() throws Exception{
		if(this.dataLoaders == null){
			return;
		}
		
		for(IDataLoader loader : this.dataLoaders){
			loader.loadData();
		}
		logger.info("===============服务器实例内存对象加载完成==================");
	}
	
	/**
	 * 每一小时加载一次的任务
	 */	
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void taskReplay() throws Exception{
		if(this.dataLoaders == null){
			return;
		}
		
		for(IDataLoader loader : this.dataLoaders){
			loader.loadData();
		}
	}
}
