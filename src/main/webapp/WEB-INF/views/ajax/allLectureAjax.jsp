<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List" pageEncoding="UTF-8" %>
    <%@ page import="com.hansung.treeze.model.Lecture" %>
        <% List<Lecture> lectures = (List<Lecture>)request.getAttribute("lectures"); 
        int i=0 ; 
       for(Lecture lecture : lectures) { %>
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
    	<% } %> 


