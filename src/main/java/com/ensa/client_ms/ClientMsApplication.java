package com.ensa.client_ms;

import com.ensa.client_ms.dao.ClientRepository;
import com.ensa.client_ms.entities.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ClientMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientMsApplication.class, args);
    }

    //tester et ajouter qlq data
    //test
    @Bean
    CommandLineRunner start(ClientRepository clientRepository ){
        return args -> {
            clientRepository.deleteAll();

            clientRepository.save(new Client(null,"mounir","el bakkari","06256225","mr@gmail.com"));
            clientRepository.save(new Client(null,"lionel","messi","06256225","messi@gmail.com"));
            clientRepository.save(new Client(null,"crestiano","ronaldo","06256225","ronaldo@gmail.com"));
        };
    }
}
