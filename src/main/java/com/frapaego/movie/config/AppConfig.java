package com.frapaego.movie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.frapaego.movie.Constants;

@Configuration
@ComponentScan(basePackageClasses = { Constants.class }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = { RestController.class, ControllerAdvice.class,
				Configuration.class }) })
@PropertySource("classpath:/app.properties")
@PropertySource(value = "classpath:/database.properties", ignoreResourceNotFound = true)
public class AppConfig {

}
