package tech.jhipster.lite.generator.server.springboot.webflux.security.jwt.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.webflux.security.jwt.domain.JwtAuthenticationReactiveModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class JwtAuthenticationReactiveApplicationService {

  private final JwtAuthenticationReactiveModuleFactory factory;

  public JwtAuthenticationReactiveApplicationService() {
    factory = new JwtAuthenticationReactiveModuleFactory();
  }

  public JHipsterModule build(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
