package org.example.zadanie10;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

        public static long getArithmeticProgressionSum(int a, int b) {
            return IntStream.range(a, b)
                    .asLongStream()
                    .sum();
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(getArithmeticProgressionSum(a,b));
            System.out.println(getArithmeticProgressionSum(10_000_000, 1_000_000_000));
        }
    }