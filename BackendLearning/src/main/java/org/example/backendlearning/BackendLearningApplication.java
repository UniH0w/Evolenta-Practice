package org.example.backendlearning;

import org.example.backendlearning.Сalculator.Calculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class BackendLearningApplication {
//    private final Calculator calculator;
//
//    public BackendLearningApplication(@Qualifier("calculator") Calculator calculator) {
//        this.calculator = calculator;
//    }
    public static void main(String[] args) {
        SpringApplication.run(BackendLearningApplication.class, args);
    }
//    @Bean
//    public boolean outToConsole() throws Exception {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Введите число a");
//        double a = sc.nextDouble();
//
//        System.out.println("Введите число b");
//        double b = sc.nextDouble();
//
//        System.out.println("Введите тип операции: " + calculator.getSupportedOperations());
//        String operationType = sc.next();
//
//        calculator.calc(operationType, a, b);
//
//        return true;
//    }

}
