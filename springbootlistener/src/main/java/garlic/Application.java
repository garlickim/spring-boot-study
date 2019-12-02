package garlic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        // ApplicationContext를 만들기 전에 사용하는 리스너는 @Bean으로 등록할 수 없다.
        // 따라서 그러한 리스너들은 아래와 같이 직접 등록해준다.
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new SampleListener());
        application.run(args);
    }
}
