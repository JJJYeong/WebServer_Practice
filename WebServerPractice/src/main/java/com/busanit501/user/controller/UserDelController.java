package com.busanit501.user.controller;

import com.busanit501.user.service.UserService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "UserDelController",urlPatterns = "/user/delete")
public class UserDelController extends HttpServlet {

    private UserService service = UserService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("UserDelController doPost");

        try {
            service.delete(request.getParameter("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/user/list");
    }
}
