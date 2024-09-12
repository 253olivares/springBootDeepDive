package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // Before dependency injection
        Car car = new Car();
        // If we wanted to change our class for a new feature we would have to
        // Bike car = new Bike();

        // Using injection we dont have too.
        Vehicle obj = new Car();

        // If we use spring framework we dont have to do either
        // calling Application context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        Vehicle obj2 = (Vehicle) context.getBean("vehicle");

        obj2.drive();

    }
}
