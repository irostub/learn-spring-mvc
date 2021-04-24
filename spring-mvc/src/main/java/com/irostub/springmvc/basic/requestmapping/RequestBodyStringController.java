package com.irostub.springmvc.basic.requestmapping;

import com.irostub.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @RequestMapping("request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.debug("messageBody = {}", messageBody);
        resp.getWriter().write("ok");
    }

    @RequestMapping("request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.debug("messageBody = {}", messageBody);
        writer.write("ok");
    }

    @RequestMapping("request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> messageBody) {
        log.debug("messageBody = {}", messageBody);
        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @RequestMapping("request-body-string-v4")
    public String requestBodyStringV4(@RequestBody HelloData helloData) {
        log.debug("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
