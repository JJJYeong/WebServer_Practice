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
@WebServlet(name = "UserUpdController",urlPatterns = "/user/update")
public class UserUpdController extends HttpServlet {

    private UserService service = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UserUpdController doGet");

        UserDTO userDTO = null;
        try {
            userDTO = service.getOne(request.getParameter("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("dto", userDTO);
        request.getRequestDispatcher("/WEB-INF/userUpd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("UserUpdController doPost");

        UserDTO userDTO = new UserDTO(
                request.getParameter("id")
                , request.getParameter("pw")
                , request.getParameter("name")
        );
        try {
            service.update(userDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/user/list");
    }
}
