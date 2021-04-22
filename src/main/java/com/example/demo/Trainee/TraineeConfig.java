package com.example.demo.Trainee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class TraineeConfig {

    @Bean
    CommandLineRunner commandLineRunner(TraineeRepository repository) {
        return args -> {
            Trainee joe = new Trainee(
                    "Joe Doe",
                    LocalDate.of(2000, Month.NOVEMBER, 1),
                    "joe.doe@gmail.com"
            );

            Trainee jane = new Trainee(
                    "Jane Doe",
                    LocalDate.of(2002, Month.JUNE, 1),
                    "jane.doe@gmail.com"
            );

            repository.saveAll(
                    List.of(joe, jane)
            );
        };
    }
}
