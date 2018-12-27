package com.spring.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//This is equivalent to spring-hibernate5-conf.xml, spring-dao.xml, and spring-service.xml

@EnableTransactionManagement	//<tx:annotation/>
@Configuration
@ComponentScan(basePackages = {"com.spring.web.mvc.advice", "com.spring.web.mvc.dao","com.spring.web.mvc.service"})
//<aop:aspectj-autoproxy proxy-target-class="true"/>
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableJpaRepositories(basePackages = "com.spring.web.mvc.repository")
public class SpringRootConfig {

	/*@Autowired
	private ApplicationContext context;*/
	
	/*@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
	    sessionBuilder.scanPackages("com.spring.web.mvc.dao.entity");
	    //sessionBuilder.addAnnotatedClasses(CustomerEntity.class,CustomerHistoryEntity.class,CityEntity.class);
	    sessionBuilder.setProperty("hibernate.show_sql", "true");
	    sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    sessionBuilder.setProperty("hibernate.hbm2ddl.auto", "update");
	   // sessionBuilder.setProperties(getHibernateProperties());
	    return sessionBuilder.buildSessionFactory();
	}*/

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {
            "com.spring.web.mvc.dao.entity"
        });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());
        return entityManagerFactoryBean;
    }


	 final Properties additionalProperties() {
	        final Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        hibernateProperties.setProperty("hibernate.show_sql", "true");
	        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
	        return hibernateProperties;
	    }

	
	/*@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
		factoryBean.setAnnotatedClasses(CustomerEntity.class,CustomerHistoryEntity.class,CityEntity.class);
		return factoryBean;
	}*/

	//<bean id
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		//jdbc/cpool = META-INF/context.xml
		//java:comp/env/ - this fix for tomcat JNDI lookup
		DataSource dataSource = dataSourceLookup.getDataSource("java:comp/env/jdbc/cpool");
		return dataSource;
	}
	
	@Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

	/*@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory());
		//transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}*/
}
