package com.garlic;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }





    @GetMapping("/hello")
    public String hello()
    {
        return "Hello Spring!!";
    }





    // embedded tomcat 사용시, http와 https 둘다 사용하기 위해
    // https 설정은 properties에
    // http 설정은 아래 ServletWebServerFactory에 설정하여 Bean으로 등록한다.
    @Bean
    public ServletWebServerFactory servletContainer()
    {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }





    private Connector createStandardConnector()
    {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return connector;
    }

}
