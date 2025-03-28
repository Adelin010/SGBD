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
    entityManagerFactoryRef = "entityManagementFactoryRU",
    transactionManagerRef = "transactionManagerRU",
    basePackages = {
        "com.sgbd.bankingSystem.repos.repoRU"
    }
)
public class BankSystemRUConfig {

    @Bean(name = "dataSourceRU")
    @ConfigurationProperties(prefix = "spring.banksystemru.datasource")
    public DataSource dataSourceFR(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagementFactoryRU")
    public LocalContainerEntityManagerFactoryBean entityManagementFactoryRU(EntityManagerFactoryBuilder builder, @Qualifier("dataSourceRU") DataSource dataSource){
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "none");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        
        return builder.dataSource(dataSource)
                                .properties(props)
                                .packages("com.sgbd.bankingSystem.model")
                                .persistenceUnit("BankAccount")
                                .build();

    }

    @Bean(name = "transactionManagerRU")
    public PlatformTransactionManager transactionManagerRU(@Qualifier("entityManagementFactoryRU") EntityManagerFactory em){
        return new JpaTransactionManager(em);    }
}
