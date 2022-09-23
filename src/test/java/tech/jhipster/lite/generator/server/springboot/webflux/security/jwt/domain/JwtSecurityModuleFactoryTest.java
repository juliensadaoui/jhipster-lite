package tech.jhipster.lite.generator.server.springboot.webflux.security.jwt.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class JwtSecurityModuleFactoryTest {

  private static final JwtAuthenticationReactiveModuleFactory factory = new JwtAuthenticationReactiveModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .put("serverPort", 9000)
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .createFile("pom.xml")
      .containing(
        """
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-security</artifactId>
            </dependency>
        """
      )
      .containing(
        """
              <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt-api</artifactId>
                <version>${jjwt.version}</version>
              </dependency>
          """
      )
      .containing(
        """
            <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-impl</artifactId>
              <version>${jjwt.version}</version>
              <scope>runtime</scope>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt-jackson</artifactId>
              <version>${jjwt.version}</version>
            </dependency>
        """
      )
      .containing(
        """
            <dependency>
              <groupId>org.zalando</groupId>
              <artifactId>problem-spring-webflux</artifactId>
              <version>${problem-spring.version}</version>
            </dependency>
        """
      )
      .and()
      .createFile("src/main/resources/config/application.properties")
      .containing("application.security.authentication.jwt.base64-secret=")
      .containing("application.security.authentication.jwt.token-validity-in-seconds=86400")
      .containing("application.security.authentication.jwt.token-validity-in-seconds-for-remember-me=2592000")
      .and()
      .createPrefixedFiles(
        "src/main/java/com/jhipster/test/technical/infrastructure/primary/exception/",
        "ProblemConfiguration.java",
        "HeaderUtil.java",
        "BadRequestAlertException.java",
        "ErrorConstants.java",
        "ExceptionTranslator.java",
        "FieldErrorDTO.java"
      )
      .createPrefixedFiles(
        "src/test/java/com/jhipster/test/technical/infrastructure/primary/exception/",
        "HeaderUtilTest.java",
        "BadRequestAlertExceptionTest.java",
        "ExceptionTranslatorIT.java",
        "ExceptionTranslatorTestController.java",
        "FieldErrorDTOTest.java"
      )
      .createFiles("src/test/java/com/jhipster/test/TestUtil.java");
  }
}
