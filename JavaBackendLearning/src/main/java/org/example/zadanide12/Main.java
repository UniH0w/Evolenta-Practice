package org.example.zadanie12;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число a");
        double a = sc.nextDouble();

        System.out.println("Введите число b");
        double b = sc.nextDouble();
        double sum = a + b;
        double diff = a - b;

        Multiplier multiplier = new Multiplier();
        Divider divider = new Divider();
        double mul = multiplier.multiply(a, b);
        double div = divider.divide(a, b);

        System.out.println("Результат сложения a и b: " + sum);
        System.out.println("Результат вычитания a и b: " + diff);
        System.out.println("Результат умножения a и b: " + mul);
        System.out.println("Результат деления a и b: " + div);
    }
}
