package com.sgbd.bankingSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.sgbd.bankingSystem.dbrepos.dbrepo4",
    entityManagerFactoryRef = "db4EntityManagerFactory",
    transactionManagerRef = "db4TransactionManager"
)
public class Db4Config {}
