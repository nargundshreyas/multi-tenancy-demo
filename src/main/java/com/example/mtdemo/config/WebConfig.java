package com.example.mtdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The Class WebConfig.
 * 
 * @author shreyasn
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  /** The tenant interceptor. */
  @Autowired
  private HandlerInterceptor tenantInterceptor;

  /*
   * (non-Javadoc)
   *
   * @see
   * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.
   * springframework.web.servlet.config.annotation.InterceptorRegistry)
   */
  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(this.tenantInterceptor);
  }
}
