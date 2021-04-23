package com.irostub.springmvc.basic.requestmapping;

import com.irostub.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic() {
        log.debug("hello-basic");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.debug("mapping-get-v2");
        return "ok";
    }

    //@PathVariable("userId")String data 식으로도 사용가능하다. 변수명을 일치시키느냐 애노테이션에 메타정보를 주느냐 차이
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.debug("mappingPath userId = {}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId) {
        log.debug("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.debug("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.debug("mappingHeader");
        return "ok";
    }

    //서버쪽이 클라이언트 쪽에서 준 데이터를 소비하는 쪽일 때
    //@PostMapping(value = "/mapping-consume", consumes = "application/json")
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.debug("mappingConsumes");
        return "ok";
    }

    //클라이언트 쪽이 서버 쪽에서 준 데이터를 소비하는 쪽일 때
    //@PostMapping(value = "/mapping-produce", produces = "text/html")
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduce() {
        log.debug("mappingProduce");
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.debug("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //만약 형식에 맞지 않는 데이터가 들어올 경우 binding exception 발생
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.debug("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
