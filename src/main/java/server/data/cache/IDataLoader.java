package server.data.cache;

public interface IDataLoader {
	/**
	 * 加载数据
	 * @return
	 */
	void loadData();
	/**
	 * 重新加载的消息类型是否匹配
	 * @param type
	 * @return
	 */
	boolean messageTypeMatch(String type);
}
