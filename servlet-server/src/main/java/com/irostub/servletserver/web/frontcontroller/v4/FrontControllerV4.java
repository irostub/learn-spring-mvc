package com.irostub.servletserver.web.frontcontroller.v4;

import com.irostub.servletserver.web.frontcontroller.MyView;
import com.irostub.servletserver.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.irostub.servletserver.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.irostub.servletserver.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();

        ControllerV4 controllerV4 = controllerMap.get(requestUrl);

        if (controllerV4 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = new HashMap<>();
        Map<String, Object> model = new HashMap<>();

        req.getParameterNames().asIterator()
                .forEachRemaining(name -> paramMap.put(name, req.getParameter(name)));


        String viewName = controllerV4.process(paramMap, model);
        MyView view = viewResolver(viewName);
        view.render(model, req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
