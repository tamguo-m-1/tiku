package com.tamguo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.tamguo.interceptor.MenuInterceptor;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Value("${file.storage.path}")
	private String fileStoragePath;
	@Autowired
	private MenuInterceptor menuInterceptor;

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuInterceptor).addPathPatterns("/**");
	}
	
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler("/files/**").addResourceLocations("file:"+fileStoragePath);
       super.addResourceHandlers(registry);
   }
	
	@Bean(name="producer")  
    public DefaultKaptcha getKaptchaBean(){  
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();  
        Properties properties=new Properties();  
  //      properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.image.width", "125");  
        properties.setProperty("kaptcha.image.height", "45");  
        properties.setProperty("kaptcha.session.key", "code");  
        properties.setProperty("kaptcha.textproducer.char.length", "4");  
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");          
        Config config=new Config(properties);  
        defaultKaptcha.setConfig(config);  
        return defaultKaptcha;  
    }  
	
	@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer(){
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
            }
        };
    }

}
