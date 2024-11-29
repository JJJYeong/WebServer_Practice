package com.busanit501.food.controller;

import com.busanit501.food.dto.FoodDTO;
import com.busanit501.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
            Long fno = Long.parseLong(request.getParameter("fno"));
            foodDTO = service.getOne(fno);
            request.setAttribute("dto", foodDTO);

            Cookie findCookie = findCookie(request.getCookies(), "viewFoods");
            // 쿠키의 값을 조회하기.
            String cookieValue = findCookie.getValue();
            log.info("cookieValue : " + cookieValue);
            // 상태 변수,
            // 조회한 게시글 번호 ->예시) cookieValue =  "1-3-5-7-"
            boolean exists = false;
            // 기본 유효성 체크, 쿠키의 존재 및, 내용의 존재여부 확인
            if(cookieValue != null && cookieValue.indexOf(fno+"-") >=0) {
                log.info("cookieValue.indexOf(fno+\"-\") " + cookieValue.indexOf(fno+"-"));
                exists = true;
            }
            // 만약에, 쿠키의 내용이 없다면, 내용을 추가하기.
            if(!exists) {
                cookieValue += fno+"-";
                findCookie.setValue(cookieValue);
                findCookie.setMaxAge(60*60*24);
                findCookie.setPath("/");
                // 무조건 서버에서 응답 해주기.
                response.addCookie(findCookie);
            }

            request.getRequestDispatcher("/WEB-INF/food/foodRead.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // findCookie , 메서드 추가.
    // 역할, 1) 찾는 쿠키 이름의 쿠키를 반환
    // 2) 쿠키가 없다면 쿠키를 생성.(쿠키 이름: viewFoods)
    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        // 쿠키가 있는 경우
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // cookie.getName(): 전체 쿠키 목록 요소의 이름
                // name : 찾고자하는 쿠키 이름.
                if (cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                }
            }
        }

        // 2, 쿠키가 없다면, -> 생성하기.
        if (findCookie == null) {
            findCookie = new Cookie("viewFoods", "");
            findCookie.setPath("/");
            findCookie.setMaxAge(60*60*24);
        }

        return findCookie;
    }
}
