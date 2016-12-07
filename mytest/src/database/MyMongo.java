package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.MongoDatabase;

public class MyMongo {
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	public MyMongo(String host, int port) {
		try {
			mongoClient = new MongoClient(host, port);
			Builder mco = MongoClientOptions.builder();
			mco.connectTimeout(2 * 1000);
			mco.socketTimeout(2 * 1000);
			mco.build();
	
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public MongoDatabase getConnection(String dbname) 
	{
		mongoDatabase = mongoClient.getDatabase(dbname);
		return mongoDatabase;
    }
	
	public void close()
	{
		mongoClient.close();		  
	}
	
}
