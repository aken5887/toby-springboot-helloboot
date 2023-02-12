package tobyspring.config;
// 자동구성 클래스를 동적으로 로딩

import java.util.logging.Logger;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    MultiValueMap<String, Object> attr = importingClassMetadata.getAllAnnotationAttributes(
        EnableMyConfigurationProperties.class.getName());

    Class propertyClass = (Class) attr.getFirst("value");

    logger.info(propertyClass.getName());
    return new String[]{propertyClass.getName()};
  }
}
