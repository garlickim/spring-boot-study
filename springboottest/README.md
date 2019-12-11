## Spring Boot 사용시, Test 관련 예제 <br>

*  spring-boot-starter-test 추가  
* @SpringBootTest  
: @RunWith(SpringRunner.class)와 함께 사용한다.  
: @SpringBootTest가 자동으로 @SpringBootApplication 어노테이션을 찾아 해당 패키지부터 모든 Bean을 스캔하여 등록한다.  

* webEnvironment  
: MOCK -----> mock servlet environment / 내장 톰캣을 구동하지 않음 / MockMvc를 사용하여 테스트  
: RANDON_PORT, DEFINED_PORT -----> 내장 톰캣 사용 / Test용 RestTemplate, WebClient를 사용하여 테스트  
: NONE -----> 서블릿 환경 제공 안함  

~~~java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // default가 SpringBootTest.WebEnvironment.MOCK
@AutoConfigureMockMvc
public class SampleControllerTest_MOCK {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello garlic"))
                .andDo(print());
    }
}
~~~

<br>

~~~java
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
~~~

<br>

~~~java
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
~~~

<br>

* @MockBean  
ApplicationContext에 들어있는 빈을 Mock으로 만든 객체로 교체.  
모든 @Test 마다 자동으로 리셋.  

<br>

* 슬라이스 테스트  
레이어 별로 잘라서 테스트하고 싶을 때 사용  
@JsonTest  
@WebMvcTest  
@WebFluxTest  
@DataJpaTest  


~~~java
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
~~~

