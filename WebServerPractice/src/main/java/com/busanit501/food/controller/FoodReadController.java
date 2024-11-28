package com.busanit501.food.controller;

import com.busanit501.food.dto.FoodDTO;
import com.busanit501.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "FoodReadController",urlPatterns = "/food/read")
public class FoodReadController extends HttpServlet {

    private FoodService service = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodReadController doGet");

        FoodDTO foodDTO = null;
        try {
            foodDTO = service.getOne(Long.parseLong(request.getParameter("fno")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("dto", foodDTO);
        request.getRequestDispatcher("/WEB-INF/food/foodRead.jsp").forward(request, response);
    }
}
