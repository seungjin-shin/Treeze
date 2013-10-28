<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List" pageEncoding="UTF-8" %>
    <%@ page import="com.hansung.treeze.model.Ticket" %>
        <% List<Ticket>tickets = (List<Ticket>)request.getAttribute("tickets"); 
        int i=0 ; %>
                <div class='row-fluid'>
                    <div class='span6'>
                        <img src='${pageContext.request.contextPath}/resources/_include/img/test/slide1.png' width='100%' height='100%'>
                    </div>
                    <div class='span6' style='height: 100%;'>
                        <h1>Question</h1> 
                        <button type='button' class='btn btn-warning pull-right' style='margin-right:30px'>질문 작성</button>
                        <table class='table table-hover'>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Lecture Name</th>
                                    <th>Create Date</th>
                                    <th>Personnel</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Ticket ticket : tickets) { %>
                                    <td>
                                        <%=i %>
                                    </td>
                                    <td>
                                        <%=ticket.getContents()%>
                                    </td>
                                    <td>학생</td>
                                    <%}%>
                                    </tbody>
                        </table>
                        <div class='pagination pagination-centered'>
                            <ul>
                                <li class='disabled'><a href='#'>«</a> 
                                </li>
                                <li class='active'><a href='#'>1</a> 
                                </li>
                                <li><a href='#'>2</a> 
                                </li>
                                <li><a href='#'>3</a> 
                                </li>
                                <li><a href='#'>4</a> 
                                </li>
                                <li><a href='#'>5</a> 
                                </li>
                                <li><a href='#'>»</a> 
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class='span12' style='height: 100%;'>
                        <iframe src='http://app.wisemapping.com/c/maps/3/try' width='100%' height='100%'></iframe>
                    </div>