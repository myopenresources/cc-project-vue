package com.cjhme.console.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * <p>
 * Title: CorsConfig.java
 * </p>
 * Description: 跨域配置
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Configuration
public class CorsConfig {

    @Autowired
    private Environment env;

    /**
     * 跨域过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(Boolean.valueOf(env.getProperty("cors.allowCredentials")));
        config.addAllowedOrigin(env.getProperty("cors.allowedOrigin"));
        config.addAllowedHeader(env.getProperty("cors.allowedHeader"));
        config.addAllowedMethod(env.getProperty("cors.allowedMethod"));
        source.registerCorsConfiguration(env.getProperty("cors.registerPath"), config);
        FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}

