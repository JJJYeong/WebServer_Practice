package com.busanit501.filter;

import com.busanit501.user.dto.UserDTO;
import com.busanit501.user.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebFilter(urlPatterns = {"/main/*", "/user/*", "/food/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter, 로그인 체크");
        // 세션 정보를 호출 및 가져오기
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // 세션 정보 확인 후, 자동 로그인 쿠키 확인
        if (session.isNew() || session.getAttribute("loginInfo") == null) {
            log.info("세션이 새로 시작되었거나 로그인 정보가 없는 경우");

            Cookie findCookie = findCookie(request.getCookies(), "rememberMe");
            if (findCookie == null) {
                // 쿠키도 없으면 로그인 페이지로
                log.info("자동로그인 쿠키가 없는 경우");
                response.sendRedirect("/login");
                return;
            }

            try {
                // 쿠키 있는 경우 사용자 정보 가져오고 해당 url로
                UserDTO userDTO = UserService.INSTANCE.getOneByUUID(findCookie.getValue());
                if (userDTO != null) {
                    session.setAttribute("loginInfo", userDTO);
                } else {
                    throw new RuntimeException("유효하지 않은 자동로그인 쿠키");
                }
            } catch (SQLException e) {
                throw new RuntimeException("데이터베이스 오류", e);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        // 쿠키가 있는 경우
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // cookie.getName(): 전체 쿠키 목록 요소의 이름
                if (cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                }
            }
        }
        return findCookie;
    }

}
