package com.busanit501.food.controller;

import com.busanit501.food.dto.FoodDTO;
import com.busanit501.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
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
        // TestListener , 에 등록된 특정 변수에 접근
        // 그러면 전역/공유 자원 처럼 사용이 되어서
        // 어느 파일에서도 접근이 가능함
        ServletContext context = request.getServletContext();
        String result = (String) context.getAttribute("appTestName");
        log.info("FoodListController ServletContext : 값 조회 확인중 : "+result);

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
