package com.sgbd.bankingSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.sgbd.bankingSystem.dbrepos.dbrepo3",
    entityManagerFactoryRef = "db3EntityManagerFactory",
    transactionManagerRef = "db3TransactionManager"
)
public class Db3Config {}
