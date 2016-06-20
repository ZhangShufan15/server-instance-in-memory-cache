package server.data.cache.example;

import java.util.Random;

import server.data.cache.DataStorage;
import server.data.cache.IDataLoader;

public class MyLoader implements IDataLoader {

	@Override
	public void loadData() {
		DataStorage.put(String.valueOf(new Random().nextInt(100000)), "myData");
	}

	@Override
	public boolean messageTypeMatch(String type) {
		return "myData".equals(type);
	}

}
