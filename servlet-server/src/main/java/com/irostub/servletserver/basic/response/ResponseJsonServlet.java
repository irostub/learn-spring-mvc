package com.irostub.servletserver.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irostub.servletserver.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResponseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("iro");
        helloData.setAge(20);

        //json 형식으로 변환, ObjectMapper 는 spring 사용시 자동의존하는 jackson 의 클래스다
        String result = objectMapper.writeValueAsString(helloData);
        resp.getWriter().write(result);
    }
}
