package modern.challenge;

import modern.challenge.repository.NumberRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private static final ExecutorService executor = Executors.newFixedThreadPool(25);

    private final NumberRepository numberRepository;

    public MainApplication(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            while (true) {
                executor.execute(numberRepository);

                Thread.sleep((int) (Math.random() * 250));
            }

        };
    }
}
