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
<script type="text/javascript">
$(document).ready(function(){

    // var quiz = new Quiz();
    // quiz.setMultipleChoiceQuiz(1, 1, "문제", 2, "1보기", "2보기", "3보기", "4보기", "5보기");

    // addMultipleQ(quiz);

    // var test = new Test();
    // test.setQuizId(1);
    // alert(test.getQuizId());
  });

Quiz = function(){
  this.quizId;
  this.type;
  this.time;
  this.contents;
  this.answerContents;
  this.answerNumber;
  this.example1;
  this.example2;
  this.example3;
  this.example4;
  this.example5;
}

Quiz.prototype.setDescriptiveQuiz = function(quizId, time, contents, answerContents){
  this.quizId = quizId;
  this.type = "descriptive";
  this.time = time;
  this.contents = contents;
  this.answerContents = answerContents;
}

Quiz.prototype.setMultipleChoiceQuiz = function(quizId, time, contents, answerNumber,       example1, example2, example3, example4, example5 ){
  this.quizId = quizId;
  this.type = "multipleChoice";
  this.time = time;
  this.contents = contents;
  this.answerNumber = answerNumber;
  this.example1 = example1;
  this.example2 = example2;
  this.example3 = example3;
  this.example4 = example4;
  this.example5 = example5;
}

</script>
<body>

<br>

<div class="container">
  <fieldset>

    <label>문제 </label>
    <div class="hero-unit">
  
  <p>다음 중 임베디드 시스템인것을 모두 고르시오. </p>
  </div>
  <label>선택지 내용 </label>
    <div class="hero-unit">
  
  <span class="badge badge-important" id="q1">1</span>
  <span onClick="selectAnswer('q1')" style="cursor:pointer;">스마트폰 내부 시스템</span> <p></p>
  <span class="badge badge-important" id="q2">2</span> 
  <span onClick="selectAnswer('q2')" style="cursor:pointer;">우주선 시스템</span> <p></p>
  <span class="badge badge-important" id="q3">3</span> 
  <span onClick="selectAnswer('q3')" style="cursor:pointer;">세탁기 시스템</span> <p></p>
  <span class="badge badge-important" id="q4">4</span> 
  <span onClick="selectAnswer('q4')" style="cursor:pointer;">쿼드콥터 시스템</span> <p></p>
  <span class="badge badge-important" id="q5">5</span> 
  <span onClick="selectAnswer('q5')" style="cursor:pointer;">은행 전산 시스템</span> <p></p>
  </div>
  <hr>
  </fieldset>

  </div>
  </div>

  <!-- dewlit -->
  <div class="container">
    <fieldset>

      <div class="hero-unit">
        <h4 style="color:red">문제</h4>
        <p>링커 (Linker)에 대해서 서술하시오. </p>
      </div>

      <div class="hero-unit">
        <h4 style="color:red">정답 </h4>
        <p> 
          <textarea style="width:450px;" rows="7" id="shortQ1"></textarea>
        </p>
      </div>

    </fieldset>
    <hr>
  </div>


  <div class="container" align=right>
    <span class="btn btn-danger" onClick="sendAnswer()">제출하기
    </span>
  </div>
  <br>

  <input type=hidden id="type0" value="multipleChoice">
  <input type=hidden id="type1" value="descriptive">

  <script>

  function sendAnswer(){
  var type = null;
 var resultURI = "/treeze/createResultOfQuiz";
 // var resultURI = "/createQuiz.jsp";
  var answer = new Array();
  for(i = 1; i <= 5; i++){
    var answerChk = document.getElementById('q' + i);
    if(answerChk.className == "badge badge-info")
      answer.push("true");
    else
      answer.push("false");
  }

  for(i = 0; i < 2; i++){
    var type = document.getElementById('type' + i);
    if(type != null){
      if(type.value == "descriptive"){

        $.post(resultURI,
          {
            classId : "1",
            nodeId : "1",
            quizId : "1",
            type : "descriptive",
            userEmail : "dewlit",
            answerContents : document.getElementById('shortQ1').value
          },
          function(data,status){
            alert("Data: " + data + "\nStatus: " + status);
          });

        // qForm.classId.value = 1;
        // qForm.nodeId.value = 1;
        // qForm.quizId.value = 1;
        // qForm.type.value = "descriptive";
        // qForm.time.value = document.getElementById('examTime').value;
        // qForm.contents.value = document.getElementById('contents' + i).value;
        // qForm.answerContents.value = document.getElementById('answerContents' + i).value;
        // qForm.submit();
      }
      else if(type.value == "multipleChoice"){



          $.post(resultURI,
          {
            classId : "1",
            nodeId : "1",
            quizId : "1",
            type : "multipleChoice",
            userEmail : "dewlit",
            answerNumber5 : answer.pop(),
            answerNumber4 : answer.pop(),
            answerNumber3 : answer.pop(),
            answerNumber2 : answer.pop(),
            answerNumber1 : answer.pop()
          },
          function(data,status){
            alert("Data: " + data + "\nStatus: " + status);
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
      alert('sucess save');
  }



  function addMultipleQ(quiz){
    var body = document.body;
    body.innerHTML = body.innerHTML + 
    "<div class='container'>" + 
    "<fieldset>" + 

    "<label>문제 </label>" + 
    "<div class='hero-unit'>" + 
  
  "<p>" + quiz.contents + "</p>" + 
  "</div>" + 
  "<label>선택지 내용 </label>" +
    "<div class='hero-unit'>" +
  
  "<span class='badge badge-important' id='q1" + quiz.quizId + "'>1</span>" + 
  "<span onClick='selectAnswer('q1" + quiz.quizId + "') style='cursor:pointer;'>" + quiz.example1 + "</span> <p></p>" + 
  "<span class='badge badge-important' id='q2" + quiz.quizId + "'>2</span>" + 
  "<span onClick='selectAnswer('q2" + quiz.quizId + "') style='cursor:pointer;'>" + quiz.example2 + "</span> <p></p>" + 
  "<span class='badge badge-important' id='q3" + quiz.quizId + "'>3</span>" + 
  "<span onClick='selectAnswer('q3" + quiz.quizId + "') style='cursor:pointer;'>" + quiz.example3 + "</span> <p></p>" + 
  "<span class='badge badge-important' id='q4" + quiz.quizId + "'>4</span>" + 
  "<span onClick='selectAnswer('q4" + quiz.quizId + "') style='cursor:pointer;'>" + quiz.example4 + "</span> <p></p>" + 
  "<span class='badge badge-important' id='q5" + quiz.quizId + "'>1</span>" + 
  "<span onClick='selectAnswer('q5" + quiz.quizId + "') style='cursor:pointer;'>" + quiz.example5 + "</span> <p></p>" + 
  "</div>" + 
  "<hr>" + 
  "</fieldset>" + 
  "</div>" + 
  "</div>";
  // <span class="badge badge-important" id="q2">2</span> 
  // <span onClick="selectAnswer('q2')" style="cursor:pointer;">우주선 시스템</span> <p></p>
  // <span class="badge badge-important" id="q3">3</span> 
  // <span onClick="selectAnswer('q3')" style="cursor:pointer;">세탁기 시스템</span> <p></p>
  // <span class="badge badge-important" id="q4">4</span> 
  // <span onClick="selectAnswer('q4')" style="cursor:pointer;">쿼드콥터 시스템</span> <p></p>
  // <span class="badge badge-important" id="q5">5</span> 
  // <span onClick="selectAnswer('q5')" style="cursor:pointer;">은행 전산 시스템</span> <p></p>
  
  }

  function addShortQ(){

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

