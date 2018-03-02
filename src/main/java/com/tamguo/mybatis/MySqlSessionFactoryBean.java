package com.tamguo.mybatis;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.Table;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.DefaultDatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.tamguo.mybatis.handler.ColumnHandler;
import com.tamguo.mybatis.handler.ConditionHandler;
import com.tamguo.mybatis.handler.DeleteByConditionHandler;
import com.tamguo.mybatis.handler.DeleteByIdsHandler;
import com.tamguo.mybatis.handler.DeleteHandler;
import com.tamguo.mybatis.handler.InsertHandler;
import com.tamguo.mybatis.handler.ResultMapHandler;
import com.tamguo.mybatis.handler.SelectAllHandler;
import com.tamguo.mybatis.handler.SelectByConditionHandler;
import com.tamguo.mybatis.handler.SelectByEntityHandler;
import com.tamguo.mybatis.handler.SelectByIdsHandler;
import com.tamguo.mybatis.handler.SelectByPageHandler;
import com.tamguo.mybatis.handler.SelectHandler;
import com.tamguo.mybatis.handler.UpdateByConditionHandler;
import com.tamguo.mybatis.handler.UpdateHandler;

@SuppressWarnings("deprecation")
public class MySqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {

	private final Log logger = LogFactory.getLog(getClass());

	private Resource configLocation;

	private Resource[] mapperLocations;

	private DataSource dataSource;

	private TransactionFactory transactionFactory;

	private Properties configurationProperties;

	private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

	private SqlSessionFactory sqlSessionFactory;

	private String environment = SqlSessionFactoryBean.class.getSimpleName();

	private boolean failFast;

	private Interceptor[] plugins;

	private TypeHandler<?>[] typeHandlers;

	private String typeHandlersPackage;

	private Class<?>[] typeAliases;

	private String typeAliasesPackage;

	private DatabaseIdProvider databaseIdProvider = new DefaultDatabaseIdProvider();

	private String daoBasePackage;
	private String modelBasePackage;
	private String daoSuffix;
	private String modelSuffix;

	public void setDaoBasePackage(String daoBasePackage) {
		this.daoBasePackage = daoBasePackage;
	}

	public void setModelBasePackage(String modelBasePackage) {
		this.modelBasePackage = modelBasePackage;
	}

	public void setDaoSuffix(String daoSuffix) {
		this.daoSuffix = daoSuffix;
	}

	public void setModelSuffix(String modelSuffix) {
		this.modelSuffix = modelSuffix;
	}

	/**
	 * Sets the DatabaseIdProvider.
	 *
	 * @since 1.1.0
	 * @return
	 */
	public DatabaseIdProvider getDatabaseIdProvider() {
		return databaseIdProvider;
	}

	/**
	 * Gets the DatabaseIdProvider
	 *
	 * @since 1.1.0
	 * @param databaseIdProvider
	 */
	public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {
		this.databaseIdProvider = databaseIdProvider;
	}

	/**
	 * Mybatis plugin list.
	 *
	 * @since 1.0.1
	 *
	 * @param plugins
	 *            list of plugins
	 *
	 */
	public void setPlugins(Interceptor[] plugins) {
		this.plugins = plugins;
	}

	/**
	 * Packages to search for type aliases.
	 *
	 * @since 1.0.1
	 *
	 * @param typeAliasesPackage
	 *            package to scan for domain objects
	 *
	 */
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	/**
	 * Packages to search for type handlers.
	 *
	 * @since 1.0.1
	 *
	 * @param typeHandlersPackage
	 *            package to scan for type handlers
	 *
	 */
	public void setTypeHandlersPackage(String typeHandlersPackage) {
		this.typeHandlersPackage = typeHandlersPackage;
	}

	/**
	 * Set type handlers. They must be annotated with {@code MappedTypes} and
	 * optionally with {@code MappedJdbcTypes}
	 *
	 * @since 1.0.1
	 *
	 * @param typeHandlers
	 *            Type handler list
	 */
	public void setTypeHandlers(TypeHandler<?>[] typeHandlers) {
		this.typeHandlers = typeHandlers;
	}

	/**
	 * List of type aliases to register. They can be annotated with
	 * {@code Alias}
	 *
	 * @since 1.0.1
	 *
	 * @param typeAliases
	 *            Type aliases list
	 */
	public void setTypeAliases(Class<?>[] typeAliases) {
		this.typeAliases = typeAliases;
	}

