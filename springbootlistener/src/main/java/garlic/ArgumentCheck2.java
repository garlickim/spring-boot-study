package garlic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ArgumentCheck2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(args)
                .forEach(System.out::println);
    }
}
