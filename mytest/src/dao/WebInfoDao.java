package dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import model.WebInfo;
import net.sf.json.JSONObject;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.bson.Document;
import org.bson.types.ObjectId;

import util.Contans;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import database.MyMongo;

public class WebInfoDao {
	public int addInfo(WebInfo wi) {
		int res = 1;
		Document info = new Document("uri", wi.getUri());
		info.put("url", wi.getUrl());
		info.put("fip", wi.getFip());
		info.put("realip", wi.getRealip());
		info.put("type", wi.getType());
		info.put("Parameter", wi.getPattam());
		info.put("time", wi.getTime());
		info.put("os", wi.getOs());
		info.put("browser", wi.getBrowser());
		MyMongo mm = null;
		MongoDatabase mongoDatabase = null;
		try {
			mm = new MyMongo(Contans.DbIP, Contans.DbPort);
			mongoDatabase = mm.getConnection(Contans.DbName);
			MongoCollection<Document> dc = mongoDatabase.getCollection(Contans.WebInfo);
			dc.insertOne(info);
		} catch (Exception e) {
			e.printStackTrace();
			res = -1;
		} finally {
			if (mm != null) {
				mm.close();
			}
		}

		return res;
	}
	
	public String findInfo(String key,String value){
		ArrayList<String> res = new ArrayList<String>();
		MyMongo mm = null;
		MongoDatabase mongoDatabase = null;
		Document query = new Document();
		try {
			if (key != null && (!key.isEmpty())) {
				if ("_id".equals(key)) {
					query.put(key, new ObjectId(value));
				} else {
					query.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("query={" + query + "}");
		try {
			mm = new MyMongo(Contans.DbIP, Contans.DbPort);
			mongoDatabase = mm.getConnection(Contans.DbName);
			MongoCollection<Document> dc = mongoDatabase.getCollection(Contans.WebInfo);
			FindIterable<Document> infoi = dc.find(query).sort(new Document("_id",-1)).limit(10);
			MongoCursor<Document> infoc = infoi.iterator();
			while(infoc.hasNext()){
				Document info = infoc.next();
				JSONObject infojs = JSONObject.fromObject(info);
				infojs.put("_id", info.get("id"));
				res.add(infojs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return res.toString();
	}

	
	public WebInfo getWebInfo(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		String type = request.getMethod();
		String pathInfo = request.getPathInfo();
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));   
		OperatingSystem os = userAgent.getOperatingSystem();  
		Browser browser = userAgent.getBrowser(); 
		String uri = request.getRequestURI();
		String r = "";
		if(type.equals("GET")){
			r = request.getQueryString();
			if(r!=null&&!r.isEmpty()){
				try {
					r = URLDecoder.decode(r,"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} else if (type.equals("POST")){
			r = getParameterFormUrlEncoded(request.getParameterMap()).toString();
		}
		
	
		
		
		String fip = request.getRemoteAddr();
		
		
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis();
		String t = sdf.format(time);
		WebInfo wi = new WebInfo();
		wi.setPathInfo(pathInfo);
		wi.setFip(fip);
		wi.setPattam(r);
		wi.setRealip(ip);
		wi.setUrl(url);
		wi.setType(type);
		wi.setUri(uri);
		wi.setTime(t);
		wi.setBrowser(browser.toString());
		wi.setOs(os.toString());
		System.out.println(wi);
		return wi;
	}
	
	private  Map<String, String> getParameterFormUrlEncoded(Map<String, String[]> properties){  
//      Map<?, ?> properties = request.getParameterMap();  
      Map<String, String> returnMap = new HashMap<String, String>();  
      Iterator<?> entries = properties.entrySet().iterator();  
      Entry<?, ?> entry;  
      String key = "";  
      String value = "";  
        
      while(entries.hasNext()){  
          entry = (Entry<?, ?>)entries.next();  
          key = (String)entry.getKey();  
          Object valueObj = entry.getValue();  
            
          if(null == valueObj){  
              value = "";  
          }else if(valueObj instanceof String[]){  
              String[] values = (String[])valueObj;  
              for(int i = 0;i < values.length;i++){  
                  value = values[i]+",";  
              }  
              value = value.substring(0, value.length()-1);   
          }else{  
              value = valueObj.toString();  
          }  
          returnMap.put(key, value);  
      }  
      return returnMap;  
 }  
	
}
