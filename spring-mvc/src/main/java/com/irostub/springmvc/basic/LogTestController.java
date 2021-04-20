package com.irostub.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";

        /*
        로거는 이와 같이 출력될 레벨을 정해서 출력할 수 있다.
        반드시 ("xxx log={}",param) 와 같은 형태로 사용한다.
        ("xxx" + param) 과 같이 쓸 수는 있으나 자바 코드 동작과정에 의해 메서드 실행전에 이미 + 연산이 일어나고 심지어 그 데이터를 가지고 있는다.
        메서드에 파라메터를 넘겨서 출력할지 안할지 결정하는 것과 메서드 실행전에 이미 연산을 다해놓고 메모리까지 먹고 있는 것과는 그 차이가 매우 크다.
        또한 system.out.println 은 더이상 쓰지 않는다. 출력 레벨 설정도 불가능할 뿐더러(부가기능도 사용못함) 성능도 로거보다 좋지 않다.

        로그 레벨 설정은 application.properties 를 참고하고 상위 레벨 설정이 베이스가 되는 식으로 작동한다.
         */

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
