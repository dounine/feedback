package dnn.common.datasource;

import com.mongodb.MongoClient;
import dnn.common.beans.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 16/4/29.
 */
@Component
public class MongoDbSource {

    @Autowired
    protected PropertiesLoader propertiesLoader;

    @Bean
    public PropertiesLoader propertiesLoader(){
        return new PropertiesLoader("config.properties");
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception{
        //UserCredentials userCredentials = new UserCredentials("lake", "lake");
        return new SimpleMongoDbFactory(new MongoClient(propertiesLoader.getProperty("mongo.host"),propertiesLoader.getInteger("mongo.port")), propertiesLoader.getProperty("mongo.db"));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception{
        return new MongoTemplate(mongoDbFactory());
    }
}
