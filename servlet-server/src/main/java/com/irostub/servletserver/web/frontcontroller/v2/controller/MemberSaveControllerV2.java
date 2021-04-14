package com.irostub.servletserver.web.frontcontroller.v2.controller;

import com.irostub.servletserver.domain.member.Member;
import com.irostub.servletserver.domain.member.MemberRepository;
import com.irostub.servletserver.web.frontcontroller.v2.ControllerV2;
import com.irostub.servletserver.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member m = new Member(username, age);
        memberRepository.save(m);

        req.setAttribute("member", m);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
