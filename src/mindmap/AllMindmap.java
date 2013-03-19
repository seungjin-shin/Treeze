package mindmap;

import mindmapGson.MindmapGson;
import mindmapGson.MindmapJson;

import java.io.IOException;
import java.util.ArrayList;
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

public class AllMindmap extends HttpServlet {
	private final int MAXNUM = 500;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setCharacterEncoding("EUC-KR");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("mindmap").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXNUM));

		entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXNUM));

		String jsonString;
		MindmapGson myGson = new MindmapGson();
		MindmapJson mindmap = new MindmapJson();
		ArrayList<String> tempid = mindmap.getId();
		ArrayList<String> temptitle = mindmap.getTitle();
		int cnt = 0;
		for (Entity entity : entities) {
			tempid.add(entity.getProperty("id").toString());
			temptitle.add(entity.getProperty("title").toString());
			cnt++;
		}
		mindmap.setCnt(cnt + "");
		jsonString = myGson.toJson(mindmap);
		resp.getWriter().print(jsonString);
		resp.getWriter().print("하이");
		resp.getWriter().print("하이");
		resp.getWriter().print("하이");
		return;
	}
}
