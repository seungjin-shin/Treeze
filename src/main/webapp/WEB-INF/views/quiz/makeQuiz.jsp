<%@ page contentType = "text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>


<title>Make Quiz</title>
</script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery-1.10.2.min.js">
</script>

<script>
// $(document).ready(function(){
	// $("#save").click(function(){
 //  $.post("createQuiz.jsp",
 //  {
 //    name:"Donald Duck",
 //    city:"Duckburg"
 //  },
 //  function(data,status){
 //    alert("Data: " + data + "\nStatus: " + status);
 //  });
// });
// });

// $(document).ready(function(){
//   $("button").click(function(){
//     $.get("index.jsp",function(data,status){
//       alert("Data: " + data + "\nStatus: " + status);
//     });
//   });
// });

</script>

<!-- <button>Send an HTTP GET request to a page and get the result back</button> -->
<body>

<select id="question">
    <option value="0" selected="selected">문제 유형</option>
    <option value="multiple">객관식</option>
    <option value="short">주관식</option>
</select>

<!-- <span id="multipleCntSpan"></span> -->

<input type=button onClick="addQuestion();" value="추가">
총 시험 시간 : <input type=text size=5 id="examTime">(분 단위 숫자만 입력)

<table id="qTable">
	<tbody id='qBody'>

	</tbody>

</table>

<input type=button value="저장하기" id="save" onClick="makeQuiz()">

<!-- <form action="/createQuiz.jsp" method=post id="qForm">
	<input type=hidden value="" name="classId">
	<input type=hidden value="" name="nodeId">
	<input type=hidden value="" name="quizId">
	<input type=hidden value="" name="type">
	<input type=hidden value="" name="time">
	
	<input type=hidden value="" name="contents">

	<input type=hidden value="" name="answerContents">
	<input type=hidden value="" name="answerNumber">

	<input type=hidden value="" name="example1">
	<input type=hidden value="" name="example2">
	<input type=hidden value="" name="example3">
	<input type=hidden value="" name="example4">
	<input type=hidden value="" name="example5">

</form> -->

<input type=hidden value=0 id="qCnt">

<script type="text/javascript">
var qBody = document.getElementById('qBody');
var qCnt = document.getElementById('qCnt');
var tr;
var td;
var table;
var addTr;
var makeQuizURI = "/treeze/createQuiz";

function makeQuiz(){
	var type = null;
	for(i = 0; i < qCnt.value; i++){
		var type = document.getElementById('type' + i);
		if(type != null){
			if(type.value == "descriptive"){

				$.post(makeQuizURI,
				  {
				  	classId : "1",
				  	nodeId : "1",
				  	quizId : "1",
				  	type : "descriptive",
				  	time : document.getElementById('examTime').value,
				 	contents : document.getElementById('contents' + i).value,
					answerContents : document.getElementById('answerContents' + i).value
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
					$.post(makeQuizURI,
				  {
				  	classId : "1",
				  	nodeId : "1",
				  	quizId : "1",
				  	type : "multipleChoice",
				  	time : document.getElementById('examTime').value,
				 	contents : document.getElementById('contents' + i).value,
					answerNumber : document.getElementById('answerNumber' + i).value,
					example1 : document.getElementById('example1_' + i).value,
					example2 : document.getElementById('example2_' + i).value,
					example3 : document.getElementById('example3_' + i).value,
					example4 : document.getElementById('example4_' + i).value,
					example5 : document.getElementById('example5_' + i).value,
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

function addQuestion(){
	var sel = document.getElementById('question').value;

	if(sel == "0")
		return;
	else if(sel == "multiple")
		addMultipleQ();
	else if(sel == "short")
		addShort();
	else
		return;

	qCnt.value = parseInt(qCnt.value) + 1;

}

function delQuestion(qNum){

	var delRow = document.getElementById('qID' + qNum);

	qBody.removeChild(delRow);
}

function addShort(){
	addTr = document.createElement('tr');
	addTr.setAttribute('id', 'qID' + qCnt.value);

	table = document.createElement('table');
	tr = document.createElement('tr');

	tr.innerHTML = "문제<input type='text' size='70' id='contents" + qCnt.value + "'>" + 
					"<input type=hidden value='descriptive' id='type" + qCnt.value + "'>";

	table.appendChild(tr);

	tr = document.createElement('tr');

	tr.innerHTML = "정답<input type='text' size='70' id='answerContents" + qCnt.value + "'>";
	table.appendChild(tr);

	tr = document.createElement('tr');
	tr.setAttribute('align', 'right');
	tr.innerHTML = "<input type=button value='삭제' onClick='delQuestion(" + qCnt.value + ")'>";
	table.appendChild(tr);

	tr = document.createElement('tr');
	tr.innerHTML = "<hr>";
	table.appendChild(tr);

	addTr.appendChild(table);

	qBody.appendChild(addTr);
}

function addMultipleQ(){
	addTr = document.createElement('tr');
	addTr.setAttribute('id', 'qID' + qCnt.value);

	table = document.createElement('table');
	tr = document.createElement('tr');

	tr.innerHTML = "문제<input type='text' size='70' id='contents" + qCnt.value + "'>" + 
					"<input type=hidden value='multipleChoice' id='type" + qCnt.value + "'>";
	table.appendChild(tr);

	tr = document.createElement('tr');

	var tmp = "";
	for(i = 1; i <= 5; i++){
		tr.innerHTML = tmp + i + "번 <input type='text' size='70' id='example" + i + "_" + qCnt.value + "'>";
		tmp = tr.innerHTML + "<br>";
	}
	table.appendChild(tr);

	tr = document.createElement('tr');

	tr.innerHTML = "정답<select id='answerNumber" + qCnt.value + "'>"  +
	        		"<option value=1 selected='selected'>1번</option>" + 
	        		"<option value=2>2번</option>" + 
	        		"<option value=3>3번</option>" + 
	        		"<option value=4>4번</option>" + 
	        		"<option value=5>5번</option></select>";

	// var tmp = td.innerHTML;
	// for(i = 2; i <= mCnt; i++){
	// 	td.innerHTML = tmp + "<option value='" + i +"'>" + i + "번</option>";
	// 	tmp = td.innerHTML;
	// }
	// td.innerHTML = tmp + "</select>";
	table.appendChild(tr);

	tr = document.createElement('tr');
	tr.setAttribute('align', 'right');
	tr.innerHTML = "<input type=button value='삭제' onClick='delQuestion(" + qCnt.value + ")'>";
	table.appendChild(tr);

	tr = document.createElement('tr');
	tr.innerHTML = "<hr>";
	table.appendChild(tr);

	addTr.appendChild(table);

	qBody.appendChild(addTr);
}




</script>

</body>
</html>