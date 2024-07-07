package aliramadhan.assignment3.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {


    @Bean
    @Qualifier("dataSource1")
    public DataSource dataSource1() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/employeeApp?useSSL=false&serverTimezone=Asia/Jakarta");
        dataSourceBuilder.username("user");
        dataSourceBuilder.password("password123");
        return dataSourceBuilder.build();
    }

    @Bean
    @Qualifier("dataSource2")
    public DataSource getDataSourcePart2() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/employeeApp1?useSSL=false&serverTimezone=Asia/Jakarta");
        dataSourceBuilder.username("user");
        dataSourceBuilder.password("password123");
        return dataSourceBuilder.build();
    }

}