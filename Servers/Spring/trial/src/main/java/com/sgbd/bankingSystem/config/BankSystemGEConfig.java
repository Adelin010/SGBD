package com.sgbd.bankingSystem.config;

import java.util.HashMap;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagementFactoryGE",
    transactionManagerRef = "transactionManagerGE",
    basePackages = {
        "com.sgbd.bankingSystem.repos.repoGE"
    }
)
public class BankSystemGEConfig {

    @Primary
    @Bean(name = "dataSourceGE")
    @ConfigurationProperties(prefix = "spring.banksystemge.datasource")
    public DataSource dataSourceGE(){
        return DataSourceBuilder.create().build();
    }

    @Primary 
    @Bean(name = "entityManagementFactoryGE")
    public LocalContainerEntityManagerFactoryBean entityManagementFactoryGE(EntityManagerFactoryBuilder builder, @Qualifier("dataSourceGE") DataSource dataSource){
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "none");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        
        return builder.dataSource(dataSource)
                                .properties(props)
                                .packages("com.sgbd.bankingSystem.model")
                                .persistenceUnit("BankAccount")
                                .build();

    }

    @Primary
    @Bean(name = "transactionManagerGE")
    public PlatformTransactionManager transactionManagerGE(@Qualifier("entityManagementFactoryGE") EntityManagerFactory em){
        return new JpaTransactionManager(em);    }
}
