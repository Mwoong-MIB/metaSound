package com.metasound.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트를 진행할 때 JUnit에 내장된 실행자 SpringExtension 실행자 실행.
//스프링 부트 테스트와 JUnit사이의 연결자 역할.
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //스프링이 관리하는 bean을 주입.
    @Autowired
    //웹 API를 테스트할 때 사용.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "Hello";

        //MockMvc 통해 /hello 주소로 HTTP GET 요청.
        mvc.perform(get("/hello"))
                //mvc.perform 결과를 검증. (200인지 아닌지)
                .andExpect(status().isOk())
                //mvc.perform 결과를 검증. (controller에서 hello를 리턴, 이 값이 맞는지)
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        //given
        String name = "test";
        int amount = 1000;

        //when
        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$.amount", is(amount)));

    }
}
