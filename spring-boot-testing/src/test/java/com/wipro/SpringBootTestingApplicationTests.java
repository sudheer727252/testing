package com.wipro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.wipro.controller.EmployeeController;

@SpringBootTest
class SpringBootTestingApplicationTests {

	 @Autowired
	  EmployeeController employeeController;

	  @Test
	  public void contextLoads() {
	    Assertions.assertThat(employeeController).isNotNull();
	  }
}
