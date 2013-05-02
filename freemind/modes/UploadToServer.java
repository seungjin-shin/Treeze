package freemind.modes;


import java.io.File;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;


public class UploadToServer {

	  public void doFileUpload(String path,String serverUrl) {
          try {
           HttpClient httpClient = new DefaultHttpClient();  
           String url = serverUrl;
           HttpPost post = new HttpPost("http://113.198.84.74:8080/treeze/upload/img"); 
           File saveFile = new File(path);
           if(saveFile.exists())
        	   System.out.println("파일있");
           
           FileBody bin = new FileBody(saveFile, "UTF-8");
           StringBody body = new StringBody("System Programing", Charset.forName("UTF-8"));
           MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
           StringBody xml2 = new StringBody(
   				"<map version=\"0.9.0\">" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"리눅스 강의\">" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"left\" TEXT=\" 리눅스 소개\">" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"리눅스의 역사\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux도 운영 체제\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux 전에는 Unix…\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux의 특징\"/>" +
   				"</node>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"left\" TEXT=\" 공개 소스 소프트웨어 (OSS or FOSS)\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"left\" TEXT=\" Linux 사용에 필요핚 기본 개념과 용어\">" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux 배포판 들\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"그 중 가장 인기 많은… ubuntu\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux의 구조\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"인터페이스 부분\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux 기본 개념들…\"/>" +
   				"</node>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"right\" TEXT=\" 시스템 호출 정리\">" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"프로세스 관련 명령\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Top 명령\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Windows 작업관리자\"/>" +
   				"<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"시스템 호출\"/>" +
   				"</node>" +
   				"</node>" +
   				"</map>", Charset.forName("UTF-8"));
           
           multipart.addPart("lectureName", body);  
           multipart.addPart("xml", xml2);
           multipart.addPart("upload", bin);

        post.setEntity(multipart);  
        HttpResponse response = httpClient.execute(post);  
        HttpEntity resEntity = response.getEntity();   
       }catch(Exception e){e.printStackTrace();
       }
	  }
}