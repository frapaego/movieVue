package com.frapaego.movie.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class DataSourceConfig {

	private static final String ENV_JDBC_PASSWORD = "jdbc.password";
	private static final String ENV_JDBC_USERNAME = "jdbc.username";
	private static final String ENV_JDBC_URL = "jdbc.url";

	@Inject
	private Environment env;

	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	@Profile("staging")
	public DataSource testDataSource() {
		final BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl(env.getProperty(ENV_JDBC_URL));
		bds.setUsername(env.getProperty(ENV_JDBC_USERNAME));
		bds.setPassword(env.getProperty(ENV_JDBC_PASSWORD));
		return bds;
	}

	@Bean
	@Profile("prod")
	public DataSource prodDataSource() {
		final JndiObjectFactoryBean ds = new JndiObjectFactoryBean();
		ds.setLookupOnStartup(true);
		ds.setJndiName("jdbc/postDS");
		ds.setCache(true);

		return (DataSource) ds.getObject();
	}

}
