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
import java.time.LocalDate;

@Log4j2
@WebServlet(name = "FoodRegController",urlPatterns = "/food/register")
public class FoodRegController extends HttpServlet {

    private FoodService service = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodRegController doGet");
        request.getRequestDispatcher("/WEB-INF/food/foodReg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("FoodRegController doPost");

        FoodDTO foodDTO = new FoodDTO(
                null
                , request.getParameter("title")
                , LocalDate.parse(request.getParameter("dueDate"))
                , false
        );
        try {
            service.register(foodDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/food/list");
    }
}