	/**
	 * If true, a final check is done on Configuration to assure that all mapped
	 * statements are fully loaded and there is no one still pending to resolve
	 * includes. Defaults to false.
	 *
	 * @since 1.0.1
	 *
	 * @param failFast
	 *            enable failFast
	 */
	public void setFailFast(boolean failFast) {
		this.failFast = failFast;
	}

	/**
	 * Set the location of the MyBatis {@code SqlSessionFactory} config file. A
	 * typical value is "WEB-INF/mybatis-configuration.xml".
	 */
	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * Set locations of MyBatis mapper files that are going to be merged into
	 * the {@code SqlSessionFactory} configuration at runtime.
	 *
	 * This is an alternative to specifying "&lt;sqlmapper&gt;" entries in an
	 * MyBatis config file. This property being based on Spring's resource
	 * abstraction also allows for specifying resource patterns here: e.g.
	 * "classpath*:sqlmap/*-mapper.xml".
	 */
	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	/**
	 * Set optional properties to be passed into the SqlSession configuration,
	 * as alternative to a {@code &lt;properties&gt;} tag in the configuration
	 * xml file. This will be used to resolve placeholders in the config file.
	 */
	public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {
		this.configurationProperties = sqlSessionFactoryProperties;
	}

	/**
	 * Set the JDBC {@code DataSource} that this instance should manage
	 * transactions for. The {@code DataSource} should match the one used by the
	 * {@code SqlSessionFactory}: for example, you could specify the same JNDI
	 * DataSource for both.
	 *
	 * A transactional JDBC {@code Connection} for this {@code DataSource} will
	 * be provided to application code accessing this {@code DataSource}
	 * directly via {@code DataSourceUtils} or
	 * {@code DataSourceTransactionManager}.
	 *
	 * The {@code DataSource} specified here should be the target
	 * {@code DataSource} to manage transactions for, not a
	 * {@code TransactionAwareDataSourceProxy}. Only data access code may work
	 * with {@code TransactionAwareDataSourceProxy}, while the transaction
	 * manager needs to work on the underlying target {@code DataSource}. If
	 * there's nevertheless a {@code TransactionAwareDataSourceProxy} passed in,
	 * it will be unwrapped to extract its target {@code DataSource}.
	 *
	 */
	public void setDataSource(DataSource dataSource) {
		if (dataSource instanceof TransactionAwareDataSourceProxy) {
			// If we got a TransactionAwareDataSourceProxy, we need to perform
			// transactions for its underlying target DataSource, else data
			// access code won't see properly exposed transactions (i.e.
			// transactions for the target DataSource).
			this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
		} else {
			this.dataSource = dataSource;
		}
	}

	/**
	 * Sets the {@code SqlSessionFactoryBuilder} to use when creating the
	 * {@code SqlSessionFactory}.
	 *
	 * This is mainly meant for testing so that mock SqlSessionFactory classes
	 * can be injected. By default, {@code SqlSessionFactoryBuilder} creates
	 * {@code DefaultSqlSessionFactory} instances.
	 *
	 */
	public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {
		this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;
	}

