package com.example.mtdemo.multitenancy;

/**
 * The Class TenantContext.
 */
public class TenantContext {

  /** The tenant. */
  private static ThreadLocal<String> tenant = new ThreadLocal<>();

  /**
   * Sets the tenant.
   *
   * @param tenantId the new tenant
   */
  public static void setTenant(final String tenantId) {
    System.out.println("Current Tenant Set to:" + tenantId);
    tenant.set(tenantId);
    // tenant.set(String.format("%s%s", tenantId, TenantResolver.TENANT_SUFFIX));
  }

  /**
   * Gets the tenant.
   *
   * @return the tenant
   */
  public static String getTenant() {
    return tenant.get();
  }

  /**
   * Clear.
   */
  public static void clear() {
    // tenant.remove();
    setTenant(TenantResolver.DEFAULT_TENANT_ID);
  }
}
