<%@ page contentType = "text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Treeze &middot; </title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Le styles -->
  <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.css" rel="stylesheet">
  <style type="text/css">

  </style>
  <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-responsive.css" rel="stylesheet">

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/resources/assets/ico/interaction-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/resources/assets/ico/interaction-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/resources/assets/ico/interaction-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/resources/assets/ico/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/ico/interaction.png">
</head>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-1.10.2.min.js">
</script>

<body>

<br>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List"
    pageEncoding="UTF-8"%>
    <%@ page import="com.hansung.treeze.model.Quiz" %> 
    
 	<%
 		String classId = request.getParameter("classId");
 		String nodeId = request.getParameter("nodeId");
 		String userEmail = request.getParameter("userEmail");
 	
		List<Quiz> quizes = (List<Quiz>)request.getAttribute("quizes");
 		int qCnt = 0;
		for(Quiz s : quizes) {
			if(s.getType().equals("descriptive")){
				%>
				  <div class="container">
				    <fieldset>

				      <div class="hero-unit">
				        <h4 style="color:red">문제</h4>
				        <p><%=s.getContents() %> </p>
				      </div>

				      <div class="hero-unit">
				        <h4 style="color:red">정답 </h4>
				        <p> 
				          <textarea style="width:450px;" rows="7" id="shortAnswer<%=qCnt %>"></textarea>
				        </p>
				      </div>
				      <input type=hidden value="descriptive" id="type<%=qCnt %>">
				      <input type=hidden value="<%=s.getQuizId()%>" id="quizId<%=qCnt %>">

				    </fieldset>
				    <hr>
				  </div>
				  <%
			}
			else if(s.getType().equals("multipleChoice")){
				%>
				<div class="container">
				  <fieldset>

				    <label>문제 </label>
				    <div class="hero-unit">
				  
				  <p><%=s.getContents() %></p>
				  </div>
				  <label>선택지 내용 </label>
				    <div class="hero-unit">
				  
				  <span class="badge badge-important" id="a1_<%=qCnt%>">1</span>
				  <span onClick="selectAnswer('a1_<%=qCnt%>')" style="cursor:pointer;"><%=s.getExample1() %></span> <p></p>
				  <span class="badge badge-important" id="a2_<%=qCnt%>">2</span> 
				  <span onClick="selectAnswer('a2_<%=qCnt%>')" style="cursor:pointer;"><%=s.getExample2() %></span> <p></p>
				  <span class="badge badge-important" id="a3_<%=qCnt%>">3</span> 
				  <span onClick="selectAnswer('a3_<%=qCnt%>')" style="cursor:pointer;"><%=s.getExample3() %></span> <p></p>
				  <span class="badge badge-important" id="a4_<%=qCnt%>">4</span> 
				  <span onClick="selectAnswer('a4_<%=qCnt%>')" style="cursor:pointer;"><%=s.getExample4() %></span> <p></p>
				  <span class="badge badge-important" id="a5_<%=qCnt%>">5</span> 
				  <span onClick="selectAnswer('a5_<%=qCnt%>')" style="cursor:pointer;"><%=s.getExample5() %></span> <p></p>
				  </div>
				  <hr>
				  <input type=hidden value="multipleChoice" id="type<%=qCnt %>">
				  <input type=hidden value="<%=s.getQuizId()%>" id="quizId<%=qCnt %>">
				  </fieldset>

				  </div>
				  </div>
				  
				  <%
			}
			else
				continue;
	%>
	<%
		qCnt++;
		} %>
		

  <div class="container" align=right>
    <span class="btn btn-danger" onClick="sendAnswer()">제출하기
    </span>
  </div>
  <br>

  <script>

  function sendAnswer(){
  var type = null;
 var resultURI = "/treeze/createResultOfQuiz";
 // var resultURI = "/createQuiz.jsp";
  
  for(i = 0; i < <%=qCnt%>; i++){
    var type = document.getElementById('type' + i);
    var quizId = document.getElementById('quizId' + i).value;
    if(type != null){
      if(type.value == "descriptive"){

        $.post(resultURI,
          {
            classId : <%=classId%>,
            nodeId : <%=nodeId%>,
            quizId : quizId,
            type : "descriptive",
            userEmail : "<%=userEmail%>",
            answerContents : document.getElementById('shortAnswer' + i).value
          },
          function(data,status){
        	  
          });

      }
      else if(type.value == "multipleChoice"){
    	  var answer = new Array();
    	  
    	  for(j = 1; j <= 5; j++){
    		    var answerChk = document.getElementById('a' + j + '_' + i);
    		    if(answerChk.className == "badge badge-info")
    		      answer.push("true");
    		    else
    		      answer.push("false");
    		  }


          $.post(resultURI,
          {
        	classId : <%=classId%>,
            nodeId : <%=nodeId%>,
            quizId : quizId,
            type : "multipleChoice",
            userEmail : "<%=userEmail%>",
            answerNumber5 : answer.pop(),
            answerNumber4 : answer.pop(),
            answerNumber3 : answer.pop(),
            answerNumber2 : answer.pop(),
            answerNumber1 : answer.pop()
          },
          function(data,status){
        	  
          });
        }
        else{
          alert('type Err');
          return;
        }
      }
    }
    if(type == null)
      alert('Q is not exist.');
    else
      alert('sucess submit.');
  }



  function selectAnswer(id){
    var sel = document.getElementById(id);

    if(sel.className == "badge badge-important")
      sel.setAttribute('class', 'badge badge-info');
    else if(sel.className == "badge badge-info")
     sel.setAttribute('class', 'badge badge-important');
   else
    return;
  }

  </script>




        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/modules/exporting.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-transition.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-alert.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-modal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-dropdown.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-scrollspy.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-tab.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-tooltip.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-popover.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-button.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-collapse.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-carousel.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-typeahead.js"></script>


      </body>
      </html>

