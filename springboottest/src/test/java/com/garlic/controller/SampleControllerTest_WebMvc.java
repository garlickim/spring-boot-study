package com.garlic.controller;

import com.garlic.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// WebMvcTest는 Class 하나만 테스트 할 때 사용
// Web과 관련된 것들만 빈으로 등록되고, 나머지(일반적인 component)는 빈으로 등록되지 않는다.
@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest_WebMvc {

    @MockBean
    SampleService mockSampleService;    // 따라서, Service는 빈으로 등록안되기 때문에 MockBean으로 등록한다.

    @Autowired
    MockMvc mockMvc;    // WebMvcTest를 사용하면 MockMvc를 사용하여 테스트 해야한다.

    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("garlic_new");
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello garlic_new"));
    }

}