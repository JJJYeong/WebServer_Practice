package com.busanit501.controller;

import com.busanit501.dto.UserDTO;
import com.busanit501.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

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
                HttpSession session = request.getSession();
                session.setAttribute("loginInfo", user);
                response.sendRedirect("/user/list");
            } else {
                // 입력값 에러
                request.setAttribute("msg", "아이디 또는 비밀번호가 맞지 않습니다.");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
