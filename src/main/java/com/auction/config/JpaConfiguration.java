package com.auction.config;

import com.auction.config.properties.HibernateProperties;
import com.auction.config.properties.JpaProperties;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties(value = {JpaProperties.class, HibernateProperties.class})
public class JpaConfiguration {
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            JpaProperties jpaProperties,
            HibernateProperties hibernateProperties
    ) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        vendorAdapter.setDatabasePlatform(jpaProperties.databasePlatform);
        vendorAdapter.setShowSql(jpaProperties.showSql);
        vendorAdapter.setGenerateDdl(jpaProperties.generateDdl);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        jpaProperties.properties.put(Environment.HBM2DDL_AUTO, hibernateProperties.ddlAuto);
        jpaProperties.properties.put(Environment.DIALECT, jpaProperties.databasePlatform);
        jpaProperties.properties.put(Environment.PHYSICAL_NAMING_STRATEGY, hibernateProperties.naming.getPhysicalStrategy());

        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.putAll(jpaProperties.properties);
        entityManagerFactory.setJpaProperties(properties);

        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.auction");
        entityManagerFactory.afterPropertiesSet();

        return entityManagerFactory;
    }

    @Bean
    @Primary
    PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory
    ) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }

    @Bean
    public JPAQueryFactory queryFactory(EntityManager entityManager) {
        return new JPAQueryFactory((EntityManager) entityManager);
    }
}
