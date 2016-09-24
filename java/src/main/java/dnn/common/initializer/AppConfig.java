package dnn.common.initializer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { Constant.SCAN_APP_PACKAGES },excludeFilters = {@ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        value = {Configuration.class})})
@EnableJpaRepositories(basePackages = "dnn.dao")
@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig {

}
