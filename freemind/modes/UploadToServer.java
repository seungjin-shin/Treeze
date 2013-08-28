package freemind.modes;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import freemind.controller.FreemindManager;
import freemind.controller.SlideData;
import freemind.json.Ticket;
import freemind.json.TreezeData;
import freemind.json.User;


public class UploadToServer {
	ArrayList<SlideData> sList;
	SlideData tmp;
	FreemindManager fManager = FreemindManager.getInstance();
	final String SERVERIP = fManager.SERVERIP;
  
	
	  public void doFileUpload() {
          try {
        	  File saveFile;//
        	  FileBody bin = null;
        		HttpClient httpClient = new DefaultHttpClient();
			for (int i = 1; i <= fManager.getPdfPage(); i++) {
				saveFile = new File(fManager.getFilePath(),
						fManager.getClassId() + "_" + i + ".jpg");

				if (saveFile.exists())
					bin = new FileBody(saveFile, "UTF-8");
				HttpPost post = new HttpPost("http://" + SERVERIP
						+ ":8080/treeze/upload/img");

				StringBody classBody = new StringBody(fManager.getClassId() + "",
						Charset.forName("UTF-8"));

				MultipartEntity multipart = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE, null,
						Charset.forName("UTF-8")); // xml, classId, LectureName
				multipart.addPart("classId", classBody);
				multipart.addPart("upload", bin);

				post.setEntity(multipart);
				HttpResponse response = httpClient.execute(post);
				HttpEntity resEntity = response.getEntity();
				EntityUtils.consume(resEntity);
			}

           System.out.println("postXmlImg");
       }catch(Exception e){e.printStackTrace();
       }
	  }
	  
	  public void lecturePost(String lectureName, String profEmail, String state) {
          try {
        		HttpClient httpClient = new DefaultHttpClient();
        	  HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/createLecture");
        	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        	  
        	  StringBody lectureTitle = new StringBody(lectureName, Charset.forName("UTF-8"));
        	  StringBody profEmailBody = new StringBody(profEmail, Charset.forName("UTF-8"));
        	  StringBody lectureState = new StringBody(state, Charset.forName("UTF-8"));
        	  StringBody profssorNameBody = new StringBody("ÀÌ¹Î¼®", Charset.forName("UTF-8"));
           
        	  multipart.addPart("lectureName", lectureTitle);  
        	  multipart.addPart("professorEmail", profEmailBody);
        	  multipart.addPart("stateOfLecture", lectureState);
        	  multipart.addPart("professorName", profssorNameBody);

        	  post.setEntity(multipart);  
        	  HttpResponse response = httpClient.execute(post);  
        	  HttpEntity resEntity = response.getEntity();
        	  EntityUtils.consume(resEntity);
        	  System.out.println("postLecture");
          }catch(Exception e){e.printStackTrace();
          }
	  }
	  
	  public void classPost(String lectureId, String profEmail, String className) {
		  	String jsonStr;
        try {
        	HttpClient httpClient = new DefaultHttpClient();
      	  HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/createClass"); 
      	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
      	  String ipStr = null;
      	  try {
				ipStr = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      	StringBody classId = new StringBody("0", Charset.forName("UTF-8"));
      	  StringBody lectureTitle = new StringBody(lectureId, Charset.forName("UTF-8"));
      	  StringBody profEmailBody = new StringBody(profEmail, Charset.forName("UTF-8"));
      	  StringBody classNameBody = new StringBody(className, Charset.forName("UTF-8"));
      	  StringBody portBody = new StringBody("2141", Charset.forName("UTF-8"));
      	  StringBody ipBody = new StringBody(ipStr, Charset.forName("UTF-8"));
      	  
      	  multipart.addPart("classIP", ipBody);
      	  multipart.addPart("port", portBody);
      	  multipart.addPart("classId", classId);
      	  multipart.addPart("lectureId", lectureTitle);  
      	  multipart.addPart("professorEmail", profEmailBody);
      	  multipart.addPart("className", classNameBody);
      	  
      	  post.setEntity(multipart);  
      	  HttpResponse response = httpClient.execute(post);  
      	  HttpEntity resEntity = response.getEntity();
      	EntityUtils.consume(resEntity);
      	  System.out.println("postClass");
        }catch(Exception e){e.printStackTrace();
        }
	  }
	  
	  public void ticketPost(Ticket t) {
		  	String jsonStr;
		  	Ticket ticket = t;
		  	
      try {
    		HttpClient httpClient = new DefaultHttpClient();
    	  HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/createTicket"); 
    	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
    	  
    	  StringBody classIdBody = new StringBody(fManager.getClassId() + "", Charset.forName("UTF-8"));
    	  StringBody parentNodeID = new StringBody(ticket.getParentNodeId(), Charset.forName("UTF-8"));
    	  StringBody contentsBody = new StringBody(ticket.getContents(), Charset.forName("UTF-8"));
    	  StringBody userNameBody = new StringBody(ticket.getUserName(), Charset.forName("UTF-8"));
    	  
    	  multipart.addPart("classId", classIdBody);
    	  multipart.addPart("contents", contentsBody);  
    	  multipart.addPart("userName", userNameBody);
    	  multipart.addPart("parentNodeId", parentNodeID);

    	  post.setEntity(multipart);  
    	  HttpResponse response = httpClient.execute(post);  
    	  HttpEntity resEntity = response.getEntity();
    	  
    	  InputStream inputStream = resEntity.getContent();
    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

    	  String str = "";
    	  String tmp;
    	  
			while((tmp = in.readLine()) != null )
				str += tmp;
			
			Gson gson = new Gson();
			
			System.out.println("response : " + str);
			TreezeData treezeData = new TreezeData();
			treezeData.setDataType(TreezeData.TICKET);
			treezeData.getArgList().add(str);
			
			OutputStream os = fManager.getOs();
			os.write(gson.toJson(treezeData).getBytes("UTF-8"));
			os.flush();
			
			EntityUtils.consume(resEntity);
    	  System.out.println("postTicket");
    	  
      }catch(Exception e){e.printStackTrace();
      }
	  }
	  
	  public void doXmlUpload() throws IOException{
    	  String xml ="";
    	  BufferedReader br;
    	  String fileName = fManager.getDownPath() + System.getProperty("file.separator") + "upload.mm";
    	  String line = "";
    	  
          try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
                while ((line = br.readLine()) != null) {
                	xml += line;
                }
                br.close();       
          } catch (Exception e) {
                e.printStackTrace();
          }
      	HttpClient httpClient = new DefaultHttpClient();
       HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/createMindMap"); 
       
       MultipartEntity multipart = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE, null,
				Charset.forName("UTF-8"));  // xml, classId, LectureName 		
       StringBody classBody = new StringBody(fManager.getClassId() + "", Charset.forName("UTF-8"));
       StringBody xmlBody = new StringBody(xml, Charset.forName("UTF-8"));

		multipart.addPart("classId", classBody);
		multipart.addPart("mindmapXML", xmlBody);

		post.setEntity(multipart);
		HttpResponse response = httpClient.execute(post);
		HttpEntity resEntity = response.getEntity();
		EntityUtils.consume(resEntity);
		System.out.println("UploadtoServer : douploadXml()");
	  }
	  
	  public void signPost(User u) {
		  User user = u;
			HttpClient httpClient = new DefaultHttpClient();
	       HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/createUser"); 
	       
	       MultipartEntity multipart = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE, null,
					Charset.forName("UTF-8"));  // xml, classId, LectureName 			
	       StringBody typeBoby;
		try {
			typeBoby = new StringBody(user.getUserType(), Charset.forName("UTF-8"));
			StringBody idBody = new StringBody(user.getIdentificationNumber() + "", Charset.forName("UTF-8"));
			StringBody nameBody = new StringBody(user.getUserName(), Charset.forName("UTF-8"));
			StringBody emailBody = new StringBody(user.getUserEmail(), Charset.forName("UTF-8"));
			StringBody pwBody = new StringBody(user.getPassword(), Charset.forName("UTF-8"));
			multipart.addPart("userType", typeBoby);
			multipart.addPart("identificationNumber", idBody);
			multipart.addPart("userName", nameBody);
			multipart.addPart("userEmail", emailBody);
			multipart.addPart("password", pwBody);
			
			post.setEntity(multipart);
			HttpResponse response = httpClient.execute(post);
			HttpEntity resEntity = response.getEntity();
			EntityUtils.consume(resEntity);
			System.out.println("UploadtoServer : singUp()");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public boolean signIn(String email, String pw){
//    	  113.198.84.80:8080/treeze/createUser
//    	  113.198.84.80:8080/treeze/login?userEmail="minsuk@hansung.ac.kr"&password="1234"
//    	  "emailFalse"
//    	  "passwordFalse"
			HttpClient httpClient = new DefaultHttpClient();
	       HttpGet get = new HttpGet("http://" + SERVERIP + ":8080/treeze/login?userEmail=" + email + "&password=" + pw); 
	       
	       MultipartEntity multipart = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE, null,
					Charset.forName("UTF-8"));  // xml, classId, LectureName 			
	       StringBody typeBoby;
		try {
			
			HttpResponse response = httpClient.execute(get);
			
			HttpEntity resEntity = response.getEntity();
			
			InputStream inputStream = resEntity.getContent();
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

	    	  String str = "";
	    	  String tmp;
	    	  
				while((tmp = in.readLine()) != null )
					str += tmp;
				
			System.out.println("response : " + str);
			
			EntityUtils.consume(resEntity);
			
			if(str.equals("emailFalse")){
				System.out.println("UploadtoServer : login return f");
				return false;
			}
			else if(str.equals("passwordFalse")){
				System.out.println("UploadtoServer : login return f");
				return false;
			}
			else{
				System.out.println("UploadtoServer : login return true");
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	  }
	  
	  public boolean checkClassIsEmpty(int classId){
			HttpClient httpClient = new DefaultHttpClient();
		       HttpGet get = new HttpGet("http://" + SERVERIP + ":8080/treeze/img?classId=" + classId);
		       
		       MultipartEntity multipart = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE, null,
						Charset.forName("UTF-8"));  // xml, classId, LectureName 				
		       StringBody typeBoby;
			try {
				HttpResponse response = httpClient.execute(get);
				
				HttpEntity resEntity = response.getEntity();
				
				InputStream inputStream = resEntity.getContent();
		    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		    	  String str = "";
		    	  String tmp;
		    	  
					while((tmp = in.readLine()) != null )
						str += tmp;
					
				EntityUtils.consume(resEntity);
				if(str.equals("{\"imgs\":[]}")){
					return true;
				}
				else{
					return false;
				}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	  }
}
