package com.irostub.servletserver.web.frontcontroller.v2;

import com.irostub.servletserver.web.frontcontroller.v1.ControllerV1;
import com.irostub.servletserver.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.irostub.servletserver.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.irostub.servletserver.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import com.irostub.servletserver.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.irostub.servletserver.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.irostub.servletserver.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerV2",urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {
    private Map<String, Object> controllerMap = new HashMap<>();

    public FrontControllerV2(Map<String, Object> map) {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();
        ControllerV2 controllerV2 = (ControllerV2) controllerMap.get(requestUrl);
        if (controllerV2 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controllerV2.process(req, resp);
        view.render(req, resp);
    }
}
