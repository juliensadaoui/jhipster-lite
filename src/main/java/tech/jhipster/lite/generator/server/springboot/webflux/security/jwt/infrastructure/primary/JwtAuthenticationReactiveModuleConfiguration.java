package tech.jhipster.lite.generator.server.springboot.webflux.security.jwt.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.webflux.security.jwt.application.JwtAuthenticationReactiveApplicationService;
import tech.jhipster.lite.module.domain.properties.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleResource;

@Configuration
class JwtAuthenticationReactiveModuleConfiguration {

  @Bean
  JHipsterModuleResource jwtSecurityModule(JwtAuthenticationReactiveApplicationService jwtSecurityReactive) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/reactive-servers/jwt")
      .slug("springboot-webflux-jwt")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().addProjectBaseName().build())
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - Webflux - Security", "Add Spring Security JWT with Spring Boot Webflux"))
      .tags("server", "spring", "spring-boot", "security", "jwt")
      .factory(jwtSecurityReactive::build);
  }
}
