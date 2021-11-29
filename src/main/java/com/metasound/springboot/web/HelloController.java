package com.metasound.springboot.web;

import com.metasound.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//컨트롤러를 JSON을 반환하는 컨트롤러로.
//각 메소드마다 선언하던 ResponseBody를 한번에 사용하는 기능.
@RestController
public class HelloController {

    @GetMapping("/hello") //HTTP Method인 Get의 요청을 받을 수 있는 API 생성.
    public String hello(){
        return "Hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }

}
