package com.irostub.servletserver.web.frontcontroller.v2.controller;

import com.irostub.servletserver.web.frontcontroller.v2.ControllerV2;
import com.irostub.servletserver.web.frontcontroller.v2.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
