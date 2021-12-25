package tech.jhipster.lite.generator.server.springboot.aop.logging.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface AopLoggingService {
  void init(Project project);

  void addMavenDependencies(Project project);
  void addJavaFiles(Project project);
  void addProperties(Project project);
}
