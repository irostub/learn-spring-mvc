package com.irostub.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @GetMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        resp.getWriter().write("ok");
    }

    @ResponseBody
    @GetMapping("/request-param-v2")
    public String requestParamV2(@RequestParam(value = "username") String username, @RequestParam(value = "age") int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //required = true 로 필수값이더라도 프론트에서 ?username= 으로 전송하면 빈문자열로 받아짐.
    //의도와 다르게 동작할 수 있으므로 주의. 추가로 원시타입에 필수값이 아니어서 빠질 경우 null 은 원시타입에 들어갈 수 없으므로
    //500에러가 터진다
    //필수값이 아예없는 경우 400오류
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //요청의 파라메터 키가 반드시 하나로 들어올 때
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, String> params)
    {
        params.forEach(log::debug);
        return "ok";
    }

    //요청의 파라메터 키가 여러개로 들어올 수 있을 때
    @ResponseBody
    @RequestMapping("/request-param-multi")
    public String requestParamMultiValue(@RequestParam MultiValueMap<String, String> params){
        params.forEach(log::debug);
        return "ok";
    }
}
