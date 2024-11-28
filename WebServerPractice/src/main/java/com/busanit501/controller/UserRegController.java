package com.busanit501.controller;

import com.busanit501.dto.UserDTO;
import com.busanit501.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "UserRegController",urlPatterns = "/register")
public class UserRegController extends HttpServlet {

    private UserService service = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UserRegController doGet");
        request.getRequestDispatcher("/WEB-INF/userReg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UserRegController doPost");

        UserDTO userDTO = new UserDTO(
                request.getParameter("id")
                , request.getParameter("pw")
                , request.getParameter("name")
        );
        try {
            if(service.getOne(userDTO.getId()).getId() == null) {
                service.register(userDTO);
                response.sendRedirect("/login");
            } else {
                // 이미 존재하는 아이디
                request.setAttribute("msg", "이미 존재하는 아이디입니다.");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
