<%@ page contentType = "text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
<head>

	<!-- Meta Tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<title>Chakra | Responsive One Page Template</title>   

	<meta name="description" content="" /> 

	<!-- Mobile Specifics -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="HandheldFriendly" content="true"/>
	<meta name="MobileOptimized" content="320"/>   

	<!-- Mobile Internet Explorer ClearType Technology -->
	<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->

	<!-- Bootstrap -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/bootstrap.min.css" rel="stylesheet">

	<!-- Main Style -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/main.css" rel="stylesheet">

	<!-- Supersized -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/supersized.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/_include/css/supersized.shutter.css" rel="stylesheet">

	<!-- FancyBox -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">

	<!-- Font Icons -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/fonts.css" rel="stylesheet">

	<!-- Shortcodes -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/shortcodes.css" rel="stylesheet">

	<!-- Responsive -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/_include/css/responsive.css" rel="stylesheet">

	<!-- Custom CSS -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/custom.css" rel="stylesheet">

	<!-- Google Font -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>

	<style type="text/css">

		.selected2 {
			box-shadow: 0px 0px 5px 4px #444444;
		}

		.ufb-functions {
			float:left;
			padding-right:20px;
			margin-left : 15px;
			border-right : 1px solid #cccccc;
			padding-bottom:20px;
		}

		.ufb-functions-list {

			margin: 30px;
			height: 760px;
			overflow-y:scroll;
			padding-right: 20px;
			border: 2px solid #cecece;

		}

		.ufb-functions-list ul {
			list-style: none;
			margin-left:10px;
			padding:0px;
		}

		.ufb-functions-list li {

			margin: 0 auto;
			width: 80%;
			height: 220px;
			border: 2px solid #cecece;
			border-radius: 4px;
			padding:10px;
			margin-top:15px;
			cursor: pointer;
		}

	</style>

	<!-- Fav Icon -->
	<link rel="shortcut icon" href="#">

	<link rel="apple-touch-icon" href="#">
	<link rel="apple-touch-icon" sizes="114x114" href="#">
	<link rel="apple-touch-icon" sizes="72x72" href="#">
	<link rel="apple-touch-icon" sizes="144x144" href="#">

	<!-- Modernizr -->
	<script src="${pageContext.request.contextPath}/resources/_include/js/modernizr.js"></script>

	<!-- Analytics -->
	<script type="text/javascript">

		var _gaq = _gaq || [];
		_gaq.push(['_setAccount', 'YOUR ID']);
		_gaq.push(['_trackPageview']);

		(function() {
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		})();

	</script>
	<!-- End Analytics -->

	<script type="text/javascript">


		var treezingHtml = new Array(); 

		var thumbnailSlidelist = "<div class='ufb-functions-list'> <ul> <li class='selected2'><a href='#' class='thumbnail'><img src='${pageContext.request.contextPath}/resources/_include/img/test/slide1.png' width='80%' height='80%'></a></li><li><a href='#' class='thumbnail'><img src='${pageContext.request.contextPath}/resources/_include/img/test/slide2.png' width='80%' height='80%'></a></li><li><a href='#' class='thumbnail'><img src='${pageContext.request.contextPath}/resources/_include/img/test/slide3.png' width='80%' height='80%'></a></li><li><a href='#' class='thumbnail'><img src='${pageContext.request.contextPath}/resources/_include/img/test/slide4.png' width='80%' height='80%'></a></li><li><a href='#' class='thumbnail'><img src='${pageContext.request.contextPath}/resources/_include/img/test/slide5.png' width='80%' height='80%'></a></li><li><a href='#' class='thumbnail'><img src='${pageContext.request.contextPath}/resources/_include/img/test/slide6.png' width='80%' height='80%'></a></li><li><a href='#' class='thumbnail'><img src='${pageContext.request.contextPath}/resources/_include/img/test/slide7.png' width='80%' height='80%'></a></li></ul> </div></div>";

		treezingHtml[0] =" <div class='row-fluid'><div class='span6'> <img src='${pageContext.request.contextPath}/resources/_include/img/test/slide1.png' width='100%' height='100%'> </div> <div class='span6' style= 'height: 100%;'> <h1>Question</h1><button type='button' class='btn btn-warning pull-right' style='margin-right:30px'>질문 작성</button><table class='table table-hover'> <thead> <tr> <th>#</th> <th>Lecture Name</th> <th>Create Date</th> <th>Personnel</th> </tr> </thead> <tbody> <tr> <td>1</td> <td> <a href='myclass.html'>미분적분학 </a></td> <td>2013.8.29</td> <td>23</td> </tr> <tr > <td>2</td> <td><a href='myclass.html'>컴퓨터구조</a></td> <td>2013.9.3</td> <td>52</td> </tr> <tr > <td>3</td> <td>알고리즘</td> <td>2012.2.4</td> <td>43</td> </tr> </tbody> </table> <div class='pagination pagination-centered'> <ul> <li class='disabled'><a href='#'>«</a></li> <li class='active'><a href='#'>1</a></li> <li><a href='#'>2</a></li> <li><a href='#'>3</a></li> <li><a href='#'>4</a></li> <li><a href='#'>5</a></li> <li><a href='#'>»</a></li> </ul> </div></div> <div class='span12' style= 'height: 100%;'> <iframe src='http://app.wisemapping.com/c/maps/3/try' width='100%' height='100%'> </iframe> </div></div>";      

		treezingHtml[1] = "<img src='${pageContext.request.contextPath}/resources/_include/img/test/slide1.png' width='100%' height='100%'>";

		treezingHtml[2] = " <iframe src='http://app.wisemapping.com/c/maps/3/try' width='100%' height='100%'> </iframe> ";

		treezingHtml[3] = "<div class='row-fluid'><div class='span4'>"+thumbnailSlidelist+"<div class='span8'><h1>Best Question</h1><table class='table'> <thead> <tr> <th>#</th> <th>Title</th> <th>Lecture Name</th> <th>Username</th> <th>Create Date</th> </tr> </thead> <tbody> <tr> <td>1</td> <td><a>운영체제의 정의가 무엇입니까 ? </a></td> <td>운영 체제</td> <td>김두형</td> <td>2013.9.23 06:13</td> </tr> <tr> <td>2</td> <td>CPU의 역할은 무엇인가요 ? </td> <td>컴퓨터 구조</td> <td>오영희</td> <td>2013.9.20 13:00</td> </tr> <tr> <td>3</td> <td>수업 시간에 말한 저 그림이 이해가 안됩니다.</td> <td>운영체제</td> <td>정말자</td> <td>2013.9.13 15:40</td> </tr> <tr> <td>4</td> <td>memory 관리에 관하여..</td> <td>컴퓨터구조</td> <td>이미자</td> <td>2013.9.10 13:10</td> <tr> <td>5</td> <td>os약어 풀이는 ? </td> <td>운영체제</td> <td>마현수</td> <td>2013.9.3 21:00</td> </tr> </tr> </tbody> </table><h1>Question</h1><button type='button' class='btn btn-warning pull-right' style='margin-right:30px'>질문 작성</button><table class='table'> <thead> <tr> <th>#</th> <th>Title</th> <th>Lecture Name</th> <th>Username</th> <th>Create Date</th> </tr> </thead> <tbody> <tr> <td>1</td> <td><a>운영체제의 정의가 무엇입니까 ? </a></td> <td>운영 체제</td> <td>김두형</td> <td>2013.9.23 06:13</td> </tr> <tr> <td>2</td> <td>CPU의 역할은 무엇인가요 ? </td> <td>컴퓨터 구조</td> <td>오영희</td> <td>2013.9.20 13:00</td> </tr> <tr> <td>3</td> <td>수업 시간에 말한 저 그림이 이해가 안됩니다.</td> <td>운영체제</td> <td>정말자</td> <td>2013.9.13 15:40</td> </tr> <tr> <td>4</td> <td>memory 관리에 관하여..</td> <td>컴퓨터구조</td> <td>이미자</td> <td>2013.9.10 13:10</td> <tr> <td>5</td> <td>os약어 풀이는 ? </td> <td>운영체제</td> <td>마현수</td> <td>2013.9.3 21:00</td> </tr> </tr> </tbody> </table></div>";
		
		treezingHtml[4] =  "<div class='row-fluid'><div class='span4'>"+thumbnailSlidelist+"<div class='span8'><h1>Suervey</h1><table class='table'> <thead> <tr> <th>#</th> <th>Title</th> <th>Lecture Name</th> <th>Username</th> <th>Create Date</th> </tr> </thead> <tbody> <tr> <td>1</td> <td><a>운영체제의 정의가 무엇입니까 ? </a></td> <td>운영 체제</td> <td>김두형</td> <td>2013.9.23 06:13</td> </tr> <tr> <td>2</td> <td>CPU의 역할은 무엇인가요 ? </td> <td>컴퓨터 구조</td> <td>오영희</td> <td>2013.9.20 13:00</td> </tr> <tr> <td>3</td> <td>수업 시간에 말한 저 그림이 이해가 안됩니다.</td> <td>운영체제</td> <td>정말자</td> <td>2013.9.13 15:40</td> </tr> <tr> <td>4</td> <td>memory 관리에 관하여..</td> <td>컴퓨터구조</td> <td>이미자</td> <td>2013.9.10 13:10</td> <tr><tr> <td>2</td> <td>CPU의 역할은 무엇인가요 ? </td> <td>컴퓨터 구조</td> <td>오영희</td> <td>2013.9.20 13:00</td> </tr> <tr> <td>3</td> <td>수업 시간에 말한 저 그림이 이해가 안됩니다.</td> <td>운영체제</td> <td>정말자</td> <td>2013.9.13 15:40</td> </tr> <tr> <td>4</td> <td>memory 관리에 관하여..</td> <td>컴퓨터구조</td> <td>이미자</td> <td>2013.9.10 13:10</td> <tr> <td>5</td> <td>os약어 풀이는 ? </td> <td>운영체제</td> <td>마현수</td> <td>2013.9.3 21:00</td> </tr> </tr> </tbody> </table></div>";
		
		treezingHtml[5] =  "<div class='row-fluid'><div class='span4'>"+thumbnailSlidelist+"<div class='span8'><h1>Quiz</h1><table class='table'> <thead> <tr> <th>#</th> <th>Title</th> <th>Lecture Name</th> <th>Username</th> <th>Create Date</th> </tr> </thead> <tbody> <tr> <td>1</td> <td><a>운영체제의 정의가 무엇입니까 ? </a></td> <td>운영 체제</td> <td>김두형</td> <td>2013.9.23 06:13</td> </tr> <tr> <td>2</td> <td>CPU의 역할은 무엇인가요 ? </td> <td>컴퓨터 구조</td> <td>오영희</td> <td>2013.9.20 13:00</td> </tr> <tr> <td>3</td> <td>수업 시간에 말한 저 그림이 이해가 안됩니다.</td> <td>운영체제</td> <td>정말자</td> <td>2013.9.13 15:40</td> </tr> <tr> <td>4</td> <td>memory 관리에 관하여..</td> <td>컴퓨터구조</td> <td>이미자</td> <td>2013.9.10 13:10</td> <tr> <td>5</td> <td>os약어 풀이는 ? </td> <td>운영체제</td> <td>마현수</td> <td>2013.9.3 21:00</td> </tr> </tr> </tbody> </table></div>";

		function reloadTreezing(direction){

			var lenOfMenu = 6; 
			var treezingView = document.getElementById('treezingView');

			var slideList = document.getElementById('slide-list');
			
			var li = slideList.getElementsByTagName('li');

			for (i = 0; i < li.length; i++) {
				var tmpLi = li[i];

				if(tmpLi.getAttribute('class') == 'slide-link-' + i + ' current-slide'){

					if(direction == 'prev'){
						
						if(i == 0)
							treezingView.innerHTML = treezingHtml[5];
						else 	
							treezingView.innerHTML = treezingHtml[i-1];
					}
					else if (direction == 'next')
						
						treezingView.innerHTML = treezingHtml[(i+1)%6];
					
				}
			}

		}
		function on_load(){

			$.get( "${pageContext.request.contextPath}/ajax/mainpage", function( data ) {
				treezingHtml[0] = data;
				var treezingView = document.getElementById('treezingView');				
				treezingView.innerHTML = treezingHtml[0] ;

			});;
			$("#my_course").click(function() {
				$.get( "${pageContext.request.contextPath}/ajax/mycourse", function( data ) {					
					var lecture_tbody = document.getElementById('lecture_tbody');
					lecture_tbody.innerHTML = data;
					$( "#my_course" ).attr({"class":"selected"});
					$( "#all_lecture" ).attr({"class":"unselected"});
				});; 
			});

			$("#all_lecture").click(function() {
				$.get( "${pageContext.request.contextPath}/ajax/allLecture", function( data ) {					
					var lecture_tbody = document.getElementById('lecture_tbody');
					lecture_tbody.innerHTML = data;
					$( "#my_course" ).attr({"class":"unselected"});
					$( "#all_lecture" ).attr({"class":"selected"});
				});; 
			});

			//add_lecture
			


			$("#add_lecture").click(function() {
				var $lecture_tbody = $("#lecture_tbody");
				var lecture_size = $lecture_tbody.children().size();
				var lecture_ids= "";				

				for(var i = 0; i < lecture_size; i++) {
					var $tr = $($lecture_tbody.children()[i]);
					var $td = $($tr.children()[4]);
					var checkbox = $td.children().children().children();					

					if(checkbox[0].checked) {
						
						var lecture_index = $($tr.children()[0]).text();
						
						lecture_ids += lecture_index + " ";
					}

				}
				console.log(lecture_ids);

				$.post( "${pageContext.request.contextPath}/ajax/addCourse", { lectureIdList: lecture_ids}, function( data ) {
										
					//var $data = $(data);
					$html_body=$("#html_body");
					$html_body.append(data)

					$("#course_add_confirm_btn").click(function() {
						$("#lecture_dialog").attr({"style":"visibility:hidden"});			

					});

					
				});	

			});


		}

		function on_keycode(){

			if(event.keyCode == "37"){
				reloadTreezing('prev');
			}
			if(event.keyCode == "39")
				reloadTreezing('next');
		}



	</script>


