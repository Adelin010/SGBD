package com.sgbd.bankingSystem.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DataBaseConfig {
    

    /**
     * Looks after the db1.datasource in the application.properties 
     * and use the information provided to instablish a connection 
     * with the new Database
     * !! NAME OF THE BEAN BY DEFAULT IS THE NAME OF THE FUNCTION
     */
    
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "db3.datasource")
    public DataSource db3DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "db4.datasource")
    public DataSource db4DataSource(){
        return DataSourceBuilder.create().build();
    }

    
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
        @Qualifier("db1DataSource") DataSource db1DataSource
    ){
        LocalContainerEntityManagerFactoryBean localC = new LocalContainerEntityManagerFactoryBean();
        localC.setDataSource(db1DataSource);
        localC.setPackagesToScan("com.sgbd.bankingSystem.model");
        localC.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localC.setJpaProperties(hibernateProperties());
        return localC;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
        @Qualifier("db2DataSource") DataSource db2DataSource
    ){
        LocalContainerEntityManagerFactoryBean localC = new LocalContainerEntityManagerFactoryBean();
        localC.setDataSource(db2DataSource);
        localC.setPackagesToScan("com.sgbd.bankingSystem.model");
        localC.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localC.setJpaProperties(hibernateProperties());
        return localC;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db3EntityManagerFactory(
        @Qualifier("db3DataSource") DataSource db3DataSource
    ){
        LocalContainerEntityManagerFactoryBean localC = new LocalContainerEntityManagerFactoryBean();
        localC.setDataSource(db3DataSource);
        localC.setPackagesToScan("com.sgbd.bankingSystem.model");
        localC.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localC.setJpaProperties(hibernateProperties());
        return localC;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db4EntityManagerFactory(
        @Qualifier("db4DataSource") DataSource db4DataSource
    ){
        LocalContainerEntityManagerFactoryBean localC = new LocalContainerEntityManagerFactoryBean();
        localC.setDataSource(db4DataSource);
        localC.setPackagesToScan("com.sgbd.bankingSystem.model");
        localC.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localC.setJpaProperties(hibernateProperties());
        return localC;
    }

    
    @Bean
    @Primary
    public PlatformTransactionManager db1TransactionManager(
        @Qualifier("db1EntityManagerFactory") EntityManagerFactory emf
    ) {
        JpaTransactionManager transM = new JpaTransactionManager();
        transM.setEntityManagerFactory(emf);
        return transM;
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager(
        @Qualifier("db2EntityManagerFactory") EntityManagerFactory emf
    ) {
        JpaTransactionManager transM = new JpaTransactionManager();
        transM.setEntityManagerFactory(emf);
        return transM;
    }

    @Bean
    public PlatformTransactionManager db3TransactionManager(
        @Qualifier("db3EntityManagerFactory") EntityManagerFactory emf
    ) {
        JpaTransactionManager transM = new JpaTransactionManager();
        transM.setEntityManagerFactory(emf);
        return transM;
    }

    @Bean
    public PlatformTransactionManager db4TransactionManager(
        @Qualifier("db4EntityManagerFactory") EntityManagerFactory emf
    ) {
        JpaTransactionManager transM = new JpaTransactionManager();
        transM.setEntityManagerFactory(emf);
        return transM;
    }



    private Properties hibernateProperties(){
        return new Properties() {
            {
                put("hibernate.hbm2ddl.auto", "none");
                put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                put("hibernate.show_sql", "true");

            }
        };
    }

}
