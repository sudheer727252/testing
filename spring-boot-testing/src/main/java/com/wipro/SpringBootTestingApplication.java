package com.wipro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    Verifying that the application has been initialized successfully
	Unit testing REST Controller with @WebMvcTest
	Unit testing Service Layer with Mockito
	Unit testing DAO Layer with @DataJpaTest and @AutoConfigureTestDatabase
	Integration testing using @SpringBootTest
	System testing using RestTemplate
 */

@SpringBootApplication
public class SpringBootTestingApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestingApplication.class, args);
	}

	

}
