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
           MultipartEntity multipart = 
                   new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);  
           multipart.addPart("lectureName", body);  
           multipart.addPart("upload", bin);     

        post.setEntity(multipart);  
        HttpResponse response = httpClient.execute(post);  
        HttpEntity resEntity = response.getEntity();   
       }catch(Exception e){e.printStackTrace();
       }
	  }
}