</head>                                                                             


<body id = "html_body" onkeydown="on_keycode()" onload="on_load()">

	<!-- This section is for Splash Screen -->
	<div class="ole">
		<section id="jSplash">
			<div id="circle"></div>
		</section>
	</div>
	<!-- End of Splash Screen -->

	<!-- Homepage Slider -->
	<div id="home-slider">	
		<div class="overlay" id="treezingView">

		</div>

		<div class="control-nav">
			<a id="prevslide" class="load-item" onclick="reloadTreezing('prev')"><i class="font-icon-arrow-simple-left"></i></a>
			<a id="nextslide" class="load-item" onclick="reloadTreezing('next')"><i class="font-icon-arrow-simple-right"></i></a>
			<ul id="slide-list"></ul>

			<a id="nextsection" href="#course"><i class="font-icon-arrow-simple-down"></i></a>
		</div>
	</div>
	<!-- End Homepage Slider -->

	<!-- Header -->
	<header>
		<div class="sticky-nav">
			<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>

			<div id="logo">
				<a id="goUp" href="#home-slider" title="Treeze | Responsive One Page Template">Treeze Template</a>
			</div>

			<nav id="menu">
				<ul id="menu-nav">
					<li class="current"><a href="#home-slider">Treezing</a></li>
					<li><a href="#course">My course</a></li>
					<li><a href="#ranking">Ranking</a></li>
					<li><a href="#tutorial">Tutorial</a></li>
					<li><a href="#feedback">Feedback</a></li>


				</ul>
			</nav>

		</div>
	</header>
	<!-- End Header -->

	<!-- Our Work Section -->
	<div id="course" class="page">
		<div class="container">


			<!-- Portfolio Projects -->
			<div class="row">
				<div class="span12">
					<!-- Filter -->
					<nav id="options" class="work-nav">
						<ul id="filters" class="option-set" data-option-key="filter">
							<li class="type-work">나의 수강관리</li>
							<li><a id="all_lecture" href="#filter" data-option-value="*" class="selected">All Lecture</a></li>
							<li><a id="my_course" data-option-value=".design">My Course</a></li>
							

						</ul>
					</nav>
					<!-- End Filter -->
				</div>


				<div class="span12">
					<div class="row">

						<!-- Course Management List -->

						<a class="btn btn-warning pull-right" style="margin-left:10px" href="addMypage.html">수강 취소</a>
						<a id="add_lecture" class="btn btn-info pull-right" style="margin-left:10px" >수강 신청</a>

						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Lecture Name</th>
									<th>Create Date</th>
									<th>Personnel</th>

								</tr>
							</thead>
							<tbody id = "lecture_tbody">

								<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List"
								pageEncoding="UTF-8"%>
								<%@	page import="com.hansung.treeze.model.Lecture" %> 

								<%
								List<Lecture> lectures = (List<Lecture>)request.getAttribute("lectures");
								int i = 0;
								for(Lecture lecture : lectures) {
								%>
								<tr >
									<td><%= lecture.getLectureId() %></td>
									<td><%= lecture.getLectureName() %></td>
									<td><%= lecture.getProfessorName() %></td>
									<td>0</td>
									<td>  
										<div class="checkbox">
											<label>
												<input type="checkbox">
											</label>
										</div>
									</td>
								</tr>
								<%
							}
							%>


						</tbody>
					</table>
					<div class="pagination pagination-centered">
						<ul>
							<li class="disabled"><a href="#">«</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">»</a></li>
						</ul>
					</div>

				</div>
			</div>
		</div>
		<!-- End Portfolio Projects -->

	</div>