	/**
	 * Set the MyBatis TransactionFactory to use. Default is
	 * {@code SpringManagedTransactionFactory}
	 *
	 * The default {@code SpringManagedTransactionFactory} should be appropriate
	 * for all cases: be it Spring transaction management, EJB CMT or plain JTA.
	 * If there is no active transaction, SqlSession operations will execute SQL
	 * statements non-transactionally.
	 *
	 * <b>It is strongly recommended to use the default
	 * {@code TransactionFactory}.</b> If not used, any attempt at getting an
	 * SqlSession through Spring's MyBatis framework will throw an exception if
	 * a transaction is active.
	 *
	 * @see SpringManagedTransactionFactory
	 * @param transactionFactory
	 *            the MyBatis TransactionFactory
	 */
	public void setTransactionFactory(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

	/**
	 * <b>NOTE:</b> This class <em>overrides</em> any {@code Environment} you
	 * have set in the MyBatis config file. This is used only as a placeholder
	 * name. The default value is
	 * {@code SqlSessionFactoryBean.class.getSimpleName()}.
	 *
	 * @param environment
	 *            the environment name
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * {@inheritDoc}
	 */
	public void afterPropertiesSet() throws Exception {
		notNull(dataSource, "Property 'dataSource' is required");
		notNull(sqlSessionFactoryBuilder, "Property 'sqlSessionFactoryBuilder' is required");

		this.sqlSessionFactory = buildSqlSessionFactory();
	}

	/**
	 * Build a {@code SqlSessionFactory} instance.
	 *
	 * The default implementation uses the standard MyBatis
	 * {@code XMLConfigBuilder} API to build a {@code SqlSessionFactory}
	 * instance based on an Reader.
	 *
	 * @return SqlSessionFactory
	 * @throws IOException
	 *             if loading the config file failed
	 */
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {

		Configuration configuration;

		XMLConfigBuilder xmlConfigBuilder = null;
		if (this.configLocation != null) {
			xmlConfigBuilder = new XMLConfigBuilder(this.configLocation.getInputStream(), null, this.configurationProperties);
			configuration = xmlConfigBuilder.getConfiguration();
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Property 'configLocation' not specified, using default MyBatis Configuration");
			}
			configuration = new Configuration();
			configuration.setVariables(this.configurationProperties);
		}

		if (hasLength(this.typeAliasesPackage)) {
			String[] typeAliasPackageArray = tokenizeToStringArray(this.typeAliasesPackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			for (String packageToScan : typeAliasPackageArray) {
				configuration.getTypeAliasRegistry().registerAliases(packageToScan);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Scanned package: '" + packageToScan + "' for aliases");
				}
			}
		}

		if (!isEmpty(this.typeAliases)) {
			for (Class<?> typeAlias : this.typeAliases) {
				configuration.getTypeAliasRegistry().registerAlias(typeAlias);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Registered type alias: '" + typeAlias + "'");
				}
			}
		}

