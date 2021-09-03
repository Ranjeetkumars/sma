/**
 * 
 */
package com.pro.social.media.agent.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pro.social.media.agent.multitenancy.CurrentTenantIdentifierResolverImpl;
import com.pro.social.media.agent.multitenancy.MapMultiTenantConnectionProvider;
import com.pro.social.media.agent.utills.CommonConstants;

import lombok.extern.slf4j.Slf4j;
/**
 * This class defines the data sources to be used for accessing the different
 * databases (one database per tenant). It generates the Hibernate session and
 * entity bean for database access via Spring JPA as well as the Transaction
 * manager to be used.
 * 
 *@author VENKAT_PRO
 * @version 1.0
 * @since 1.0 (June 2019)
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.pro.social.media.agent" }, transactionManagerRef = "txManager")
@EnableTransactionManagement
@Slf4j
public class MultiTenancyConfiguration {
	
//	@Autowired
//	private JpaProperties jpaProperties;

	@Value("${db.driver}")
	private  String DB_DRIVER;

	@Value("${hibernate.dialect}")
	private  String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private  String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private  String HIBERNATE_HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private  String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Value("${hibernate.format_sql}")
	private  String HIBERNATE_FORMAT_SQL;

	// C3P0 Configaration
	@Value("${spring.jpa.properties.hibernate.c3p0.max_size}")
	private  String CONN_POOL_MAX_SIZE;

	@Value("${spring.jpa.properties.hibernate.c3p0.min_size}")
	private  String CONN_POOL_MIN_SIZE;

	@Value("${spring.jpa.properties.hibernate.c3p0.idle_test_period}")
	private  String CONN_POOL_IDLE_PERIOD;
	

	@Value("${db.url}")
	private  String DB_URL;

	@Value("${db.username}")
	private  String DB_USERNAME;
	
	@Value("${db.password}")
	private  String DB_PASSWORD;
	
	@Value("${db2.url}")
	private  String DB_URL2;

	@Value("${db2.username}")
	private  String DB_USERNAME2;
	
	@Value("${db2.password}")
	private  String DB_PASSWORD2;
	
	@Value("${db3.url}")
	private  String DB_URL3;

	@Value("${db3.username}")
	private  String DB_USERNAME3;
	
	@Value("${db3.password}")
	private  String DB_PASSWORD3;
	
	

	
	
	
    /**
     * Builds a map of all data sources defined the application.yml file     * 
     * @return
     */
    @Primary
    @Bean(name = "dataSourcesMtApp")
    public Map<String, DataSource> dataSourcesMtApp() {
        Map<String, DataSource> result = new HashMap<>();
//        ComboPooledDataSource dataSource = new ComboPooledDataSource("venkat");
//		try {
//			dataSource.setDriverClass(DB_DRIVER);
//		} catch (PropertyVetoException pve) {
//			return null;
//		}
//		dataSource.setJdbcUrl(DB_URL);
//		dataSource.setUser(DB_USERNAME);
//		dataSource.setPassword(DB_PASSWORD);
//		dataSource.setMinPoolSize(Integer.parseInt(CONN_POOL_MIN_SIZE));
//		dataSource.setMaxPoolSize(Integer.parseInt(CONN_POOL_MAX_SIZE));
//		dataSource.setMaxIdleTime(Integer.parseInt(CONN_POOL_IDLE_PERIOD));
//		log.info("::::::::ComboPooledDataSource::::" + DB_URL + "::: is created::::::" + dataSource);
	
//		ComboPooledDataSource dataSource1 = new ComboPooledDataSource("venkat1");
//		try {
//			dataSource1.setDriverClass(DB_DRIVER);
//		} catch (PropertyVetoException pve) {
//			return null;
//		}
//		dataSource1.setJdbcUrl(DB_URL2);
//		dataSource1.setUser(DB_USERNAME2);
//		dataSource1.setPassword(DB_PASSWORD2);
//		dataSource1.setMinPoolSize(Integer.parseInt(CONN_POOL_MIN_SIZE));
//		dataSource1.setMaxPoolSize(Integer.parseInt(CONN_POOL_MAX_SIZE));
//		dataSource1.setMaxIdleTime(Integer.parseInt(CONN_POOL_IDLE_PERIOD));
//		log.info("::::::::ComboPooledDataSource::::" + DB_URL + "::: is created::::::" + dataSource1);
		
        ComboPooledDataSource dataSourceDefault = new ComboPooledDataSource("venkat2");
		try {
			dataSourceDefault.setDriverClass(DB_DRIVER);
		} catch (PropertyVetoException pve) {
			return null;
		}
		dataSourceDefault.setJdbcUrl(DB_URL3);
		dataSourceDefault.setUser(DB_USERNAME3);
		dataSourceDefault.setPassword(DB_PASSWORD3);
		dataSourceDefault.setMinPoolSize(Integer.parseInt(CONN_POOL_MIN_SIZE));
		dataSourceDefault.setMaxPoolSize(Integer.parseInt(CONN_POOL_MAX_SIZE));
		dataSourceDefault.setMaxIdleTime(Integer.parseInt(CONN_POOL_IDLE_PERIOD));
		log.info("::::::::ComboPooledDataSource::::" + DB_URL + "::: is created::::::" + dataSourceDefault);
//		result.put("tenantId1", dataSource);
//		result.put("tenantId2", dataSource1);
		result.put(CommonConstants.DEFAULT_TEANTID, dataSourceDefault);
		return result;
    }

    /**
     * Autowires the data sources so that they can be used by the Spring JPA to
     * access the database
     * 
     * @return
     */
    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        // Autowires dataSourcesMtApp
        return new MapMultiTenantConnectionProvider();
    }

    /**
     * Since this is a multi-tenant application, Hibernate requires that the current
     * tenant identifier is resolved for use with
     * {@link org.hibernate.context.spi.CurrentSessionContext} and
     * {@link org.hibernate.SessionFactory#getCurrentSession()}
     * 
     * @return
     */
    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new CurrentTenantIdentifierResolverImpl();
    }

    /**
     * org.springframework.beans.factory.FactoryBean that creates a JPA
     * {@link javax.persistence.EntityManagerFactory} according to JPA's standard
     * container bootstrap contract. This is the most powerful way to set up a
     * shared JPA EntityManagerFactory in a Spring application context; the
     * EntityManagerFactory can then be passed to JPA-based DAOs via dependency
     * injection. Note that switching to a JNDI lookup or to a
     * {@link org.springframework.orm.jpa.LocalEntityManagerFactoryBean} definition
     * is just a matter of configuration!
     * 
     * @param multiTenantConnectionProvider
     * @param currentTenantIdentifierResolver
     * @return
     */    
    @Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(MultiTenantConnectionProvider multiTenantConnectionProvider,
		CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

		Map<String, Object> hibernateProps = new LinkedHashMap<>();
//		hibernateProps.putAll(this.jpaProperties.getProperties());
		hibernateProps.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProps.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		hibernateProps.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

		// No dataSource is set to resulting entityManagerFactoryBean
		LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
		result.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		result.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		result.setJpaPropertyMap(hibernateProps);

		return result;
	}
    
    
    /**
     * Interface used to interact with the entity manager factory for the
     * persistence unit.
     * 
     * @param entityManagerFactoryBean
     * @return
     */
    @Bean
	public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return entityManagerFactoryBean.getObject();
	}

    @Bean
	public SessionFactory getSessionFactory(EntityManagerFactory entityManagerFactory) {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}
    /**
     * Creates a new
     * {@link org.springframework.orm.jpa.JpaTransactionManager#JpaTransactionManager(EntityManagerFactory emf)}
     * instance.
     * 
     * {@link org.springframework.transaction.PlatformTransactionManager} is the
     * central interface in Spring's transaction infrastructure. Applications can
     * use this directly, but it is not primarily meant as API: Typically,
     * applications will work with either TransactionTemplate or declarative
     * transaction demarcation through AOP.
     * 
     * @param entityManagerFactory
     * @return
     */
	@Bean
	public PlatformTransactionManager txManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