</div>
<!-- End Our Work Section -->



<script src="${pageContext.request.contextPath}/resources/_include/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function () {
		$('#container1').highcharts({
			chart: {
				type: 'column',
				margin: [ 50, 50, 100, 80]
			},
			title: {
				text: '접속횟수 순위 '
			},
			xAxis: {
				categories: [
				'신승진',
				'송태웅',
				'신건영',
				'김두형',
				'김병수',
				'Mumbai',
				'Sao Paulo',
				'Mexico City',
				'Dehli',
				'Osaka',
				'Cairo',
				'Kolkata',
				'Los Angeles',
				'Shanghai',
				'Moscow',
				'Beijing',
				'Buenos Aires',
				'Guangzhou',
				'Shenzhen',
				'Istanbul'
				],
				labels: {
					rotation: -45,
					align: 'right',
					style: {
						fontSize: '13px',
						fontFamily: 'Verdana, sans-serif'
					}
				}
			},
			yAxis: {
				min: 0,
				title: {
					text: 'Population (millions)'
				}
			},
			legend: {
				enabled: false
			},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.x +'</b><br/>'+
					'Population in 2008: '+ Highcharts.numberFormat(this.y, 1) +
					' millions';
				}
			},
			series: [{
				name: 'Population',
				data: [34.4, 21.8, 20.1, 20, 19.6, 19.5, 19.1, 18.4, 18,
				17.3, 16.8, 15, 14.7, 14.5, 13.3, 12.8, 12.4, 11.8,
				11.7, 11.2],
				dataLabels: {
					enabled: true,
					rotation: -90,
					color: '#FFFFFF',
					align: 'right',
					x: 4,
					y: 10,
					style: {
						fontSize: '13px',
						fontFamily: 'Verdana, sans-serif'
					}
				}
			}]
		});
});


