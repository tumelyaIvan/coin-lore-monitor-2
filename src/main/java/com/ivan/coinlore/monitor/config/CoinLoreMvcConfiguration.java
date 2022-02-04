package com.ivan.coinlore.monitor.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration

@EnableWebMvc
@EnableScheduling
@EnableJpaRepositories(basePackages = {
                        "com.ivan.coinlore.monitor.dao"
})

@ComponentScan("com.ivan.coinlore.monitor.rest")
@ComponentScan("com.ivan.coinlore.monitor.service.impl")

@PropertySource("classpath:orm.properties")
public class CoinLoreMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public ObjectMapper jacksonMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        int timeout = 5 * 60 * 1000;
        httpRequestFactory.setConnectTimeout(timeout);
        httpRequestFactory.setReadTimeout(timeout);

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        return restTemplate;
    }

    @Bean
    public DataSource dataSource(@Value("${dbUrl}") String url, 
                                 @Value("${dbUser}") String username, 
                                 @Value("${dbPassword}") String password, 
                                 @Value("${driverClassName}") String driverClassName) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, 
                                                                       @Value("${showSql}") Boolean showSql, 
                                                                       @Value("${dbPlatform}") String dbPlatform) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("com.ivan.coinlore.monitor.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(showSql);
        vendorAdapter.setDatabasePlatform(dbPlatform);

        entityManager.setJpaVendorAdapter(vendorAdapter);

        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
