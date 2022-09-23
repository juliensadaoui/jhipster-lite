package tech.jhipster.lite.generator.server.springboot.webflux.security.jwt.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.javaDependency;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainJava;
import static tech.jhipster.lite.module.domain.JHipsterModule.versionSlug;
import static tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope.RUNTIME;

import java.util.List;
import tech.jhipster.lite.common.domain.Base64Utils;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterDestination;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.LogLevel;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.javaproperties.PropertyKey;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class JwtAuthenticationReactiveModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/webflux/security/jwt/authentication");

  private static final GroupId JJWT_GROUP = groupId("io.jsonwebtoken");
  private static final VersionSlug JJWT_VERSION = versionSlug("jjwt");

  private static final String AUTHENTICATION_PRIMARY = "authentication/infrastructure/primary";

  private static final String SPRING_SECURITY_PACKAGE = "org.springframework.security";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();
    String applicationName = properties.projectBaseName().capitalized();
    //JHipsterDestination destination = toSrcMainJava().append(properties.packagePath()).append("security/jwt/infrastructure/config");

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("applicationName", applicationName)
        .and()
      //.documentation(documentationTitle("Jwt Security"), source.template("cucumber.md"))
      .springMainProperties()
        .set(new PropertyKey("application.security.authentication.jwt.base64-secret"), new PropertyValue(List.of(Base64Utils.getBase64Secret())))
        .set(new PropertyKey("application.security.authentication.jwt.token-validity-in-seconds"), new PropertyValue(List.of("86400")))
        .set(new PropertyKey("application.security.authentication.jwt.token-validity-in-seconds-for-remember-me"), new PropertyValue(List.of("2592000")))
        .and()
      .files()
        .batch(SOURCE.append("main/infrastructure/primary"), toSrcMainJava().append(packagePath).append(AUTHENTICATION_PRIMARY))
          .addTemplate("ApplicationSecurityDefaults.java")
          .addTemplate("ApplicationSecurityProperties.java")
          .addTemplate("JwtTokenFilter.java")
          .addTemplate("SecurityConfiguration.java")
          .addTemplate("TokenProvider.java")
          .and()
        //.add(source.template("AuthoritiesConstants.java"), toSrcMainJava().append(packagePath).append("security/jwt/domain/AuthoritiesConstants.java"))
        //.add(source.template("SecurityUtils.java"), toSrcMainJava().append(packagePath).append("security/jwt/application/SecurityUtils.java"))
        .and()
      .javaDependencies()
        .addDependency(springBootSecurityDependency())
        .addDependency(jjwtApiDependency())
        .addDependency(jjwtImplDependency())
        .addDependency(jjwtJacksonDependency())
        .addDependency(problemSpringWebfluxDependency())
        .and()
      .springMainLogger(SPRING_SECURITY_PACKAGE, LogLevel.WARN)
      .springTestLogger(SPRING_SECURITY_PACKAGE, LogLevel.WARN)
      .build();
    //@formatter:on
  }

  private JavaDependency springBootSecurityDependency() {
    return javaDependency().groupId("org.springframework.boot").artifactId("spring-boot-starter-security").build();
  }

  private JavaDependency jjwtApiDependency() {
    return javaDependency().groupId(JJWT_GROUP).artifactId("jjwt-api").versionSlug(JJWT_VERSION).build();
  }

  private JavaDependency jjwtImplDependency() {
    return javaDependency().groupId(JJWT_GROUP).artifactId("jjwt-impl").versionSlug(JJWT_VERSION).scope(RUNTIME).build();
  }

  private JavaDependency jjwtJacksonDependency() {
    return javaDependency()
      .groupId(JJWT_GROUP)
      .artifactId("jjwt-jackson")
      .versionSlug(JJWT_VERSION)
      .scope(JavaDependencyScope.RUNTIME)
      .build();
  }

  private JavaDependency problemSpringWebfluxDependency() {
    return javaDependency().groupId("org.zalando").artifactId("problem-spring-webflux").versionSlug("problem-spring.version").build();
  }
}
