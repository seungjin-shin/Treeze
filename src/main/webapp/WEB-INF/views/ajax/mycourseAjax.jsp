<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List" pageEncoding="UTF-8" %>
    <%@ page import="com.hansung.treeze.model.Course" %>
        <% List<Course> courses = (List<Course>)request.getAttribute("courses"); 
        int i=0 ; 
       for(Course course : courses) { %>
            <tr >
				<td><%= i++ %></td>
				<td><%= course.getLectureName() %></td>

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