</script>
<script type="text/javascript">
	$(function () {

    	// Radialize the colors
    	Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
    		return {
    			radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
    			stops: [
    			[0, color],
		            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
		            ]
		        };
		    });

		// Build the chart
		$('#container2').highcharts({
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text: '나의 출석률 '
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage}%</b>',
				percentageDecimals: 1
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: true,
						color: '#000000',
						connectorColor: '#000000',
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
						}
					}
				}
			},
			series: [{
				type: 'pie',
				name: 'Browser share',
				data: [
				['출석',   75.0],
				['지각',       15.0],

				['조퇴',    10.0]
				
				]
			}]
		});
	});


</script>

<script type="text/javascript">
	$(function () {
		$('#container3').highcharts({
			chart: {
				type: 'bar'
			},
			title: {
				text: '질문 순위 '
			},
			xAxis: {
				categories: ['김병수', '신승진', '신건영', 'Grapes', 'Bananas']
			},
			yAxis: {
				min: 0,
				title: {
					text: 'Total fruit consumption'
				}
			},
			legend: {
				backgroundColor: '#FFFFFF',
				reversed: true
			},
			plotOptions: {
				series: {
					stacking: 'normal'
				}
			},
			series: [{
				name: '질문글 개수',
				data: [43, 24, 14, 12, 5]
			},{
				name: '베스트 질문 선정',
				data: [13, 10, 6, 3, 1]
			}]
		});
	});


