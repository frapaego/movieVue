package com.frapaego.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "com.frapaego.movie" })
public class DataJpaConfig {

}
