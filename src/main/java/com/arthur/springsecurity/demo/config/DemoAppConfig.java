package com.arthur.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.arthur.springsecurity")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	//variable holds properties
	@Autowired
	private Environment env;
	private Logger logger=Logger.getLogger(getClass().getName());

	//define a bean
	@Bean
	public org.springframework.web.servlet.ViewResolver ViewResolver() {
		InternalResourceViewResolver viewResolver= new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	//define a bean for datasource
	
	
	@Bean
	public DataSource securityDataSource() {
		//create connnection pool
		ComboPooledDataSource securityDataSource
		=new ComboPooledDataSource();
		//jdbc driver
		try {
		securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		logger.info("jdbc uri="+env.getProperty("jdbc.url"));
		logger.info("jdbc user="+env.getProperty("jdbc.user"));
		
		
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		//set connection pool
		securityDataSource.setInitialPoolSize(
         getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMaxPoolSize(
		         getIntProperty("connection.pool.maxPoolSize"));		
		securityDataSource.setMinPoolSize(
		         getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxIdleTime(
		         getIntProperty("connection.pool.maxIdleTime"));		
		return securityDataSource;
	}
	private int getIntProperty(String pn) {
		String pV=env.getProperty(pn);
		int intP=Integer.parseInt(pV);
		return intP;
	}
}
