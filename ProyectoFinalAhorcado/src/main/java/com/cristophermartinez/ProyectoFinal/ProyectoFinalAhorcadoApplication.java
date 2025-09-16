package com.cristophermartinez.ProyectoFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ProyectoFinalAhorcadoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoFinalAhorcadoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("API funcionando");
    }
}
