//package aliramadhan.assignment3.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class DataSourceConfigCop {
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource1")
//    public HikariConfig hikariConfig1() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    @Primary
//    public DataSource dataSource1() {
//        return new HikariDataSource(hikariConfig1());
//    }
//
//    @Bean
//    @Primary
//    public DataSourceTransactionManager transactionManager1(DataSource dataSource1) {
//        return new DataSourceTransactionManager(dataSource1);
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource2")
//    public HikariConfig hikariConfig2() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    public DataSource dataSource2() {
//        return new HikariDataSource(hikariConfig2());
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager2(DataSource dataSource2) {
//        return new DataSourceTransactionManager(dataSource2);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate1(DataSource dataSource1) {
//        return new JdbcTemplate(dataSource1);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate2(DataSource dataSource2) {
//        return new JdbcTemplate(dataSource2);
//    }
//}
