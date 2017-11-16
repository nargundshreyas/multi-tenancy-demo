package com.example.mtdemo.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class HibernateProperties.
 *
 * @author shreyasn
 */
@Configuration
public class HibernateConfig {

  /** The properties. */
  @Autowired
  private JpaProperties properties;

  /**
   * Jpa vendor adapter.
   *
   * @return the jpa vendor adapter
   */
  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    return new HibernateJpaVendorAdapter();
  }



  /**
   * Entitiy manager factory.
   *
   * @param dataSource the data source
   * @param connectionProvider the connection provider
   * @param resolver the resolver
   * @return the local container entity manager factory bean
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
      final MultiTenantConnectionProvider connectionProvider,
      final CurrentTenantIdentifierResolver resolver) {
    Map<String, Object> properties = new HashMap<>();
    properties.putAll(this.properties.getHibernateProperties(dataSource));
    properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
    properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
    properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, resolver);
    properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setPackagesToScan("com.example.mtdemo");
    em.setJpaVendorAdapter(this.jpaVendorAdapter());
    em.setJpaPropertyMap(properties);
    return em;
  }
}
