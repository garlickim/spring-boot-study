package com.garlic.controller;

import com.garlic.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest_RestTemplate {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    SampleService mockSampleService; // Service는 Mock으로 만들어 순수하게 Controller만 테스트 하고 싶을 때

    @Test
    public void hello() {
        String result = testRestTemplate.getForObject("/hello", String.class);

        assertThat(result).isEqualTo("hello garlic");
    }

    @Test
    public void hello_mock() {
        when(mockSampleService.getName()).thenReturn("garlic_new");
        String result = testRestTemplate.getForObject("/hello", String.class);

        assertThat(result).isEqualTo("hello garlic_new");
    }

}