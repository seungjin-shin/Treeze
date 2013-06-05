package mindmap;

import mindmapGson.MindmapGson;
import mindmapGson.MindmapJson;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.List;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class Mindmap extends HttpServlet {
	private final int MAXNUM = 500;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String keyStr = "mindmap";
		Key mindmapKey = KeyFactory.createKey("mindmap", keyStr);
		
		String strCnt = req.getParameter("cnt");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		int cnt = Integer.parseInt(strCnt);

		Date date = new Date();

		Entity entity = new Entity("mindmap", mindmapKey);
		entity.setProperty("id", id);
		entity.setProperty("title", title);
		entity.setProperty("cnt", strCnt);
		entity.setProperty("feedbackCnt", "0");

		for(int i = 0; i < cnt; i++){
			String para = req.getParameter("mindmap" + i);
			entity.setProperty("mindmap" + i, para);
		}

		entity.setProperty("date", date);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);
		resp.getWriter().print("Save mindmap");
		return;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("mindmap").addSort("date",Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXNUM));
		
		entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXNUM));
		
		for (Entity entity : entities) {
			if (id.equals(entity.getProperty("id").toString())) {
				String jsonString;
				MindmapGson myGson = new MindmapGson();
				MindmapJson mindmap = new MindmapJson();
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				// mindmap.setId(entity.getProperty("id").toString());
				// mindmap.setMindmap(entity.getProperty("mindmap").toString());
				// mindmap.setTitle(entity.getProperty("title").toString());
				String strCnt = entity.getProperty("cnt").toString();
				int cnt = Integer.parseInt(strCnt);

				// map = entity.getProperties();
				
				// Set<String> set = map.keySet();
				// Iterator<String> iter = set.iterator();
				// String str = "";
				// while(iter.hasNext()){
				// 	String temp = (String) iter.next();
				// 	str += temp;
				// }

				for(int i = 0; i < cnt; i++){
					String contents = entity.getProperty("mindmap" + i).toString();
					resp.getWriter().print(contents);
				}

				//jsonString = myGson.toJson(mindmap);
				//resp.getWriter().print(jsonString);
				return;
			}
		}
	}
}
