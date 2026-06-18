package org.example.zadanie9;

import java.util.stream.IntStream;

public class Student implements Learner {

    @Override
    public void learn() {
        System.out.println("Я учусь. .zZ");
        IntStream.range(0, 1000000)
                .mapToDouble(Math::log)
                .sum();
        System.out.println("Я закончил учиться");
    }
}
