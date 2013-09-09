package freemind.modes;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.JFrame;

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

import freemind.Frame.TextDialogue;
import freemind.controller.FreemindManager;
import freemind.controller.SlideData;
import freemind.json.ArrayUser;
import freemind.json.ArrayVersion;
import freemind.json.ClassInfo;
import freemind.json.Lecture;
import freemind.json.Ticket;
import freemind.json.TreezeData;
import freemind.json.User;
import freemind.json.Version;


public class UploadToServer {
	FreemindManager fManager;
	String SERVERIP;
	
	public void setSERVERIP(String sERVERIP) {
		SERVERIP = sERVERIP;
	}

	JFrame curFrame;
	
	public JFrame getCurFrame() {
		return curFrame;
	}

	public void setCurFrame(JFrame curFrame) {
		this.curFrame = curFrame;
	}

	public UploadToServer(FreemindManager f) {
		fManager = f;
	}

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
				
				fManager.setLodingValue((int)(((double)i / (double)fManager.getPdfPage()) * 100));

				post.setEntity(multipart);
				HttpResponse response = httpClient.execute(post);
				HttpEntity resEntity = response.getEntity();
				EntityUtils.consume(resEntity);
			}
			
			fManager.setLodingValue(100);

           System.out.println("postXmlImg");
       }catch(Exception e){
    	   new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
       }
	  }
	  
	  public void lecturePost(String lectureName) {
          try {
        		HttpClient httpClient = new DefaultHttpClient();
        	  HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/createLecture");
        	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        	  
        	  StringBody lectureTitle = new StringBody(lectureName, Charset.forName("UTF-8"));
        	  StringBody profEmailBody = new StringBody(fManager.getUser().getUserEmail(), Charset.forName("UTF-8"));
        	  StringBody lectureState = new StringBody("false", Charset.forName("UTF-8"));
        	  StringBody profssorNameBody = new StringBody(fManager.getUser().getUserName(), Charset.forName("UTF-8"));
           
        	  multipart.addPart("lectureName", lectureTitle);  
        	  multipart.addPart("professorEmail", profEmailBody);
        	  multipart.addPart("stateOfLecture", lectureState);
        	  multipart.addPart("professorName", profssorNameBody);

        	  post.setEntity(multipart);  
        	  HttpResponse response = httpClient.execute(post);  
        	  HttpEntity resEntity = response.getEntity();
        	  EntityUtils.consume(resEntity);
        	  System.out.println("postLecture");
          }catch(Exception e){
        	  new TextDialogue(getCurFrame(), "Server down, Program end", true);
  			e.printStackTrace();
  			System.exit(0);
          }
	  }
	  
	  public void deleteLecturePost(Lecture lecture) {
          try {
        		HttpClient httpClient = new DefaultHttpClient();
        	  HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/deleteLecture");
        	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        	  
        	  StringBody lectureNameBody = new StringBody(lecture.getLectureName(), Charset.forName("UTF-8"));
        	  StringBody profEmailBody = new StringBody(lecture.getProfessorEmail(), Charset.forName("UTF-8"));
        	  StringBody lectureState = new StringBody(lecture.getStateOfLecture() + "", Charset.forName("UTF-8"));
        	  StringBody profssorNameBody = new StringBody(lecture.getProfessorName(), Charset.forName("UTF-8"));
        	  StringBody idBody = new StringBody(lecture.getId() + "", Charset.forName("UTF-8"));
        	  StringBody lectureIdBody = new StringBody(lecture.getLectureId() + "", Charset.forName("UTF-8"));
        	  
        	  multipart.addPart("id", idBody);
        	  multipart.addPart("lectureName", lectureNameBody);  
        	  multipart.addPart("professorEmail", profEmailBody);
        	  multipart.addPart("stateOfLecture", lectureState);
        	  multipart.addPart("lectureId", lectureIdBody);
        	  multipart.addPart("professorName", profssorNameBody);
        	  
        	  post.setEntity(multipart);  
        	  HttpResponse response = httpClient.execute(post);  
        	  HttpEntity resEntity = response.getEntity();
        	  EntityUtils.consume(resEntity);
        	  System.out.println("delete Lecture");
          }catch(Exception e){
        	  new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
          }
	  }
	  
	  public void classPost(String lectureId, String className) {
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
      	  StringBody profEmailBody = new StringBody(fManager.getUser().getUserEmail(), Charset.forName("UTF-8"));
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
      	InputStream inputStream = resEntity.getContent();
  	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

  	  String str = "";
  	  String tmp;
  	  
			while((tmp = in.readLine()) != null )
				str += tmp;
      	  
      	EntityUtils.consume(resEntity);
      	  System.out.println("postClass : " + str);
        }catch(Exception e){
        	new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
        }
	  }
	  
	  public void deleteClassPost(ClassInfo classInfo) {
		  	String jsonStr;
      try {
      	HttpClient httpClient = new DefaultHttpClient();
    	  HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/deleteClass"); 
    	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
    	  
    	  StringBody classId = new StringBody(classInfo.getClassId() + "", Charset.forName("UTF-8"));
    	  StringBody lectureIdBody = new StringBody(classInfo.getLectureId() + "", Charset.forName("UTF-8"));
    	  StringBody profEmailBody = new StringBody(classInfo.getProfessorEmail(), Charset.forName("UTF-8"));
    	  StringBody classNameBody = new StringBody(classInfo.getClassName(), Charset.forName("UTF-8"));
    	  StringBody portBody = new StringBody(classInfo.getPort() + "", Charset.forName("UTF-8"));
    	  StringBody ipBody = new StringBody(classInfo.getClassIP(), Charset.forName("UTF-8"));
    	  StringBody idBody = new StringBody(classInfo.getId() + "", Charset.forName("UTF-8"));

    	  multipart.addPart("id", idBody);
    	  multipart.addPart("classIP", ipBody);
    	  multipart.addPart("port", portBody);
    	  multipart.addPart("classId", classId);
    	  multipart.addPart("lectureId", lectureIdBody);  
    	  multipart.addPart("professorEmail", profEmailBody);
    	  multipart.addPart("className", classNameBody);
    	  
    	  post.setEntity(multipart);  
    	  HttpResponse response = httpClient.execute(post);  
    	  HttpEntity resEntity = response.getEntity();
    	  InputStream inputStream = resEntity.getContent();
    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

    	  String str = "";
    	  String tmp;
    	  
			while((tmp = in.readLine()) != null )
				str += tmp;
    	  EntityUtils.consume(resEntity);
    	  System.out.println("deleteClass : " + str);
    	  
      }catch(Exception e){
    	  new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
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
    	  
      }catch(Exception e){
    	  new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
      }
	  }
	  
	  public void doXmlUpload(){
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
       StringBody classBody;
	try {
		classBody = new StringBody(fManager.getClassId() + "", Charset.forName("UTF-8"));
		StringBody xmlBody = new StringBody(xml, Charset.forName("UTF-8"));
		
		multipart.addPart("classId", classBody);
		multipart.addPart("mindmapXML", xmlBody);
		
		post.setEntity(multipart);
		HttpResponse response = httpClient.execute(post);
		HttpEntity resEntity = response.getEntity();
		InputStream inputStream = resEntity.getContent();
  	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

  	  String str = "";
  	  String tmp;
  	  
			while((tmp = in.readLine()) != null )
				str += tmp;
		EntityUtils.consume(resEntity);
		System.out.println("UploadtoServer : douploadXml() : " + str);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		new TextDialogue(getCurFrame(), "Server down, Program end", true);
		e.printStackTrace();
		System.exit(0);
	}
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
			StringBody nameBody = new StringBody(user.getUserName().trim(), Charset.forName("UTF-8"));
			StringBody emailBody = new StringBody(user.getUserEmail().trim(), Charset.forName("UTF-8"));
			StringBody pwBody = new StringBody(user.getPassword().trim(), Charset.forName("UTF-8"));
			multipart.addPart("userType", typeBoby);
			multipart.addPart("identificationNumber", idBody);
			multipart.addPart("userName", nameBody);
			multipart.addPart("userEmail", emailBody);
			multipart.addPart("password", pwBody);
			
			post.setEntity(multipart);
			HttpResponse response = httpClient.execute(post);
			HttpEntity resEntity = response.getEntity();
			
			InputStream inputStream = resEntity.getContent();
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

	    	  String str = "";
	    	  String tmp;
	    	  
				while((tmp = in.readLine()) != null )
					str += tmp;
			
				EntityUtils.consume(resEntity);
				
				Gson gson = new Gson();
				ArrayUser arrayUser = gson.fromJson(str, ArrayUser.class);
				User checkUser = arrayUser.getUser();
				
				if(user.getUserEmail().equals(checkUser.getUserEmail())){
					new TextDialogue(getCurFrame(), "Success sign up.", true);
				}
				else{
					new TextDialogue(getCurFrame(), "Fail sign up.", true);
				}
			System.out.println("UploadtoServer : singUp() : " + str);
		} catch (IOException e) {
			new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
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
				new TextDialogue(getCurFrame(), "Email is not exist.", true);
				return false;
			}
			else if(str.equals("passwordFalse")){
				new TextDialogue(getCurFrame(), "Incorrect password.", true);
				return false;
			}
			else{
				Gson gson = new Gson();
				ArrayUser arrayUser = gson.fromJson(str, ArrayUser.class);
				User user = arrayUser.getUser();
				fManager.setUser(user);
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	  }
	  
	  public boolean checkClassIsEmpty(int classId){
			HttpClient httpClient = new DefaultHttpClient();
		       HttpGet get = new HttpGet("http://" + SERVERIP + ":8080/treeze/isRegistedMindmap?classId=" + classId);
		       
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
				if(str.equals("true")){
					return true;
				}
				else{
					return false;
				}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				new TextDialogue(getCurFrame(), "Server down, Program end", true);
				e.printStackTrace();
				System.exit(0);
			}
			return false;
	  }
	  
	  public void setStateOfLecture(Lecture lecture, boolean state){
	
	  	HttpClient httpClient = new DefaultHttpClient();  
		  HttpPost post = new HttpPost("http://" + fManager.SERVERIP + ":8080/treeze/setStateOfLecture");
		  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
		  
		  String str = "";
		  
		  try {
      	  
      	  StringBody lectureNameBody = new StringBody(lecture.getLectureName(), Charset.forName("UTF-8"));
      	  StringBody profEmailBody = new StringBody(lecture.getProfessorEmail(), Charset.forName("UTF-8"));
      	  StringBody lectureState = new StringBody(state + "", Charset.forName("UTF-8"));
      	  StringBody profssorNameBody = new StringBody(lecture.getProfessorName(), Charset.forName("UTF-8"));
      	  StringBody idBody = new StringBody(lecture.getId() + "", Charset.forName("UTF-8"));
      	  StringBody lectureIdBody = new StringBody(lecture.getLectureId() + "", Charset.forName("UTF-8"));
      	  
      	  multipart.addPart("id", idBody);
      	  multipart.addPart("lectureName", lectureNameBody);  
      	  multipart.addPart("professorEmail", profEmailBody);
      	  multipart.addPart("stateOfLecture", lectureState);
      	  multipart.addPart("lectureId", lectureIdBody);
      	  multipart.addPart("professorName", profssorNameBody);
      	  
      	  post.setEntity(multipart);  
      	  HttpResponse response = httpClient.execute(post);  
      	  HttpEntity resEntity = response.getEntity();
      	InputStream inputStream = resEntity.getContent();
  	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

  	  String tmp;
  	  
			while((tmp = in.readLine()) != null )
				str += tmp;
      	  EntityUtils.consume(resEntity);
      	  
        }catch(Exception e){
      	  new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
        }
		  
		  System.out.println("change state : " + state + ", : " + str);
	  }
	  
	  public void doExeFileUpload() {
		  
          try {
        	  StringBody versionBody = new StringBody("3.0", Charset.forName("UTF-8"));
        	  StringBody userTypeBody = new StringBody("student",
						Charset.forName("UTF-8"));
        	  
        	  
//        	  HttpClient httpClient = new DefaultHttpClient();  
//    		  HttpPost post = new HttpPost("http://" + fManager.SERVERIP + ":8080/treeze/updateVersion");
//    		  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
//    		  
//    		  String str = "";
//    		  
////    			  String userType;
////    				String versionId;
////    				
//          	  StringBody userTypeBody = new StringBody("professor", Charset.forName("UTF-8"));
//
//          	  multipart.addPart("userType", userTypeBody);
//          	  multipart.addPart("version", versionBody);  
//          	  
//          	  post.setEntity(multipart);  
//          	  HttpResponse response = httpClient.execute(post);  
//          	  HttpEntity resEntity = response.getEntity();
//          	InputStream inputStream = resEntity.getContent();
//      	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//
//      	  String tmp;
//      	  
//    			while((tmp = in.readLine()) != null )
//    				str += tmp;
//    			System.out.println(str);
//          	  EntityUtils.consume(resEntity);
    		
        	  
        	  
        	  
        	  
        	  
        	  File saveFile;//
        	  FileBody bin = null;
        	HttpClient httpClient = new DefaultHttpClient();
			saveFile = new File("C:\\Users\\Shin\\Desktop\\새 폴더\\student\\student.exe");

				if (saveFile.exists())
					bin = new FileBody(saveFile, "UTF-8");
				
				HttpPost post = new HttpPost("http://" + SERVERIP
						+ ":8080/treeze/upload/file");

				

				MultipartEntity multipart = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE, null,
						Charset.forName("UTF-8")); // xml, classId, LectureName
				multipart.addPart("version", versionBody);
				multipart.addPart("userType", userTypeBody);
				multipart.addPart("upload", bin);

				post.setEntity(multipart);
				HttpResponse response = httpClient.execute(post);
				HttpEntity resEntity = response.getEntity();
				InputStream inputStream = resEntity.getContent();
		    	 BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		    	  String str = "";
		    	  String tmp;
		    	  
					while((tmp = in.readLine()) != null )
						str += tmp;
				EntityUtils.consume(resEntity);

           System.out.println("postExeFile : " + str);
           
     	 httpClient = new DefaultHttpClient();
			saveFile = new File("C:\\Users\\Shin\\Desktop\\새 폴더\\student\\treeze.cnf");

				if (saveFile.exists())
					bin = new FileBody(saveFile, "UTF-8");
				
			 post = new HttpPost("http://" + SERVERIP
						+ ":8080/treeze/upload/file");


				 multipart = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE, null,
						Charset.forName("UTF-8")); // xml, classId, LectureName
				multipart.addPart("version", versionBody);
				multipart.addPart("userType", userTypeBody);
				multipart.addPart("upload", bin);

				post.setEntity(multipart);
			response = httpClient.execute(post);
				 resEntity = response.getEntity();
				 inputStream = resEntity.getContent();
		    	 in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		    	  
				EntityUtils.consume(resEntity);

        System.out.println("postExeFile : " + str);
        
