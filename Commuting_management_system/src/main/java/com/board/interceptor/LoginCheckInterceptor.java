package com.board.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.board.domain.UserDTO;


public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 세션에서 회원 정보 조회
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("loggedInUser");

        // 2. 회원 정보 체크
        if (user == null) {
            response.sendRedirect("/user/login");
            return false;
        }
        if (user.getRole()==null) {
        	response.sendRedirect("/main");
        	return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}