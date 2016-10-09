package dnn.common.datasource;

import dnn.common.beans.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 16/4/29.
 */
@Component
public class MySqlDbSource {

    @Autowired
    protected PropertiesLoader propertiesLoader;

    @Bean
    public PropertiesLoader propertiesLoader(){
        return new PropertiesLoader("config.properties");
    }

}
