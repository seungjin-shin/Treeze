Treeze
======
tomcat - Spring - DB - Client 이 4박자가 다 맞아야하는데 전부 utf-8로 바꾸려고했지만 잘 넣어지질 않았다.

tomcat : 환경설정에서 open config 해서 encoding방식을 utf-8로 바꿔야한다.
spring : properties.xml 에 jdbc url설정부분에 jdbc:mysql://localhost:3306/etbike?useUnicode=true&amp;characterEncoding=utf8 로한다.
         web.xml에서도

<pre>
<code>
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
</code>
</pre>

   를 해주고

servlet-context.xml 에서도

        <beans:bean class="com.etbike.server.support.spring.resolver.NegotiateInternalResourceViewResolver" p:order="1" >
                <beans:property name="prefix" value="/WEB-INF/views/" />
                <beans:property name="suffix" value=".jsp" />
                <beans:property name="contentType" value="text/html; charset=UTF-8"/>
        </beans:bean>



이런부분으로 수정해주었다.

DB : 데이터베이스에서는 초기에 만들때부터 UTF-8, utf-8_general_ci 로 설정을해서 생성하지않으면 도중에 변경,수정해도 소용이없다.
     그렇게 새롭게 생성하는데 my.ini 이파일을 없으면 만든다고했는데 my-medium.ini를 복사해서 이름바꿔서 생성해줬다.
     data라는 폴더에서 찾아보면 있을수있다. 거기에다가 
     init_connect="SET collation_connection=utf8_general_ci"
init_connect="SET NAMES utf8"
default-character-set=utf8
character-set-server=utf8
collation-server=utf8_general_ci
character-set-client-handshake=FALSE

이런것들이 적혀있어야한다.

디비가 생성됫을때 콘솔에서 mysql> show variables like 'c%'; 쳐서

+--------------------------+-----------------------------------------+
| Variable_name            | Value                                   |
+--------------------------+-----------------------------------------+
| character_set_client     | utf8                                    |
| character_set_connection | utf8                                    |
| character_set_database   | utf8                                    |
| character_set_filesystem | binary                                  |
| character_set_results    | utf8                                    |
| character_set_server     | utf8                                    |
| character_set_system     | utf8                                    |
| character_sets_dir       | /usr/local/server/mysql/share/charsets/ |
| collation_connection     | utf8_general_ci                         |
| collation_database       | utf8_general_ci                         |
| collation_server         | utf8_general_ci                         |
| completion_type          | NO_CHAIN                                |
| concurrent_insert        | AUTO                                    |
| connect_timeout          | 10                                      |
+--------------------------+-----------------------------------------+

이게 떠야지 되는거다 ....

Client : 부분에서는 post로 보내는데 보내는 entity내용을 인코딩해서 보내니까 잘됬다.

 HttpClient httpClient = new DefaultHttpClient();
                        String url = serverUrl;
                        HttpPost post = new HttpPost(url);
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                        nameValuePairs.add(new BasicNameValuePair("title", "송태웅"));
                        nameValuePairs.add(new BasicNameValuePair("content", "송태웅"));
                        nameValuePairs.add(new BasicNameValuePair("writer", "송태웅"));
                        UrlEncodedFormEntity entityRequest =  new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                        post.setEntity(entityRequest);
                        HttpResponse response = httpClient.execute(post);

2012.11.17 (토) 김두형은 헤매고 형이 먼저 해결한 인코딩문제 
