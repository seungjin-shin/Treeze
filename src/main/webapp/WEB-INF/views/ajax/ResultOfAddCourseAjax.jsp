<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List" pageEncoding="UTF-8"%>
<%@ page import="com.hansung.treeze.model.Course"%>



<div id="lecture_dialog" class="modal" style="visibility: visible">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3>수강신청 결과 </h3>
	</div>


	<div class="modal-body">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>Lecture Name</th>
					<th>수강신청여부</th>
					<th></th>

				</tr>
			</thead>
			<tbody id="lecture_tbody">
				<% List<Course> alreadyCourses = (List<Course>)request.getAttribute("alreadyCourses"); 
        List<Course> successCourses = (List<Course>)request.getAttribute("successCourses");
        int i=0 ; 
       for(Course course : alreadyCourses) { %>
				<tr>
					<td><%= i++ %></td>
					<td><%= course.getLectureName() %></td>

					<td>이미존재</td>
					<td>
						<div class="checkbox">
							<label> <input type="checkbox">
							</label>
						</div>
					</td>
				</tr>
				<% } %>
				<% for(Course course : successCourses) { %>
				<tr>
					<td><%= i++ %></td>
					<td><%= course.getLectureName() %></td>

					<td>성공</td>
					<td>
						<div class="checkbox">
							<label> <input type="checkbox">
							</label>
						</div>
					</td>
				</tr>
				<% } 
		%>

			</tbody>
		</table>


		<div class="modal-footer">

			<a id="course_add_confirm_btn" class="btn btn-success">확인</a>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/_include/js/jquery-1.9.1.min.js"></script>

<script type="text/javascript">

	window.onload = function(){ 
	$("#course_add_confirm_btn").click(function() {
					$("#lecture_dialog").attr({"style":"visibility:hidden"});				

				});
	}
</script>


