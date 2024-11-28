package com.busanit501.user.controller;

import com.busanit501.user.dto.UserDTO;
import com.busanit501.user.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "UserInfoController",urlPatterns = "/user/info")
public class UserInfoController extends HttpServlet {

    private UserService service = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UserInfoController doGet");

        UserDTO userDTO = null;
        try {
            userDTO = service.getOne(request.getParameter("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("dto", userDTO);
        request.getRequestDispatcher("/WEB-INF/user/userInfo.jsp").forward(request, response);
    }
}
