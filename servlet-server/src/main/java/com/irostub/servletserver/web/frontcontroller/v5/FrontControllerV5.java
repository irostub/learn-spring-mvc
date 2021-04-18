package com.irostub.servletserver.web.frontcontroller.v5;

import com.irostub.servletserver.web.frontcontroller.ModelView;
import com.irostub.servletserver.web.frontcontroller.MyView;
import com.irostub.servletserver.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.irostub.servletserver.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.irostub.servletserver.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.irostub.servletserver.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.irostub.servletserver.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.irostub.servletserver.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.irostub.servletserver.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.irostub.servletserver.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {
    private Map<String, Object> handlerMappingMap = new HashMap<>();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);

        MyHandlerAdapter adapter = getAdapter(handler);
        ModelView modelView = adapter.handle(req, resp, handler);

        MyView myView = viewResolver(modelView.getViewName());
        myView.render(modelView.getModel() ,req, resp);
    }

    private MyHandlerAdapter getAdapter(Object handler) {
        for (MyHandlerAdapter attr : handlerAdapters) {
            if(attr.supports(handler)){
                return attr;
            }
        }
        throw new IllegalArgumentException(handler + "를 찾을 수 없습니다");
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
