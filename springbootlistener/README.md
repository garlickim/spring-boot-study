## Spring Boot Listener <br>

* ApplicationContext를 만들기 전에 사용하는 리스너는 @Bean으로 등록할 수 없다.  
  따라서, SpringApplication에 직접 등록한다.

~~~java
    SpringApplication application = new SpringApplication(Application.class);
    application.addListeners(new SampleListener());
    application.run(args);
~~~

~~~java
    public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
        @Override
        public void onApplicationEvent(ApplicationStartingEvent event) {
            System.out.println("=======================");
            System.out.println("Application is starting");
            System.out.println("=======================");
        }
    }
~~~

<br><br>

* 애플리케이션 실행한 뒤 뭔가 실행하고 싶을 때 ApplicationRunner(추천) 또는 CommandLineRunner  
 --> Application Arguments만 받는다. JVM 옵션은 받지 않는다.  
* 순서 지정 가능 @Order

<br>