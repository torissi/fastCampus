package com.example.restaurant.interfaces;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//웹에 대한 요청 test 만들기
@ExtendWith(SpringExtension.class) //스프링을 통해 이 테스트를 실행할거다
@WebMvcTest(RestaurantController.class) //RestaurantController를 테스트 할거다
class RestaurantControllerTest {

    @Autowired //스프링을 통해 주입해주자
    private MockMvc mvc;

    @Test
    public void list() throws Exception { //perform에서는 예외가 나올 수 있기 때문에 예외처리 해줌
        mvc.perform(get("/restaurant"))
            .andExpect(status().isOk()) //통신 상태가 200인가
            .andExpect(content().string(
                    containsString("\"id\":1004")
            ))
            .andExpect(content().string(
                    containsString("\"name\":\"Bab zip\"")
            )); //가게 이름이 나오는가

    }

    @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurant/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bab zip\"")
                ));
        mvc.perform(get("/restaurant/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\"")
                ));
    }
}