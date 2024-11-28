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
@WebServlet(name = "FoodUpdController",urlPatterns = "/food/update")
public class FoodUpdController extends HttpServlet {

    private FoodService service = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodUpdController doGet");

        FoodDTO foodDTO = null;
        try {
            foodDTO = service.getOne(Long.parseLong(request.getParameter("fno")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("dto", foodDTO);
        request.getRequestDispatcher("/WEB-INF/food/foodUpd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("FoodUpdController doPost");

        FoodDTO foodDTO = new FoodDTO(
                Long.parseLong(request.getParameter("fno"))
                , request.getParameter("title")
                , LocalDate.parse(request.getParameter("dueDate"))
                , "on".equals(request.getParameter("finished"))
        );
        try {
            service.update(foodDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/food/list");
    }
}
