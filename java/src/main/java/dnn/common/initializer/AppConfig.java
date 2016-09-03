package dnn.common.initializer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = { Constant.SCAN_APP_PACKAGES },excludeFilters = {@ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        value = {Configuration.class})})
public class AppConfig {

    //这里是测试标签版本
}
