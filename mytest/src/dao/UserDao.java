package dao;

import net.sf.json.JSONObject;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import util.Contans;
import database.MyMongo;
import model.user;

public class UserDao {
	
	public JSONObject login(user u) {
		int res = -1;
		JSONObject r = new JSONObject();
		Document query = new Document("uname",u.getUname());
		query.put("pwd", u.getPwd());
		MyMongo mm = null;
		JSONObject userj = null;
		try {
			mm = new MyMongo(Contans.DbIP, Contans.DbPort);
			MongoDatabase db = mm.getConnection(Contans.DbName);
			MongoCollection<Document> dc = db.getCollection(Contans.DbUser);
			FindIterable<Document> useri = dc.find(query);
			System.out.println("query={" + query.toJson() + "}");
			MongoCursor<Document> userc = useri.iterator();
			if(userc.hasNext()){
				Document user = userc.next();
				userj = JSONObject.fromObject(user);
				userj.put("_id", user.get("_id"));
				userj.remove("pwd");
				res = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = -1;
		} finally {
			if(mm != null){
				mm.close();
			}
			r.put("res", res);
			r.put("user", userj);
		}
		System.out.println("r={" + r + "}");
		return r;
	}
}
