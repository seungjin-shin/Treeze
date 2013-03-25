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
    function showFeedback(id, user){
     document.writeM.innerHTML = "<input type='hidden' name='id' value='" +
     id +"'>" + 
     "<input type='hidden' name='user' value='" + user + "'>";
     document.writeM.submit();
   }
   </script>


   <body>

    <div class="container-fluid">
      <div class="row-fluid">        
        <div class="container-narrow">



          <div class="masthead">
            <ul class="nav nav-pills pull-right">
             <li class="active"><a href="#">Home</a></li>
             <li><a href="playing.jsp">My Page</a></li>
          <!--<form class="navbar-form pull-right">
          <input class="span2" type="text" placeholder="Email">
          <input class="span2" type="password" placeholder="Password">-->
          <li><a href="signin.html">Sign In</a></li>
          <!--</form>-->
        </ul>
        
        <h3 class="muted">Treeze!</h3>
      </div>

        <%@ page import="org.json.*"%>
			  <%@ page import = "java.io.*" %>
  			<%@ page import = "java.net.*" %>
 			 <%
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String users = ""; 
        JSONObject jsonObject = null;
        String json = null;
        %>

      <hr>
      <span class="label label-important" style="font-size:10pt;"><%=title%></span>
      <ul class="pager">

        <%
        String allusers = "http://dewliteyez.appspot.com/feedbackusers?id=" + id;
        BufferedReader br = new BufferedReader(new InputStreamReader((new URL(allusers)).openConnection().getInputStream(),"EUC-KR"));
        String tmp;

        while((tmp = br.readLine()) != null)
          users += tmp;

        %>
        <%
        jsonObject = new JSONObject ( users );
        JSONArray userArray = new JSONArray();
        JSONArray cntArray = new JSONArray();
        userArray = jsonObject.getJSONArray ( "user" );
        cntArray = jsonObject.getJSONArray ( "count" );
        String strCnt = jsonObject.getString( "cnt" );
        int cnt = Integer.parseInt(strCnt);
        %>



        <%
        for (int i = 0 ; i < cnt ; i++) {
        String user = userArray.get(i).toString();
        String count = cntArray.get(i).toString();
        %>
        <li onclick="showFeedback('<%=id%>','<%=user%>')"><a href="#" style="margin-left:20px;margin-right:20px;font-size:20pt;"><%=user%><span class="badge badge-important" style="position:relative;bottom:10px;"><%=count%></span></a></li>
        <%
      }
      %>
        
      </ul>

      <hr>
      <form name="writeM" method="post" action="showFeedback.jsp">
      </form>
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

  </body>
  </html>
