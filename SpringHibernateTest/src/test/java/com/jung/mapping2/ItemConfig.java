package com.jung.mapping2;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
public class ItemConfig {
	private static final String PACKAGE = "com.jung.mapping2";

	@Bean
	DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl("jdbc:h2:tcp://localhost/~/test");
		hikariConfig.setUsername("sa");
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	JpaTransactionManager transactionManager(@Qualifier("sessionFactory1") EntityManagerFactory  emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.H2);
		jpaVendorAdapter.setShowSql(true);
		return jpaVendorAdapter;
	}

	@Bean(name={"sessionFactory1"})
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());

		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		localContainerEntityManagerFactoryBean.setJpaProperties(properties);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPackagesToScan(PACKAGE);

		return localContainerEntityManagerFactoryBean;
	}
	
	@Bean(name={"sessionFactory2"})
	LocalContainerEntityManagerFactoryBean entityManagerFactory2() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());

		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		localContainerEntityManagerFactoryBean.setJpaProperties(properties);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPackagesToScan(PACKAGE);

		return localContainerEntityManagerFactoryBean;
	}
}
