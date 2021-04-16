package com.irostub.servletserver.web.frontcontroller.v4.controller;

import com.irostub.servletserver.domain.member.Member;
import com.irostub.servletserver.domain.member.MemberRepository;
import com.irostub.servletserver.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);

        return "save-result";
    }
}
