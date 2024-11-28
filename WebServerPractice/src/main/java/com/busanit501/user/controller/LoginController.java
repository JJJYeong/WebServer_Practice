package com.busanit501.user.controller;

import com.busanit501.user.dto.UserDTO;
import com.busanit501.user.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private UserService service = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("LoginController doGet");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("LoginController doPost");

        UserDTO userDTO = UserDTO.builder()
                .id(request.getParameter("id"))
                .pw(request.getParameter("pw"))
                .build();
        try {
            UserDTO user = service.login(userDTO);

            if(user.getId() != null) {
                // 로그인 성공
                boolean rememberMe = "on".equals(request.getParameter("auto"));
                if(rememberMe) {
                    // 자동로그인 체크 시 uuid 및 쿠키 세팅
                    String uuid = UUID.randomUUID().toString();
                    service.updateUUID(request.getParameter("id"), uuid);
                    user.setUuid(uuid);

                    Cookie rememberCookie = new Cookie("rememberMe", uuid);
                    rememberCookie.setPath("/");
                    rememberCookie.setMaxAge(60*60*24*7);
                    response.addCookie(rememberCookie);
                }
                // 세션 세팅
                HttpSession session = request.getSession();
                session.setAttribute("loginInfo", user);
                response.sendRedirect("/main");

            } else {
                // 입력값 에러
                request.setAttribute("msg", "아이디 또는 비밀번호가 맞지 않습니다.");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
