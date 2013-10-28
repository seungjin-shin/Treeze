package com.hansung.treeze.filter;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hansung.treeze.controller.ClassInfoController;
import com.hansung.treeze.model.User;
import com.hansung.treeze.service.UserService;

public class LoginFilter implements Filter {
	public static final String SIGNED_USER = "signedUser";
	
	public static final String CONTEXT_PATH = "/treeze";
	
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	private HashSet<String> ignoreUrlSet;
	
    public LoginFilter() { }
	public void destroy() { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		
		String requestUri = req.getRequestURI();
		
		logger.info("request uri - " + requestUri);
		
		// get Session
		HttpSession session = ((HttpServletRequest)request).getSession();
		// 현재 세션에 User 정보가 입력되어있는지 확인
		User signedUser = (User)session.getAttribute(SIGNED_USER);
		
		
		// filter ignore case, 로그인 한 경우
		if (null != signedUser || ignoreUrlSet.contains(requestUri)) {
			logger.info("로그인 했거나 로그인 체크를 필요로하지 않는 URL");
			
			chain.doFilter(request, response);
		}
		// 로그인 하지 않은 경우 로그인 페이지(signin.jsp)로 리다이렉트
		else {
			logger.info("로그인이 필요함");
			String contextPath = req.getContextPath();
			
			res.sendRedirect(String.format("%s/treeze/signin?before=%s", contextPath, req.getRequestURI()));
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		ignoreUrlSet = new HashSet<String>();
		
		ignoreUrlSet.add(CONTEXT_PATH + "/treezing/signin");
		ignoreUrlSet.add(CONTEXT_PATH + "/treezing/signout");
		ignoreUrlSet.add(CONTEXT_PATH + "/treezing");
		ignoreUrlSet.add(CONTEXT_PATH + "/treezing/");
	}
}