//        httpClient = new DefaultHttpClient();
//		saveFile = new File("C:\\Users\\Shin\\Desktop\\새 폴더\\Resources_ko.properties");
//
//			if (saveFile.exists())
//				bin = new FileBody(saveFile, "UTF-8");
//			
//		 post = new HttpPost("http://" + SERVERIP
//					+ ":8080/treeze/upload/file");
//
//
//			 multipart = new MultipartEntity(
//					HttpMultipartMode.BROWSER_COMPATIBLE, null,
//					Charset.forName("UTF-8")); // xml, classId, LectureName
//			multipart.addPart("version", versionBody);
//			multipart.addPart("userType", userTypeBody);
//			multipart.addPart("upload", bin);
//
//			post.setEntity(multipart);
//		response = httpClient.execute(post);
//			 resEntity = response.getEntity();
//			 inputStream = resEntity.getContent();
//	    	 in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//
//	    	  
//			EntityUtils.consume(resEntity);
//
//    System.out.println("postExeFile : " + str);
//    
//    httpClient = new DefaultHttpClient();
//	saveFile = new File("C:\\Users\\Shin\\Desktop\\새 폴더\\freemind.properties");
//
//		if (saveFile.exists())
//			bin = new FileBody(saveFile, "UTF-8");
//		
//	 post = new HttpPost("http://" + SERVERIP
//				+ ":8080/treeze/upload/file");
//
//
//		 multipart = new MultipartEntity(
//				HttpMultipartMode.BROWSER_COMPATIBLE, null,
//				Charset.forName("UTF-8")); // xml, classId, LectureName
//		multipart.addPart("version", versionBody);
//		multipart.addPart("userType", userTypeBody);
//		multipart.addPart("upload", bin);
//
//		post.setEntity(multipart);
//	response = httpClient.execute(post);
//		 resEntity = response.getEntity();
//		 inputStream = resEntity.getContent();
//    	 in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//
//    	  
//		EntityUtils.consume(resEntity);
//
//System.out.println("postExeFile : " + str);
//
//httpClient = new DefaultHttpClient();
//saveFile = new File("C:\\Users\\Shin\\Desktop\\새 폴더\\mindmap_menus.xml");
//
//	if (saveFile.exists())
//		bin = new FileBody(saveFile, "UTF-8");
//	
// post = new HttpPost("http://" + SERVERIP
//			+ ":8080/treeze/upload/file");
//
//
//	 multipart = new MultipartEntity(
//			HttpMultipartMode.BROWSER_COMPATIBLE, null,
//			Charset.forName("UTF-8")); // xml, classId, LectureName
//	multipart.addPart("version", versionBody);
//	multipart.addPart("userType", userTypeBody);
//	multipart.addPart("upload", bin);
//
//	post.setEntity(multipart);
//response = httpClient.execute(post);
//	 resEntity = response.getEntity();
//	 inputStream = resEntity.getContent();
//	 in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//
//	  
//	EntityUtils.consume(resEntity);
//
//System.out.println("postExeFile : " + str);
//
//httpClient = new DefaultHttpClient();
//saveFile = new File("C:\\Users\\Shin\\Desktop\\새 폴더\\patterns.xml");
//
//	if (saveFile.exists())
//		bin = new FileBody(saveFile, "UTF-8");
//	
// post = new HttpPost("http://" + SERVERIP
//			+ ":8080/treeze/upload/file");
//
//
//	 multipart = new MultipartEntity(
//			HttpMultipartMode.BROWSER_COMPATIBLE, null,
//			Charset.forName("UTF-8")); // xml, classId, LectureName
//	multipart.addPart("version", versionBody);
//	multipart.addPart("userType", userTypeBody);
//	multipart.addPart("upload", bin);
//
//	post.setEntity(multipart);
//response = httpClient.execute(post);
//	 resEntity = response.getEntity();
//	 inputStream = resEntity.getContent();
//	 in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//
//	  
//	EntityUtils.consume(resEntity);
//
//System.out.println("postExeFile : " + str);

          }
		  catch (Exception e) {
			// TODO: handle exception
		}
           
       }

	  
	  public String checkVersion(){
		  HttpClient httpClient = new DefaultHttpClient();
	       HttpGet get = new HttpGet("http://" + SERVERIP + ":8080/treeze/getLastVersion?userType=" + Version.PROFESSOR);
	       String str = "";
	       MultipartEntity multipart = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE, null,
					Charset.forName("UTF-8"));  // xml, classId, LectureName 				
		try {
			HttpResponse response = httpClient.execute(get);
			
			HttpEntity resEntity = response.getEntity();
			
			InputStream inputStream = resEntity.getContent();
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

	    	  
	    	  String tmp;
	    	  
				while((tmp = in.readLine()) != null )
					str += tmp;
				
				System.out.println("checkVersion : " + str);
				Gson gson = new Gson();
				ArrayVersion arrayVersion = gson.fromJson(str, ArrayVersion.class);
				Version version = arrayVersion.getVersion();
				
			EntityUtils.consume(resEntity);
			return version.getVersion();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	  }
	  
	  public boolean checkDuplEmail(String email){
		  
		  HttpClient httpClient = new DefaultHttpClient();
	       HttpGet get = new HttpGet("http://" + SERVERIP + ":8080/treeze/getLastVersion?userType=" + Version.PROFESSOR);
	       String str = "";
	       MultipartEntity multipart = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE, null,
					Charset.forName("UTF-8"));  // xml, classId, LectureName 				
		try {
			HttpResponse response = httpClient.execute(get);
			
			HttpEntity resEntity = response.getEntity();
			
			InputStream inputStream = resEntity.getContent();
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

	    	  
	    	  String tmp;
	    	  
				while((tmp = in.readLine()) != null )
					str += tmp;
				
				System.out.println("checkVersion : " + str);
				Gson gson = new Gson();
				ArrayVersion arrayVersion = gson.fromJson(str, ArrayVersion.class);
				Version version = arrayVersion.getVersion();
				
				EntityUtils.consume(resEntity);
			
				if(str.equals("true"))
					return true;
				else
					return false;
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new TextDialogue(getCurFrame(), "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	  }
	  
}
