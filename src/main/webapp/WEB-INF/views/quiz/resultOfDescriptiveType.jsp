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


      <div class="hero-unit">
        <h4 style="color:red">퀴즈 내용</h4>
        <p>링커 (Linker)에 대해서 서술하시오. </p>
      </div>

      <div class="hero-unit">
        <h4 style="color:red">정답 </h4>
        <p> 링커는 object 파일을 가지고  exe파일과 map파일을 생성하는데 exe파일은 실행파일이고 map파일은 -m 옵션으로 생성된 파일인데 이것을 보면 섹션당 메모리 소요량을 쉽게 파악할 수 있다. 여기서 섹션이라는 용어가 생소할 수 있는데 이것은 링커가 여러 가지 object 파일을 공통된 성질의 것들로 모아서 관리하는 포맷이라 할 수 있겠다. 이렇게 많은 object파일을 분류하는데 있어서 어떤 규칙이 사용되는데 TI에서는 AT&T사가 개발한 COFF(Common Object File Format)을 사용한다. COFF의 규칙은 프로그램 개수와 상관없이 프로그램을 몇 개의 요소로 구분할 수 있다. 프로그램의 수가 아무리 많아도 공통된 섹션은 대 여섯 개로 압축되는데 이렇게 모여진 섹션들을 최종 실행 파일로 만드는 것이 링커의 주 기능이다. 이러한 과정에서 링커 명령 파일(Linker command file)이 object 파일을 어떻게 결합하고 프로그램 데이터를 임베디드 시스템 메모리의 어느 곳에 배치할지를 링커에게 명령한다. 
          [출처] 『링커란 무엇일까?』|작성자 까꿍도사

        </p>
      </div>
      <button type="button" class="btn btn-info pull-right">채점 결과 저장 </button>

      <table class="table"  border="0" cellpadding="0" cellspacing="0" width="60%" style="table-layout:fixed">
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
            <td>   <label class="checkbox">
            <input type="checkbox">
            </label></td>
          </tr>
          <tr>
            <td>1</td>
            <td>0892067</td>
            <td>송태웅</td>
            <td>3번 - 세탁기 시스템</td>
             <td>   <label class="checkbox">
            <input type="checkbox">
            </label></td>
          </tr>

          <tr>
            <td>1</td>
            <td>0892067</td>
            <td>송태웅</td>
            <td>3번 - 세탁기 시스템</td>
             <td>   <label class="checkbox">
            <input type="checkbox">
            </label></td>
          </tr>

          <tr>
            <td>1</td>
            <td>0892067</td>
            <td>송태웅</td>
            <td>3번 - 세탁기 시스템</td>
             <td>   <label class="checkbox">
            <input type="checkbox">
            </label></td>
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
        <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
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

