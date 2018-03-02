package com.tamguo.config.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.Assert;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.tamguo.mybatis.MySqlSessionFactoryBean;

@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
@MapperScan("com.tamguo.dao")
@EnableConfigurationProperties(MybatisProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisAutoConfiguration implements EnvironmentAware{
	
	private final Logger logger = Logger.getLogger(getClass());
	private Environment environment;
	private RelaxedPropertyResolver propertyResolver;
	
	@Autowired
	private MybatisProperties properties;
	
    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    
    @Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}
    
    @PostConstruct
    public void checkConfigFileExists() {
        if (this.properties.isCheckConfigLocation()) {
            Resource resource = this.resourceLoader
                    .getResource(this.properties.getConfig());
            Assert.state(resource.exists(),
                    "Cannot find config location: " + resource
                            + " (please add config file or check your Mybatis "
                            + "configuration)");
        }
    }
    
    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
	public MySqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		MySqlSessionFactoryBean sqlSessionFactoryBean = new MySqlSessionFactoryBean();
		sqlSessionFactoryBean.setDaoBasePackage("com.tamguo.dao");
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setModelBasePackage("com.tamguo.model");
		sqlSessionFactoryBean.setDaoSuffix("Mapper");
		sqlSessionFactoryBean.setModelSuffix("Entity");
		List<Interceptor> plugins = new ArrayList<Interceptor>();
		// 显示SQL语句，可以打开，但不要提交
		//plugins.add(new ShowSQLInterceptor());
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.put("dialect", "mysql");
		properties.put("offsetAsPageNum", false);
		properties.put("rowBoundsWithCount", true);
		properties.put("reasonable", true);
		pageHelper.setProperties(properties);
		plugins.add(pageHelper);
		sqlSessionFactoryBean.setPlugins(plugins.toArray(new Interceptor[] {}));
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml");
		sqlSessionFactoryBean.setMapperLocations(resources);
		return sqlSessionFactoryBean;
	}

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory,MybatisProperties properties) {
        return new SqlSessionTemplate(sqlSessionFactory,
                properties.getExecutorType());
    }
    
    @Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() throws SQLException {
		if (StringUtils.isEmpty(propertyResolver.getProperty("url"))) {
			logger.warn("数据库连接池异常，请检查配置文件，当前配置文件："+Arrays.toString(environment.getActiveProfiles()));
			throw new ApplicationContextException("数据库连接池配置异常");
		}
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
		druidDataSource.setUrl(propertyResolver.getProperty("url"));
		druidDataSource.setUsername(propertyResolver.getProperty("username"));
		druidDataSource.setPassword(propertyResolver.getProperty("password"));
		druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));
		druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle")));
		druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive")));
		druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("maxWait")));
		druidDataSource.setTimeBetweenEvictionRunsMillis(
				Long.parseLong(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
		druidDataSource.setMinEvictableIdleTimeMillis(
				Long.parseLong(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
		druidDataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
		druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("testWhileIdle")));
		druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow")));
		druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("testOnReturn")));
		druidDataSource.setPoolPreparedStatements(
				Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements")));
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.parseInt(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));
		druidDataSource.setFilters(propertyResolver.getProperty("filters"));
		return druidDataSource;
	}
    
	/**
	 * 配置事务管理器
	 */
	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource)
			throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

}
