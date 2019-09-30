package com.kairos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.kairos.conf.SwaggerConfiguration;


@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class PruebakairosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebakairosApplication.class, args);
	}
	

	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	         registry.addResourceHandler("swagger-ui.html")
	                  .addResourceLocations("classpath:/META-INF/resources/");
	  }
}
