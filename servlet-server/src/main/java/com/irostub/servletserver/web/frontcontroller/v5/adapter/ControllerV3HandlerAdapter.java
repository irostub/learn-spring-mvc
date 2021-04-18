package com.irostub.servletserver.web.frontcontroller.v5.adapter;

import com.irostub.servletserver.web.frontcontroller.ModelView;
import com.irostub.servletserver.web.frontcontroller.MyView;
import com.irostub.servletserver.web.frontcontroller.v3.ControllerV3;
import com.irostub.servletserver.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler){
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(req);

        return controller.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();

        req.getParameterNames().asIterator()
                .forEachRemaining(name-> paramMap.put(name, req.getParameter(name)));
        return paramMap;
    }
}
