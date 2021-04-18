package com.irostub.servletserver.web.frontcontroller.v3.controller;

import com.irostub.servletserver.web.frontcontroller.v3.ControllerV3;
import com.irostub.servletserver.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
