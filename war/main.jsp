<!DOCTYPE html>
<html lang="en">
  <head>
	<%@ page pageEncoding="EUC-KR" %>
    <!--<meta charset="utf-8">-->
    
    <title>Template &middot; Bootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 20px;
        padding-bottom: 40px;
      }

      /* Custom container */
      .container-narrow {
        margin: 0 auto;
        max-width: 700px;
      }
      .container-narrow > hr {
        margin: 30px 0;
      }

      /* Main marketing message and sign up button */
      .jumbotron {
        margin: 60px 0;
        text-align: center;
      }
      .jumbotron h1 {
        font-size: 72px;
        line-height: 1;
      }
      .jumbotron .btn {
        font-size: 21px;
        padding: 14px 24px;
      }

      /* Supporting marketing content */
      .marketing {
        margin: 60px 0;
      }
      .marketing p + h4 {
        margin-top: 28px;
      }
    </style>
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="img/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="img/ico/favicon.png">
  </head>
<script>
function showProject(id, chk){
		 document.writeM.innerHTML = "<input type='hidden' name='id' value='" +
			 id +"'>" + 
		 "<input type='hidden' name='chk' value='" + chk + "'>";
		 document.writeM.submit();
	}
</script>


  <body>


<div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Sidebar</li>
              <li class="active"><a href="#">Link</a></li>
              
              
              
              <%@ page import="org.json.*"%>
			  <%@ page import = "java.io.*" %>
  			<%@ page import = "java.net.*" %>
 			 <%
  			String mindmap = ""; 
   			JSONObject jsonObject = null;
   			String json = null;

  String allmindmap = "http://dewliteyez.appspot.com/allmindmap";
  BufferedReader br = new BufferedReader(new InputStreamReader((new URL(allmindmap)).openConnection().getInputStream(),"UTF-8"));
  String tmp;
  
  while((tmp = br.readLine()) != null)
    mindmap += tmp;

  jsonObject = new JSONObject ( mindmap );
  JSONArray idArray = new JSONArray();
  JSONArray titleArray = new JSONArray();
  idArray = jsonObject.getJSONArray ( "id" );
  titleArray = jsonObject.getJSONArray ( "title" );
  String strCnt = jsonObject.getString("cnt");
  int cnt = Integer.parseInt(strCnt);
  %>
   
  

			<%
			for (int i = 0 ; i < cnt ; i++) {
		     String id = idArray.get(i).toString();
     		String title = titleArray.get(i).toString();
     		%>
              <li><a href="#" onclick="showProject('<%=id%>', '1')"><%=title%></a></li>
     		<%
   			}
  			%>
              
              
              <p><a class="btn" href="#" onclick="showProject('', '0')">쓰기 &raquo;</a></p>
            </ul>
          </div><!--/.well -->  
        </div><!--/span-->
        <form name="writeM" method="post" action="mindmap.jsp">
		</form>
        

    <div class="container-narrow">



      <div class="masthead">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <!--<form class="navbar-form pull-right">
          <input class="span2" type="text" placeholder="Email">
          <input class="span2" type="password" placeholder="Password">-->
          <li><a href="signin.html">Sign In</a></li>
          <!--</form>-->
        </ul>
        
        <h3 class="muted">For Needs!</h3>
      </div>

      <hr>

      <div class="jumbotron">
        <h1>Open Your Idea!</h1>
        <p class="lead">당신의 아이디어를 공개하세요!.</p>
        <p class="lead">생각을 공개하면 분명 새로운 무언가를 얻을 수 있을 것입니다!</p>
        <a class="btn btn-large btn-success" href="index.html">Make Mind Map!</a>
      </div>



      <hr>

      <div class="row-fluid marketing">
        <div class="span6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
        
        

        <div class="span6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
      </div>
      

      <hr>

      <div class="footer">
        <p>&copy; Company 2013</p>
      </div>

    </div> <!-- /container -->
    
    

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap/jquery.js"></script>
    <script src="js/bootstrap/bootstrap-transition.js"></script>
    <script src="js/bootstrap/bootstrap-alert.js"></script>
    <script src="js/bootstrap/bootstrap-modal.js"></script>
    <script src="js/bootstrap/bootstrap-dropdown.js"></script>
    <script src="js/bootstrap/bootstrap-scrollspy.js"></script>
    <script src="js/bootstrap/bootstrap-tab.js"></script>
    <script src="js/bootstrap/bootstrap-tooltip.js"></script>
    <script src="js/bootstrap/bootstrap-popover.js"></script>
    <script src="js/bootstrap/bootstrap-button.js"></script>
    <script src="js/bootstrap/bootstrap-collapse.js"></script>
    <script src="js/bootstrap/bootstrap-carousel.js"></script>
    <script src="js/bootstrap/bootstrap-typeahead.js"></script>
    <script src="js/libs/json2.js"></script>

  </body>
</html>
