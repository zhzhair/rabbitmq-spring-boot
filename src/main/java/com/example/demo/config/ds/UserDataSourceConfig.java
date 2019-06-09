package com.example.demo.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = UserDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {

    // 精确到 user 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.example.demo.user.dao";
    private static final String MAPPER_LOCATION = "classpath:mapper/user/*.xml";

    @Value("${user.datasource.url}")
    private String url;

    @Value("${user.datasource.username}")
    private String user;

    @Value("${user.datasource.password}")
    private String password;

    @Value("${user.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "userDataSource")
    @Primary
    public DataSource userDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(50);
        dataSource.setMaxActive(500);
        dataSource.setMaxWait(5000L);
        dataSource.setMinIdle(50);
        dataSource.setQueryTimeout(60);
        return dataSource;
    }

    @Bean(name = "userTransactionManager")
    @Primary
    public DataSourceTransactionManager userTransactionManager() {
        return new DataSourceTransactionManager(userDataSource());
    }

    @Bean(name = "userSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource userDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(userDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(UserDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}