package garlic;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// 애플리케이션 실행한 뒤 뭔가 실행하고 싶을 때 ApplicationRunner(추천) 또는 CommandLineRunner
// Application Arguments만 받는다.
@Component
public class ArgumentCheck implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("foo : " + args.containsOption("foo"));
        System.out.println("bar : " + args.containsOption("bar"));
    }
}
