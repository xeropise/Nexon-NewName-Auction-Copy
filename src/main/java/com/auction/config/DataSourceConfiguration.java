package com.auction.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.auction.common.constant.DataSourceConstant.MASTER;
import static com.auction.common.constant.DataSourceConstant.SLAVE;

@Configuration
public class DataSourceConfiguration {


    @Bean(MASTER)
    @ConfigurationProperties(prefix = "auction.datasource.master.hikari")
    DataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(SLAVE)
    @ConfigurationProperties(prefix = "auction.datasource.slave.hikari")
    DataSource slaveDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("routingDataSource")
    @Primary
    @DependsOn(value = {MASTER, SLAVE})
    DataSource routingDataSource(
            DataSource masterDataSource,
            DataSource slaveDataSource
    ) {
        Map<Object, Object> dataSourceMap = new HashMap<>();

        dataSourceMap.put(MASTER, masterDataSource);
        dataSourceMap.put(SLAVE, slaveDataSource);

        RoutingDataSource dataSource = new RoutingDataSource();

        dataSource.setDefaultTargetDataSource(masterDataSource);
        dataSource.setTargetDataSources(dataSourceMap);
        dataSource.afterPropertiesSet();

        return new LazyConnectionDataSourceProxy(dataSource);
    }

    static class RoutingDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
                // log.info { "Slave Working" }
                return SLAVE;
            }

            // log.info { "Master Working" }
            return MASTER;
        }
    }
}
