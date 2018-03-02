package com.tamguo.config.dao;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * druid sql监控
 *
 */
@Configuration
public class DruidConfig { 
	
  @Bean public FilterRegistrationBean filterRegistrationBean() { 
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(); 
    filterRegistrationBean.setFilter(new WebStatFilter()); 
    filterRegistrationBean.addUrlPatterns("/*"); 
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); 
    return filterRegistrationBean; 
   }
}