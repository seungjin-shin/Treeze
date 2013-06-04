<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Sticky footer &middot; Twitter Bootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet">
    <style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */

      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      #wrap > .container {
        padding-top: 60px;
      }
      .container .credit {
        margin: 20px 0;
      }

      code {
        font-size: 80%;
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

  <body>


    <!-- Part 1: Wrap all page content here -->
    <div id="wrap"> 

      <!-- Fixed navbar -->
      <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">My Page</a>
            <div class="nav-collapse collapse">
              <ul class="nav">
              	<li><a href="main.jsp">Home</a></li>
                <li><a href="playing.jsp">Playing MindMap</a></li>
                
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Feedback MindMap <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a tabindex="-1" href="adopted.jsp">Adopted MindMap</a></li>
                    <li class="divider"></li>
                    <li class="nav-header"></li>
                    <li><a tabindex="-1" href="waiting.jsp">Waiting MindMap</a></li>
                  </ul>
                </li>
                <li><a href="complete.jsp">Complete MindMap</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </div>
      </div>
      

 <!-- list -->
      <div class="container-fluid">
       <div class="row-fluid">
        <div class="span8">
          <div class="well sidebar-nav" style="margin-left:500px;"><!--  style="margin-left:600px;"-->
            <ul class="nav nav-list">
              <li class="nav-header">Sidebar</li>
              <li class="active"><a href="#">Adopted MindMap List</a></li>
              
              
              
              <%@ page import="org.json.*"%>
			  <%@ page import = "java.io.*" %>
  			<%@ page import = "java.net.*" %>
 			 <%
  			String mindmap = ""; 
   			JSONObject jsonObject = null;
   			String json = null;

  String allmindmap = "http://treeze-map.appspot.com/allmindmap";
  BufferedReader br = new BufferedReader(new InputStreamReader((new URL(allmindmap)).openConnection().getInputStream(),"UTF-8"));
  String tmp;
  
  while((tmp = br.readLine()) != null)
    mindmap += tmp;

  jsonObject = new JSONObject ( mindmap );
  JSONArray idArray = new JSONArray();
  JSONArray titleArray = new JSONArray();
  JSONArray fbCntArray = new JSONArray();
  idArray = jsonObject.getJSONArray ( "id" );
  titleArray = jsonObject.getJSONArray ( "title" );
  fbCntArray = jsonObject.getJSONArray ("feedbackCnt");
  String strCnt = jsonObject.getString("cnt");
  int cnt = Integer.parseInt(strCnt);
  %>
   
  

			<%
			for (int i = 0 ; i < cnt ; i++) {
		     String id = idArray.get(i).toString();
     		String title = titleArray.get(i).toString();
     		String fbCnt = fbCntArray.get(i).toString(); 
     		%>
              <li><a href="#" onclick="showProject('<%=id%>', '1')"><%=title%><%=fbCnt%></a></li>
     		<%
   			}
  			%>
              
              
              <p><a class="btn" href="#" onclick="showProject('', '0')">adopted</a></p>
            </ul>
          </div><!--/.well -->  
        </div><!--/span-->
        </div>
        </div>
        <form name="writeM" method="post" action="mindmap.jsp">
		</form>
<!-- list -->

        
      <!-- Begin page content -->


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


    <!-- open source by dropdown -->



  </body>
</html>
