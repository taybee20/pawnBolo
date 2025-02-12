package pawnbolo.com.pawnbolo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pawnbolo.com.pawnbolo.models.Bolo;
import pawnbolo.com.pawnbolo.repositories.BoloRepository;

@SpringBootApplication
public class PawnBoloApplication {

    public static void main(String[] args) {

        SpringApplication.run(PawnBoloApplication.class, args);
    }

//    @Bean
//    ApplicationRunner applicationRunner(BoloRepository boloRepository) {
//        return args -> {
//            boloRepository.save(new Bolo());
//        };
//    }

}
