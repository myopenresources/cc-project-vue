package com.cjhme.demo.impl.base.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.cjhme.system.impl.base.mybatis.interceptor.PrepareInterceptor;


/**
 *
 * <p>
 * Title: MyBatisConfig.java
 * </p>
 * Description: mybatis配置
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Configuration
public class DemoSessionTemplateConfig {

    @Autowired
    private Environment env;

    @Resource(name="demo.dataSource")
    private DataSource demoDataSource;



    /**
     * sqlSessionTemplate
     * @return
     * @throws Exception
     */
    @Bean(name="demo.sqlSessionTemplate")
    public SqlSessionTemplate demoSessionTemplate() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(env.getProperty("demo.mybatis.configLocation")));
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + env.getProperty("demo.mybatis.mapperLocations");
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));

        sqlSessionFactoryBean.setDataSource(demoDataSource);

        PrepareInterceptor prepareInterceptor = new PrepareInterceptor(this.env);
        Properties properties=new Properties();
        properties.setProperty("dialect",env.getProperty("demo.dialect"));
        properties.setProperty("stmtIdRegex",env.getProperty("demo.stmtIdRegex"));
        prepareInterceptor.setProperties(properties);
        
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{prepareInterceptor});

        return  new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
    }


}

