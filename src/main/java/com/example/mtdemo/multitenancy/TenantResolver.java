package com.example.mtdemo.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TenantResolver implements CurrentTenantIdentifierResolver {

  // @Value("${jwt.default-tenant-id}")
  public static String DEFAULT_TENANT_ID = "t0_mt";

  @Value("${jwt.tenant-suffix}")
  public static String TENANT_SUFFIX;

  @Override
  public String resolveCurrentTenantIdentifier() {
    String tenantId = null;
    tenantId = TenantContext.getTenant();
    if (tenantId != null) {
      // return String.format("%s%s", tenantId, TENANT_SUFFIX);
      return tenantId;
    }
    // return String.format("%s%s", DEFAULT_TENANT_ID, TENANT_SUFFIX);
    return DEFAULT_TENANT_ID;
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }

}
