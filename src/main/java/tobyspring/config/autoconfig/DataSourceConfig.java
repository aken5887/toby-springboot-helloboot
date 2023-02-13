package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Driver;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.MyDatasourceProperties;

@MyAutoConfiguration
@ConditionalMyOnClass(value = "org.springframework.jdbc.core.JdbcOperations", use=true)
@EnableMyConfigurationProperties(MyDatasourceProperties.class)
@EnableTransactionManagement
public class DataSourceConfig {
  private final Logger logger = Logger.getLogger(this.getClass().getName());

  @Bean
  @ConditionalMyOnClass(value = "com.zaxxer.hikari.HikariDataSource", use = true)
  @ConditionalOnMissingBean
  DataSource hikariDataSource(MyDatasourceProperties properties) {
    HikariDataSource datasource = new HikariDataSource();
    datasource.setDriverClassName(properties.getDriverClassName());
    datasource.setJdbcUrl(properties.getUrl());
    datasource.setUsername(properties.getUsername());
    datasource.setPassword(properties.getPassword());
    return datasource;
  }

  @Bean
  @ConditionalOnMissingBean
  DataSource simpleDataSource(MyDatasourceProperties properties) throws ClassNotFoundException {
    SimpleDriverDataSource dataSource =  new SimpleDriverDataSource();
    dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
    dataSource.setUrl(properties.getUrl());
    dataSource.setUsername(properties.getUsername());
    dataSource.setPassword(properties.getPassword());
    logger.info(dataSource.getDriver().toString());
    return dataSource;
  }

  @Bean
  @ConditionalOnSingleCandidate(DataSource.class)
  @ConditionalOnMissingBean
  JdbcTemplate jdbcTemplate(DataSource dataSource){
    return new JdbcTemplate(dataSource);
  }

  @Bean
  @ConditionalOnSingleCandidate(DataSource.class)
  @ConditionalOnMissingBean
  JdbcTransactionManager jdbcTransactionManager(DataSource dataSource){
    return new JdbcTransactionManager(dataSource);
  }
}
