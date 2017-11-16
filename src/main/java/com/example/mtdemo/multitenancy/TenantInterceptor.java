package com.example.mtdemo.multitenancy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

  @Value("${jwt.tenantHeader}")
  private String tenantHeader;

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler) throws Exception {
    String token = request.getHeader(this.tenantHeader);
    TenantContext.setTenant(token);
    return true;
  }

  @Override
  public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final ModelAndView modelAndView) throws Exception {
    TenantContext.clear();
  }

  public String getTenantId(final String token) {
    String tenantId = "";
    return tenantId;
  }
}
