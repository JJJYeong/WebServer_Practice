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
import java.util.List;

@Log4j2
@WebServlet(name = "UserListController",urlPatterns = "/user/list")
public class UserListController extends HttpServlet {

    private UserService service = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodListController doGet");

        List<UserDTO> userList = null;
        try {
            userList = service.listAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("list", userList);
        request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(request, response);
    }
}
