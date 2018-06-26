package com.frapaego.movie.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// Loads the spring beans required by the framework
@Profile(value = { "dev", "staging" })
public class SwaggerConfig {

	@Bean
	public Docket movieApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(moviePaths()).build();
	}

	private Predicate<String> moviePaths() {
		return regex("/api/movies.*");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SpringMVC API").description("SpringMVC API reference for developers")
				.contact(new Contact("Francisco J. Páez", "", "frapaego@gmail.com"))
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
	}

}
