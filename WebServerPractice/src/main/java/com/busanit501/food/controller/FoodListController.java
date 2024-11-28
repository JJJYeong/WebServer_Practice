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
import java.util.List;

@Log4j2
@WebServlet(name = "FoodListController",urlPatterns = "/food/list")
public class FoodListController extends HttpServlet {

    private FoodService service = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodListController doGet");

        List<FoodDTO> foodList = null;
        try {
            foodList = service.listAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("list", foodList);
        request.getRequestDispatcher("/WEB-INF/food/foodList.jsp").forward(request, response);
    }
}
