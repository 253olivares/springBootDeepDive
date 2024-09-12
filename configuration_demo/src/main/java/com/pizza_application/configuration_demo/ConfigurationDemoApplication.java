package com.pizza_application.configuration_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pizza_application.configuration_demo.config.PizzaConfig;

@SpringBootApplication
public class ConfigurationDemoApplication implements CommandLineRunner {

	private PizzaConfig pizzaConfig;

	public ConfigurationDemoApplication(PizzaConfig pizzaConfig) {
		this.pizzaConfig = pizzaConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationDemoApplication.class, args);
	}

	@Override
	public void run(final String... args) {

		System.out.println(String.format("I want a %s curst pizza, with %s and %s sauce",
				pizzaConfig.getCrust(),
				pizzaConfig.getTopping(),
				pizzaConfig.getSauce()));
	}

}
