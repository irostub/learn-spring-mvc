package com.irostub.servletserver.web.springmvc.v1;

import com.irostub.servletserver.domain.member.Member;
import com.irostub.servletserver.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringMemberListControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process(){
        List<Member> members = memberRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("members");
        modelAndView.addObject("members", members);
        return modelAndView;
    }
}
