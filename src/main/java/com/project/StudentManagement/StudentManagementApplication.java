package com.project.StudentManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Student Course Management System",
				version = "1.0.0",
				description = "This project is used to maintain Student and Course Management",
				termsOfService = "Copyright@2023",
				contact = @Contact(
						name = "Vaibhav",
						email = "vaibhav@gmail.com"
				),
				license = @License(
						name = "license",
						url = "something.google.com"
				)
		)
)
public class StudentManagementApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
