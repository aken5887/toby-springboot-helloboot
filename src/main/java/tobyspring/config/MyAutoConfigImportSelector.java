package tobyspring.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
  private final ClassLoader classLoader;

  public MyAutoConfigImportSelector(ClassLoader classLoader){
    this.classLoader = classLoader;
  }

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    List<String> autoConfigs = new ArrayList<>();
    ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(config -> {
      System.out.println(config);
      autoConfigs.add(config);
    });
    return autoConfigs.toArray(String[]::new);
  }
}
