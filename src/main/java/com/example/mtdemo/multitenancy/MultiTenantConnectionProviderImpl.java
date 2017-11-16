package com.example.mtdemo.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class MultiTenantConnectionProviderImpl.
 */
@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The data source. */
  @Autowired
  private DataSource dataSource;

  /*
   * (non-Javadoc)
   *
   * @see org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#getAnyConnection()
   */
  @Override
  public Connection getAnyConnection() throws SQLException {
    return this.dataSource.getConnection();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#getConnection(java.lang
   * .String)
   */
  @Override
  public Connection getConnection(final String tenantIdentifie) throws SQLException {
    String tenantId = TenantContext.getTenant();
    final Connection connection = this.getAnyConnection();
    try {
      if (tenantId != null) {
        connection.createStatement().execute("USE " + tenantId);
      } else {
        connection.createStatement().execute("USE " + TenantResolver.DEFAULT_TENANT_ID);
      }
    } catch (SQLException sqlException) {
      System.out.println("Problem with Schema Connection " + tenantId);
    }
    return connection;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.hibernate.service.spi.Wrapped#isUnwrappableAs(java.lang.Class)
   */
  @Override
  public boolean isUnwrappableAs(final Class unWrapable) {
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#releaseAnyConnection(
   * java.sql.Connection)
   */
  @Override
  public void releaseAnyConnection(final Connection connection) throws SQLException {
    connection.close();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#releaseConnection(java.
   * lang.String, java.sql.Connection)
   */
  @Override
  public void releaseConnection(final String tenantIdentifier, final Connection connection)
      throws SQLException {
    try {
      connection.createStatement().execute("USE " + TenantResolver.DEFAULT_TENANT_ID);
    } catch (SQLException e) {
      throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
    }
    connection.close();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider#
   * supportsAggressiveRelease()
   */
  @Override
  public boolean supportsAggressiveRelease() {
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.hibernate.service.spi.Wrapped#unwrap(java.lang.Class)
   */
  @Override
  public <T> T unwrap(final Class<T> unwrapType) {
    return null;
  }

}
