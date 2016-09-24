package dnn.common.beans;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * Created by huanghuanlai on 16/8/17.
 */
@Component
public class Components{

    private PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");

    @Bean
    public DruidDataSource getDruid(){
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(propertiesLoader.getProperty("db.driver"));
        dds.setUrl(propertiesLoader.getProperty("db.url"));
        dds.setUsername(propertiesLoader.getProperty("db.username"));
        dds.setPassword(propertiesLoader.getProperty("db.password"));

        return dds;
    }

    /**
     * 实体管理器
     * @return
     */
    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean getLCEMF(DruidDataSource druidDataSource){
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setDataSource(druidDataSource);
        lcemf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        lcemf.setPackagesToScan("dnn.entity");

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        jpaProperties.setProperty("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
        jpaProperties.setProperty("hibernate.show_sql","true");
        jpaProperties.setProperty("hibernate.format_sql","true");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto","update");

        lcemf.setJpaProperties(jpaProperties);

        return lcemf;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean){
        return localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory();
    }

    /**
     * 事务管理器
     * @return
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
