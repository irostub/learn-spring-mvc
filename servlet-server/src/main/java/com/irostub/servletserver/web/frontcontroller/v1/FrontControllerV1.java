package com.irostub.servletserver.web.frontcontroller.v1;

import com.irostub.servletserver.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.irostub.servletserver.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.irostub.servletserver.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {
    private Map<String, Object> controllerMap = new HashMap<>();

    public FrontControllerV1(Map<String, Object> map) {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();
        ControllerV1 controllerV1 = (ControllerV1) controllerMap.get(requestUrl);
        if (controllerV1 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controllerV1.process(req, resp);
    }
}
