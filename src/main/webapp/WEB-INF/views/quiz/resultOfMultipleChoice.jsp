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

<body>

<div class="container">
  <div class="btn-group">
    <button class="btn btn-info dropdown-toggle" data-toggle="dropdown">퀴즈 타입 <span class="caret"></span></button>
    <ul class="dropdown-menu">
      <li><a href="#">객관식</a></li>
      <li><a href="#">주관식</a></li>
      
      <li class="divider"></li>
      <li><a href="#">Separated link</a></li>
    </ul>
  </div>
  <fieldset>

    <label>제한 시간</label>
    <input type="text" placeholder="Limited Time..">
    
    <label>퀴즈내용 </label>
    <div class="hero-unit">
  
  <p>다음 중 임베디드 시스템인것을 모두 고르시오. </p>
  </div>
<label>선택지 내용 </label>
    <div class="hero-unit">
  
  <span class="badge badge-important">1</span> 스마트폰 내부 시스템 <p></p>
  <span class="badge badge-important">2</span> 우주선 시스템 <p></p>
  <span class="badge badge-info">3</span> 세탁기 시스템 (정답)<p></p>
  <span class="badge badge-important">4</span> 쿼드콥터 시스템 <p></p>
  <span class="badge badge-important">5</span> 은행 전산 시스템 <p></p>
  </div>

  <hr>
            <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/jquery-1.9.1.min.js"></script>
            <script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: '퀴즈 결과  '
            },
            xAxis: {
                categories: ['1번 - 스마트폰 내부 시스템', '2번 - 우주선 시스템', '3번 - 세탁기 시스템 ', '4번 - 쿼드콥터 시스템', '5번 - 은행 전산 시스템']
            },
            yAxis: {
                min: 0,
                title: {
                    text: '정답 인원수 '
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
                name: '정답 인원',
                data: [115, 203, 140, 107, 202]
            }]
        });
    });



</script>
<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
<hr> 

            <table class="table">
              <thead>
                <tr>
                  <th>#</th>
                  <th>학번</th>
                  <th>이름 </th>
                  <th>기입한 답</th>
                  <th>정답 여부</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>0892067</td>
                  <td>송태웅</td>
                  <td>3번 - 세탁기 시스템</td>
                  <td><span class="label label-info">O</span></td>
                </tr>
        <tr>
                  <td>1</td>
                  <td>0892080</td>
                  <td>신건영</td>
                  <td>3번 - 세탁기 시스템</td>
                  <td><span class="label label-info">O</span></td>
                </tr>
        
        <tr>
                  <td>1</td>
                  <td>0892045</td>
                  <td>신승진</td>
                  <td>1번 - 스마트 내부 시스템</td>
                  <td><span class="label label-important">X</span></td>
                </tr>
        
        <tr>
                  <td>1</td>
                  <td>0892067</td>
                  <td>장영창</td>
                  <td>3번 - 세탁기 시스템</td>
                  <td><span class="label label-info">O</span></td>
                </tr>
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
  
  </fieldset>


  </div>




        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
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

