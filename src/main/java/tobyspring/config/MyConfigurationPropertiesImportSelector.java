package tobyspring.config;
// 자동구성 클래스를 동적으로 로딩

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    MultiValueMap<String, Object> attr = importingClassMetadata.getAllAnnotationAttributes(
        EnableMyConfigurationProperties.class.getName());

    Class propertyClass = (Class) attr.getFirst("value");

    return new String[]{propertyClass.getName()};
  }
}
