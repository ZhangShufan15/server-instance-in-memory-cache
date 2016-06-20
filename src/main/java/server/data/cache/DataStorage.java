package server.data.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStorage {
	//数据存储
	private static Map<String, Object> container = new ConcurrentHashMap<String, Object>();

	
	/**
	 * 添加数据
	 * @param value 数据
	 * @param keys 访问数据的关键字组合
	 */
	public static void put(Object value, String ...keys){
		StringBuilder sb = new StringBuilder();
		for(String key:keys){
			sb.append(key);
			sb.append("-");
		}
		container.put(sb.toString(), value);
	}
	/**
	 * 获取数据
	 * @param keys 访问数据的关键字组合
	 * @return
	 */
	public static Object get(String ...keys){
		StringBuilder sb = new StringBuilder();
		for(String key:keys){
			sb.append(key);
			sb.append("-");
		}
		
		Object obj = container.get(sb.toString());
		
		if(obj == null){
			throw new RuntimeException("获取内存对象失败，key=" + sb.toString());
		}
		
		return obj;
	}
	/**
	 * 获取数据（转型成T类型对象）
	 * @param clazz 欲返回的数据类型
	 * @param keys 访问数据的关键字组合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getGeneric(Class<T> clazz,String ...keys){
		StringBuilder sb = new StringBuilder();
		for(String key:keys){
			sb.append(key);
			sb.append("-");
		}
		
		Object obj = container.get(sb.toString());
		
		if(obj == null){
			throw new RuntimeException("获取内存对象失败，key=" + sb.toString());
		}
		
		return (T)obj;
	}
	/**
	 * 获取数据（转型成T类型对象数组）
	 * @param clazz 欲返回的数据类型
	 * @param keys 访问数据的关键字组合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getGenericList(Class<T> clazz,String ...keys){
		StringBuilder sb = new StringBuilder();
		for(String key:keys){
			sb.append(key);
			sb.append("-");
		}
		
		Object obj = container.get(sb.toString());
		
		if(obj == null){
			throw new RuntimeException("获取内存对象失败，key=" + sb.toString());
		}
		
		return (List<T>)obj;
	}
}
