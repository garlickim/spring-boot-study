## Spring Boot로 Embedded Tomcat 사용 시, Http/Https 설정 예시 프로젝트 <br>

* https 설정은 프로젝트 내의 application.properties와 같이 설정

~~~properties
server.ssl.key-store=keystore.p12
server.ssl.key-store-password=123456
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat
server.port=8443
~~~

<br>

* http 설정은 Java 에서 처리 (ServletWebServerFactory에 설정하여 Bean으로 등록)

~~~java
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
~~~
<br>
  

## HTTP2를 사용하는 방법

~~~properties
server.http2.enable=true
~~~

* HTTP2는 SSL이 적용된 상태에서만 가능하다.
* Undertow 사용시 별다른 설정은 필요가 없다.  
* Tomcat 8.5.x 버전 사용시 별도의 설정이 필요하므로, Tomcat 9.x 과 JDK 9를 사용하면 별도의 설정이 필요없다.  
