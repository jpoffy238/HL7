package com.mj.HL7;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    @Autowired
    public DataSource masterDataSource() {
    	DataSource ds = DataSourceBuilder.create().build();
        return    ds;
       }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate( DataSource masterDataSource) {
    	        return new JdbcTemplate(masterDataSource);
    }
}
