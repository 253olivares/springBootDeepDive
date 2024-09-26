package com.bookkeeper.bookkeeperApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// this starts up our spring boot applications. The spring boot app annotation triggers auto-configuration and component scanning
// to search for beans that will be auto=loads into classes that require bean functionality. 
@SpringBootApplication
// used to specify this class works as a controller for our application. This
// marks our class as a web request handler.
// With controller spring knows to look for rest api request handlers within our
// class.
@Controller
public class BookkeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookkeeperApplication.class, args);
	}

	// we call request mapping to begin maping url requests to our application
	// request mapping is a general use annotation just telling spring that this
	// endpoint exist
	// Spring doesnt know if it is a get post patch or delete request yet and have
	// specific annotations for theses.
	@RequestMapping("/")
	// Response bodt automatically serializes our data into JSON. So Hello World
	// string is passed back as a json object and printing to our page.
	public @ResponseBody String greeting() {
		return "Hello, World";
	}

}
