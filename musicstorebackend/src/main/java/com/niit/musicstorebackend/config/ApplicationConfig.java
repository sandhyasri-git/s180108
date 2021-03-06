package com.niit.musicstorebackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.musicstorebackend.dao.ProductDAO;
import com.niit.musicstorebackend.dao.ProductDAOImpl;
import com.niit.musicstorebackend.model.Product;
import com.niit.musicstorebackend.model.User;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement

public class ApplicationConfig {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("org.h2.Driver");
	    dataSource.setUrl("jdbc:h2:tcp://localhost/~/sandhya"); 
	    dataSource.setUsername("sa");
	    dataSource.setPassword("");
	    System.out.println("data source");
	    return dataSource;
	}
	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.put("hibernate.hbm2ddl.auto", "update");
	    properties.put("hibernate.current_session_context_class", "thread");
	    System.out.println("hibernate");
	    return properties;
	}

	
	@Autowired
	@Bean(name="sessionfactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    sessionBuilder.addProperties(getHibernateProperties());
	    sessionBuilder.addAnnotatedClasses(Product.class);
	    sessionBuilder.addAnnotatedClasses(User.class);
		   System.out.println("session factory");
		   
		    return sessionBuilder.buildSessionFactory();
		}

	@Autowired
	@Bean(name="productDAO")
	public ProductDAO getProductDAO(SessionFactory sessionFactory)
	{
		return new ProductDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="product")
	public Product getProduct()
	{
		return new Product();
	}

	@Autowired
	@Bean(name = "transactionmanager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

}
