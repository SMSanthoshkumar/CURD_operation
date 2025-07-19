package com.example.amigoscode.students;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student santhosh = new Student(

                    "Santhosh",
                    "smsanthoshkumar@gmail.com",
                    LocalDate.of(2007, Month.DECEMBER,07)

            );
            Student mounesh = new Student(

                    "Mounesh",
                    "kmouneshkumarn@gmail.com",
                    LocalDate.of(2002, Month.JULY,01)

            );
            repository.saveAll(
                    List.of(santhosh,mounesh)
            );
        };
    }


}
