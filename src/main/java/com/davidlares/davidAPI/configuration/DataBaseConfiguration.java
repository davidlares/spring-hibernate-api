package com.davidlares.davidAPI.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // queremos trabajar una interfaz como un Bean
@EnableTransactionManagement // ayudar a la parte de las transacciones
public class DataBaseConfiguration {
	
	@Bean // nos dice el comportamiento que va a tener el objeto, contra que se instancia
	
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource()); 
		// conf de la base de datos
		sessionFactoryBean.setPackagesToScan("com.davidlares.davidAPI.model");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/javaAPI?autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("davicho");
		return dataSource;
	}
	
	// metodo para Hibernate
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		//properties.put("spring.jpa.properties.hibernate","org.hibernate.dialect.MySQLDialect");
		properties.put("show_sql","true");
		return properties;
	}

	@Bean
	@Autowired // Persistido y atado por otro metodo persistido
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
	
}
