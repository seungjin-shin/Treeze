package com.treeze.uploadthread;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStream;import java.io.InputStreamReader;import java.io.OutputStream;import java.io.UnsupportedEncodingException;import java.nio.charset.Charset;import org.apache.http.HttpEntity;import org.apache.http.HttpResponse;import org.apache.http.client.ClientProtocolException;import org.apache.http.client.HttpClient;import org.apache.http.client.methods.HttpPost;import org.apache.http.entity.mime.HttpMultipartMode;import org.apache.http.entity.mime.MultipartEntity;import org.apache.http.entity.mime.content.StringBody;import org.apache.http.impl.client.DefaultHttpClient;import org.apache.http.util.EntityUtils;import com.google.gson.Gson;import com.treeze.data.ServerSocket;import com.treeze.data.TreezeData;import com.treeze.data.TreezeStaticData;public class CreateLecture extends Thread {	private String lectureId;	private String lectureName;	private String studentEmail;	public CreateLecture(String lectureId,String lectureName,String studentEmail) {		// TODO Auto-generated constructor stub		this.lectureId = lectureId;		this.lectureName = lectureName;		this.studentEmail = studentEmail;	}	@Override	public void run() {    	// TODO Auto-generated method stub    	super.run();    	 HttpClient httpClient = new DefaultHttpClient();        	  HttpPost post = new HttpPost("http://" + TreezeStaticData.IP + ":8080/treeze/createCourse");      	  System.out.println(studentEmail);      	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));      	try {			StringBody lectureIdBody = new StringBody(lectureId, Charset.forName("UTF-8"));			StringBody lectureNameBody = new StringBody(lectureName, Charset.forName("UTF-8"));		  	  StringBody studentEmailBody = new StringBody(studentEmail, Charset.forName("UTF-8"));		  	  		  	    	  multipart.addPart("lectureId", lectureIdBody);	    	  multipart.addPart("lectureName", lectureNameBody);  	    	  multipart.addPart("studentEmail", studentEmailBody);	    	  	    	  post.setEntity(multipart);  	    	  HttpResponse response = httpClient.execute(post);  	    	  HttpEntity resEntity = response.getEntity();	    	  InputStream inputStream = resEntity.getContent();	    	  String str = "";	    	  String tmp;	    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));				byte[] b = new byte[4096];				while((tmp = in.readLine()) != null )					str += tmp;				String responseContents = str;										System.out.println("[Post respone]"+responseContents);				TreezeData t = new TreezeData();				t.setDataType(TreezeData.TICKET);				t.getArgList().add(responseContents);																				//System.out.println(gson.toJson(t));				EntityUtils.consume(resEntity);	    	  System.out.println("postEnd");		} catch (UnsupportedEncodingException e) {			// TODO Auto-generated catch block			e.printStackTrace();		} catch (ClientProtocolException e) {			// TODO Auto-generated catch block			e.printStackTrace();		} catch (IOException e) {			// TODO Auto-generated catch block			e.printStackTrace();		}      	  	         	     }}