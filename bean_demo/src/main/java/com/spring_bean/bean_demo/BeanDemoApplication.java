package com.spring_bean.bean_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring_bean.bean_demo.services.ColorPrinter;

@SpringBootApplication
// This is where our bean search begins
// Inside springbootapplication it has a component scan and looks for beans
public class BeanDemoApplication implements CommandLineRunner {

	private ColorPrinter colorPrinter;

	public BeanDemoApplication(ColorPrinter colorPrinter) {
		this.colorPrinter = colorPrinter;
	}

	public static void main(String[] args) {
		SpringApplication.run(BeanDemoApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		System.out.println(colorPrinter.print());
	}

}