</script>


<!-- About Section -->
<div id="ranking" class="page-alternate">
	<div class="container">
		<hr style="border-color: #BB8E65;">
		<!-- Title Page -->
		<div class="row">
			<div class="span12">
				<div class="title-page">
					<h2 class="title">Ranking</h2>


					<div class="page-description">
						<div class="row">
							<!-- Edit profile -->
							<div class="span5">
								<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
							</div>
							<div class="span7">
								<div id="container3" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
							</div>
							<div class="span12">
								<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
							</div>
							
						</div>
					</div>

				</div>
			</div>
		</div>
		
	</div>
</div>



<!-- End Services Section -->
<div id="tutorial" class="page-alternate">
	<div class="container">
		<hr style="border-color: #BB8E65;">
		<!-- Title Page -->
		<div class="row">
			<div class="span12">
				<div class="title-page">
					<h2 class="title">Tutorial</h2>

					<div class="page-description">

					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="feedback" class="page-alternate">
	<div class="container">
		<hr style="border-color: #BB8E65;">
		<!-- Title Page -->
		<div class="row">
			<div class="span12">
				<div class="title-page">
					<h2 class="title">Feedback</h2>
					<center>
						<div class="page-description" style="border-radius: 6px;background-color: #A1C4B8;">
							<h3>What's on your feedback?</h3>
							<textarea rows="6" placeholder="Your Feedback" style="width: 70%;"> </textarea>
							<input type="text" placeholder="your@e-mail.com" style="width: 50%;"><br>
							<input type="text" placeholder="Name" style="width: 50%;"><br>
							<button class="btn btn-centered">Contact us</button>
						</div>
					</center>


				</div>
			</div>
		</div>
	</div>

