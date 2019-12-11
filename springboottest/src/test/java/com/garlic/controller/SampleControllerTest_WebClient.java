package com.garlic.controller;

import com.garlic.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest_WebClient {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService mockSampleService; // Service는 Mock으로 만들어 순수하게 Controller만 테스트 하고 싶을 때

    @Test
    public void hello_mock() {
        when(mockSampleService.getName()).thenReturn("garlic_new");

        webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello garlic_new");
    }

}