package com.example.demo.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = UserActionDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "userActionSqlSessionFactory")
public class UserActionDataSourceConfig {

    // 精确到 demo 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.example.demo.user.daoAction";
    private static final String MAPPER_LOCATION = "classpath:mapper/userAction/*.xml";

    @Value("${userAction.datasource.url}")
    private String url;

    @Value("${userAction.datasource.username}")
    private String user;

    @Value("${userAction.datasource.password}")
    private String password;

    @Value("${userAction.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "userActionDataSource")
    public DataSource userActionDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(100);
        dataSource.setMaxActive(1000);
        dataSource.setMinIdle(50);
        dataSource.setMaxWait(5000L);
        dataSource.setQueryTimeout(60);
        return dataSource;
    }

    @Bean(name = "userActionTransactionManager")
    public DataSourceTransactionManager userActionTransactionManager() {
        return new DataSourceTransactionManager(userActionDataSource());
    }

    @Bean(name = "userActionSqlSessionFactory")
    public SqlSessionFactory userActionSqlSessionFactory(@Qualifier("userActionDataSource") DataSource userActionDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(userActionDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(UserActionDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}