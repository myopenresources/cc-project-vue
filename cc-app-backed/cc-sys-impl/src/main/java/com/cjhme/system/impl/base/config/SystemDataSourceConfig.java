package com.cjhme.system.impl.base.config;

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
public class SystemDataSourceConfig {

    @Autowired
    private Environment env;


    /**
     * 主数据源
     * @return
     */
    @Primary
    @Bean(name = "system.dataSource")
    public DataSource systemDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName(env.getProperty("system.uniqueResourceName"));
        ds.setXaDataSourceClassName(env.getProperty("system.xaDataSourceClassName"));
        ds.setMinPoolSize(Integer.valueOf(env.getProperty("system.minPoolSize")));
        ds.setMaxPoolSize(Integer.valueOf(env.getProperty("system.maxPoolSize")));
        ds.setBorrowConnectionTimeout(Integer.valueOf(env.getProperty("system.borrowConnectionTimeout")));
        ds.setTestQuery(env.getProperty("system.testQuery"));
        ds.setMaintenanceInterval(Integer.valueOf(env.getProperty("system.maintenanceInterval")));

        Properties xaProperties=new Properties();
        xaProperties.setProperty("url",env.getProperty("system.xaUrl"));
        xaProperties.setProperty("user",env.getProperty("system.xaUser"));
        xaProperties.setProperty("password",env.getProperty("system.xaPassword"));
        ds.setXaProperties(xaProperties);

        return ds;
    }

}
