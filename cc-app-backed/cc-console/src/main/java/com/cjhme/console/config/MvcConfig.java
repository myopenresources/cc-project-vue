package com.cjhme.console.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 *
 * <p>
 * Title: MvcConfig.java
 * </p>
 * Description: MvcConfig配置
 * <p>
 * Modify histoty:
 *
 * @author cjh
 * @version 1.0
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {


    Log LOGGER = LogFactory.getLog(WebMvcConfigurationSupport.class);


    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("初始化拦截器!");
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }

    /**
     * 配置servlet处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(-1);
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxInMemorySize(1024);
        return commonsMultipartResolver;
    }

    /**
     * 文件上传注册
     * @return
     */
    @Bean
    public FilterRegistrationBean<MultipartFilter> multipartFilterRegistrationBean() {
        final MultipartFilter multipartFilter = new MultipartFilter();
        final FilterRegistrationBean<MultipartFilter> filterRegistrationBean = new FilterRegistrationBean<MultipartFilter>(multipartFilter);
        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
        return filterRegistrationBean;
    }
}