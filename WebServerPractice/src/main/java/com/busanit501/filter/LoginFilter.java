package com.busanit501.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/main/*", "/user/*", "/food/*"})
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter, 로그인 체크");
        // 세션 정보를 호출 및 가져오기.
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // 서버에 최초로 접근했다면, 서버에서 JSESSIONID 발급을 해준다.
        if(session.isNew()) {
            log.info("최초로 서버에 요청");
            response.sendRedirect("/login");
            return;
        }
        // 2번째 이후의 방문, 세션이라는 저장공간에 키 : loginInfo , 값: 로그인한 유저를 기록.
        if(session.getAttribute("loginInfo") == null) {
            log.info("2번째 이후로 서버에 요청을 했지만, 로그인 정보는 없는 경우");
            response.sendRedirect("/login");
            return;
        }
        // 쿠키 체크
//        if(cookie == null) {
//            response.sendRedirect("/login");
//            return;
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
