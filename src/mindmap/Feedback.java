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

public class Feedback extends HttpServlet {
	private final int MAXNUM = 500;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");

		String keyStr = "feedback";
		Key feedbackKey = KeyFactory.createKey("feedback", keyStr);

		String strCnt = req.getParameter("cnt");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String user = req.getParameter("user");
		int cnt = Integer.parseInt(strCnt);

		Date date = new Date();

		Entity entity = new Entity("feedback", feedbackKey);
		entity.setProperty("id", id);
		entity.setProperty("title", title);
		entity.setProperty("cnt", strCnt);
		entity.setProperty("user", user);

		for (int i = 0; i < cnt; i++) {
			String para = req.getParameter("mindmap" + i);
			entity.setProperty("mindmap" + i, para);
		}

		entity.setProperty("date", date);
		entity.setProperty("confirmChk", "n");

		// get version value
		String version = "1";

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("feedback").addSort("date",
				Query.SortDirection.DESCENDING);

		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXNUM));
		if (entities.isEmpty()) {
			version = "1";
		} else {
			for (Entity entry : entities) {
				if(user.equals(entry.getProperty("user").toString()) && 
						id.equals(entry.getProperty("id"))){
					String tmpStr;
					int tmpInt;
					tmpStr = entry.getProperty("version").toString();
					tmpInt = Integer.parseInt(tmpStr) + 1;
					version = tmpInt + "";
					break;
				}
			}
		}
		entity.setProperty("version", version);
		//get version value
		//end

		DatastoreService datastore2 = DatastoreServiceFactory
				.getDatastoreService();
		datastore2.put(entity);
		return;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		String user = req.getParameter("user");
		String version = req.getParameter("version");

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("feedback").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXNUM));

		entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXNUM));

		for (Entity entity : entities) {
			if (id.equals(entity.getProperty("id").toString()) &&
					user.equals(entity.getProperty("user").toString()) &&
					version.equals(entity.getProperty("version").toString())) {
				
				String strCnt = entity.getProperty("cnt").toString();
				int cnt = Integer.parseInt(strCnt);


				for (int i = 0; i < cnt; i++) {
					String contents = entity.getProperty("mindmap" + i)
							.toString();
					resp.getWriter().print(contents);
				}
				return;
			}
		}
	}
}
