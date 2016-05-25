package com.webmarket.application.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cache.ehcache.EhCacheRegionFactory.NET_SF_EHCACHE_CONFIGURATION_RESOURCE_NAME;
import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource({"classpath:db/mysql.properties"})
@EnableJpaRepositories(basePackages = {"com.webmarket.**.dao"})
@ComponentScan(basePackages = {"com.webmarket.**.dao"})
@EnableTransactionManagement
public class DbConfig {
    @Autowired
    private Environment environment;

    @Value("classpath:db/initDB.sql")
    private Resource initDb;

    @Value("classpath:db/populateDB.sql")
    private Resource populateDb;

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setEnabled(Boolean.valueOf(environment.getProperty("database.init")));
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        return dataSourceInitializer;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(initDb);
        resourceDatabasePopulator.addScript(populateDb);
        return resourceDatabasePopulator;
    }

    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(environment.getProperty("database.driver"));
        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.webmarket.**.model");

        Properties jpaPropertyMap = new Properties();
        jpaPropertyMap.setProperty(SHOW_SQL, environment.getProperty("jpa.showSql"));
        jpaPropertyMap.setProperty(FORMAT_SQL, environment.getProperty("hibernate.format_sql"));
        jpaPropertyMap.setProperty(USE_SQL_COMMENTS, environment.getProperty("hibernate.use_sql_comments"));
        jpaPropertyMap.setProperty(CACHE_REGION_FACTORY, environment.getProperty("hibernate.cache.region.factory"));
        jpaPropertyMap.setProperty(USE_SECOND_LEVEL_CACHE, environment.getProperty("hibernate.use_second_cache_level"));
        jpaPropertyMap.setProperty(USE_QUERY_CACHE, environment.getProperty("hibernate.use_query_cache"));
        jpaPropertyMap.setProperty(NET_SF_EHCACHE_CONFIGURATION_RESOURCE_NAME, environment.getProperty("ehcache.config_location"));

        entityManagerFactoryBean.setJpaProperties(jpaPropertyMap);
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}
