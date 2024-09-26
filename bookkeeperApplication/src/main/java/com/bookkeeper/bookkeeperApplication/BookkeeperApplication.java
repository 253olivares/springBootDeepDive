package com.bookkeeper.bookkeeperApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class BookkeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookkeeperApplication.class, args);
	}

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}

}