</div>
</div>



<!-- Footer -->
<footer>
	<p class="credits">&copy;2013 Treeze. <a href="http://themes.alessioatzeni.com/html/chakra/" title="Chakra | Responsive One Page Template">Teaching Assistant Using Mind-maps</a> by <a href="http://www.alessioatzeni.com/" title="Alessio Atzeni | Web Designer &amp; Front-end Developer">Hansung university Treezed</a></p>
</footer>
<!-- End Footer -->

<!-- Back To Top -->
<a id="back-to-top" href="#">
	<i class="font-icon-arrow-simple-up"></i>
</a>
<!-- End Back to Top -->


<!-- Js -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->


<script src="${pageContext.request.contextPath}/resources/_include/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/resources/_include/js/modules/exporting.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?sensor=true"></script> <!-- Google Map API -->
<script src="${pageContext.request.contextPath}/resources/_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/_include/js/supersized.3.2.7.min.js"></script> <!-- Slider -->
<script src="${pageContext.request.contextPath}/resources/_include/js/waypoints.js"></script> <!-- WayPoints -->
<script src="${pageContext.request.contextPath}/resources/_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.tweet.js"></script> <!-- Tweet -->
<script src="${pageContext.request.contextPath}/resources/_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
<script src="${pageContext.request.contextPath}/resources/_include/js/main.js"></script> <!-- Default JS -->
<!-- End Js -->

</body>
</html>