		if (!isEmpty(this.plugins)) {
			for (Interceptor plugin : this.plugins) {
				configuration.addInterceptor(plugin);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Registered plugin: '" + plugin + "'");
				}
			}
		}

		if (hasLength(this.typeHandlersPackage)) {
			String[] typeHandlersPackageArray = tokenizeToStringArray(this.typeHandlersPackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			for (String packageToScan : typeHandlersPackageArray) {
				configuration.getTypeHandlerRegistry().register(packageToScan);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Scanned package: '" + packageToScan + "' for type handlers");
				}
			}
		}

		if (!isEmpty(this.typeHandlers)) {
			for (TypeHandler<?> typeHandler : this.typeHandlers) {
				configuration.getTypeHandlerRegistry().register(typeHandler);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Registered type handler: '" + typeHandler + "'");
				}
			}
		}

		if (xmlConfigBuilder != null) {
			try {
				xmlConfigBuilder.parse();

				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Parsed configuration file: '" + this.configLocation + "'");
				}
			} catch (Exception ex) {
				throw new NestedIOException("Failed to parse config resource: " + this.configLocation, ex);
			} finally {
				ErrorContext.instance().reset();
			}
		}

		if (this.transactionFactory == null) {
			this.transactionFactory = new SpringManagedTransactionFactory();
		}

		Environment environment = new Environment(this.environment, this.transactionFactory, this.dataSource);
		configuration.setEnvironment(environment);

		if (this.databaseIdProvider != null) {
			try {
				configuration.setDatabaseId(this.databaseIdProvider.getDatabaseId(this.dataSource));
			} catch (SQLException e) {
				throw new NestedIOException("Failed getting a databaseId", e);
			}
		}

		if (!isEmpty(this.mapperLocations)) {
			for (Resource mapperLocation : this.mapperLocations) {
				if (mapperLocation == null) {
					continue;
				}

				try {
					String baseXMLString = getBaseXMLString(mapperLocation.getInputStream());
					if (!StringUtils.isEmpty(baseXMLString)) {
						XPathParser xPathParser = new XPathParser(baseXMLString, true, configuration.getVariables(), new XMLMapperEntityResolver());
						BaseXMLMapperBuilder baseBuilder = new BaseXMLMapperBuilder(xPathParser, configuration, mapperLocation.toString(), configuration.getSqlFragments());
						baseBuilder.parse();
					} else {
						XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(), configuration, mapperLocation.toString(), configuration.getSqlFragments());
						xmlMapperBuilder.parse();
					}
				} catch (Exception e) {
					throw new NestedIOException("Failed to parse mapping resource: '" + mapperLocation + "'", e);
				} finally {
					ErrorContext.instance().reset();
				}

				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Parsed mapper file: '" + mapperLocation + "'");
				}
			}
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Property 'mapperLocations' was not specified or no matching resources found");
			}
		}

		return this.sqlSessionFactoryBuilder.build(configuration);
	}

	private String getBaseXMLString(InputStream inputStream) {
		Document doc = null;
		String entityFullName = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			builder.setEntityResolver(new IgnoreDTDEntityResolver());
			doc = builder.parse(inputStream);
			Element mapper = doc.getDocumentElement();
			entityFullName = getEntityFullName(mapper);
			if (!StringUtils.isEmpty(entityFullName)) {
				Class<?> entity = Class.forName(entityFullName);
				String tableName = getTableName(entity);
				if (!StringUtils.isEmpty(tableName)) {
					logger.debug("class " + entityFullName + " found, use generateXML init ");
					// resultMap
					mapper.appendChild(new ResultMapHandler(doc, "resultMap", entity).build());
					// column
					mapper.appendChild(new ColumnHandler(doc, "column", entity).build());
					// condition
					mapper.appendChild(new ConditionHandler(doc, "condition", entity).build());
					// delete
					mapper.appendChild(new DeleteHandler(doc, "delete", entity).build());
					// select
					mapper.appendChild(new SelectHandler(doc, "select", entity, "resultMap").build());
					// selectAll
					mapper.appendChild(new SelectAllHandler(doc, "selectAll", entity, "resultMap").build());
					// insert
					mapper.appendChild(new InsertHandler(doc, "insert", entity).build());
					// update
					mapper.appendChild(new UpdateHandler(doc, "update", entity).build());
					// selectByIds
					mapper.appendChild(new SelectByIdsHandler(doc, "selectByIds", entity, "resultMap").build());
					// deleteByIds
					mapper.appendChild(new DeleteByIdsHandler(doc, "deleteByIds", entity).build());
					// selectByEntity
					mapper.appendChild(new SelectByEntityHandler(doc, "selectByEntity", entity, "resultMap").build());
					// selectByPage
					mapper.appendChild(new SelectByPageHandler(doc, "selectByPage", entity, "resultMap").build());
					// selectByCondition
					mapper.appendChild(new SelectByConditionHandler(doc, "selectByCondition", entity, "resultMap").build());
					// updateByCondition
					mapper.appendChild(new UpdateByConditionHandler(doc, "updateByCondition", entity).build());
					// deleteByCondition
					mapper.appendChild(new DeleteByConditionHandler(doc, "deleteByCondition", entity).build());
				}
				return doc2String(doc);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return null;
	}

	private String getTableName(Class<?> entity) {
		if (entity.isAnnotationPresent(Table.class)) {
			return entity.getAnnotation(Table.class).name();
		}
		return null;
	}

	private String getEntityFullName(Element element) {
		String namespace = element.getAttribute("namespace");
		// 以配置包开头并以配置后缀结尾的解析，其他的不解析（为了兼容已有的）
		if (namespace.startsWith(daoBasePackage) && namespace.endsWith(daoSuffix)) {
			int lastIndexOf = namespace.lastIndexOf(".");
			String daoName = namespace.substring(lastIndexOf);
			String modelName = daoName.replace(daoSuffix, modelSuffix);
			return namespace.replaceFirst(daoBasePackage, modelBasePackage).replace(daoName, modelName);
		} else {
			return null;
		}
	}

	public String doc2String(Document doc) {
		try {
			Source source = new DOMSource(doc);
			StringWriter stringWriter = new StringWriter();
			Result result = new StreamResult(stringWriter);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doc.getDoctype().getPublicId());
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			return stringWriter.getBuffer().toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public SqlSessionFactory getObject() throws Exception {
		if (this.sqlSessionFactory == null) {
			afterPropertiesSet();
		}

		return this.sqlSessionFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<? extends SqlSessionFactory> getObjectType() {
		return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSingleton() {
		return true;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void onApplicationEvent(ApplicationEvent event) {
		if (failFast && event instanceof ContextRefreshedEvent) {
			// fail-fast -> check all statements are completed
			this.sqlSessionFactory.getConfiguration().getMappedStatementNames();
		}
	}

	class IgnoreDTDEntityResolver implements EntityResolver {
		public InputSource resolveEntity(String arg0, String arg1) throws SAXException, IOException {
			return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
		}
	}

}
