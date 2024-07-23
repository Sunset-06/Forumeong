package com.example.forumeong;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Controller
	public class SimpleController {
    	@Value("${spring.application.name}")
    	String appName;

    	@GetMapping("/")
    	public String homePage(Model model) {
        	model.addAttribute("appName", appName);
        	return "home";
    	}

		@GetMapping("/signin")
    	public String signinPage(Model model) {
        	model.addAttribute("appName", appName);
        	return "signIn";
    	}
	}

}
