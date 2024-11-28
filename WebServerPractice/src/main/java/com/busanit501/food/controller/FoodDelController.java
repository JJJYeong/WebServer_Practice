package com.busanit501.food.controller;

import com.busanit501.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "FoodDelController",urlPatterns = "/food/delete")
public class FoodDelController extends HttpServlet {

    private FoodService service = FoodService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("FoodDelController doGet");

        try {
            service.delete(Long.parseLong(request.getParameter("fno")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/food/list");
    }
}
