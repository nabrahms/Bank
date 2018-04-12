/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Nick-PC
 */
@Configuration
public class DBConfig {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:C:\\Users\\Nick-PC\\Documents\\NetBeansProjects\\bank.io\\Bank\\UsersNick-PCDocumentsSQLite Databasebankdatabase.db");
        dataSourceBuilder.type(org.sqlite.SQLiteDataSource.class);
        return dataSourceBuilder.build();
    }
}
