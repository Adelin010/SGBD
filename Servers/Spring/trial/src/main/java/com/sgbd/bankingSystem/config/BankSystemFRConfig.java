package com.sgbd.bankingSystem.config;

import java.util.HashMap;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagementFactoryFR",
    transactionManagerRef = "transactionManagerFR",
    basePackages = {
        "com.sgbd.bankingSystem.repos.repoFR"
    }
)
public class BankSystemFRConfig {

    @Bean(name = "dataSourceFR")
    @ConfigurationProperties(prefix = "spring.banksystemfr.datasource")
    public DataSource dataSourceFR(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagementFactoryFR")
    public LocalContainerEntityManagerFactoryBean entityManagementFactoryFR(EntityManagerFactoryBuilder builder, @Qualifier("dataSourceFR") DataSource dataSource){
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "none");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        
        return builder.dataSource(dataSource)
                                .properties(props)
                                .packages("com.sgbd.bankingSystem.model")
                                .persistenceUnit("BankAccount")
                                .build();

    }

    @Bean(name = "transactionManagerFR")
    public PlatformTransactionManager transactionManagerFR(@Qualifier("entityManagementFactoryFR") EntityManagerFactory em){
        return new JpaTransactionManager(em);    }
}
