package com.project.management.projectmanagement.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.project.management.projectmanagement.dao"}
)
public class DatabaseConfig {

    // entity manager
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManager(@Autowired HibernateJpaVendorAdapter hibernateJpaVendorAdapter,
                                                                @Autowired DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        em.setPersistenceUnitName("projectManagerPU");
        // In case of Spring-based scanning, no persistence.xml is necessary; all you need to do is to specify base packages to search here.
        em.setPackagesToScan("com.project.management.projectmanagement.entity");
        return em;
    }

    // transaction manager
    public PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);

    }

    // hibernate configuration
    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(Boolean.TRUE);
        hibernateJpaVendorAdapter.setGenerateDdl(Boolean.TRUE);
//        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

        return hibernateJpaVendorAdapter;
    }

    // Creating a Datasource
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .password("12je12ij12@123213%!$#$#@D@D@#DEWDede")
                .url("jdbc:mysql://localhost:3306/dev")
                .username("root")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
