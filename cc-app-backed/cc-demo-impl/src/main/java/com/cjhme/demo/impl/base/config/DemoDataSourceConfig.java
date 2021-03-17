package com.cjhme.demo.impl.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 * <p>
 * Title: DataSourceConfig.java
 * </p>
 * Description: 数据源配置
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Configuration
public class DemoDataSourceConfig {

    @Autowired
    private Environment env;


    /**
     * 数据源
     * @return
     */
    @Bean(name = "demo.dataSource")
    public DataSource demoDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName(env.getProperty("demo.uniqueResourceName"));
        ds.setXaDataSourceClassName(env.getProperty("demo.xaDataSourceClassName"));
        ds.setMinPoolSize(Integer.valueOf(env.getProperty("demo.minPoolSize")));
        ds.setMaxPoolSize(Integer.valueOf(env.getProperty("demo.maxPoolSize")));
        ds.setBorrowConnectionTimeout(Integer.valueOf(env.getProperty("demo.borrowConnectionTimeout")));
        ds.setTestQuery(env.getProperty("demo.testQuery"));
        ds.setMaintenanceInterval(Integer.valueOf(env.getProperty("demo.maintenanceInterval")));

        Properties xaProperties=new Properties();
        xaProperties.setProperty("url",env.getProperty("demo.xaUrl"));
        xaProperties.setProperty("user",env.getProperty("demo.xaUser"));
        xaProperties.setProperty("password",env.getProperty("demo.xaPassword"));
        ds.setXaProperties(xaProperties);

        return ds;
    }

}
