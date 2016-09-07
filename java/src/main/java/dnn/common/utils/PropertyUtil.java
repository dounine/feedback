package dnn.common.utils;

import dnn.common.beans.PropertiesLoader;

/**
 * Created by lgq on 16-9-6.
 */
public class PropertyUtil {
    private static PropertiesLoader propertiesLoader=null;

    private PropertyUtil(){}

    public static PropertiesLoader getInstance() {
        if(null==propertiesLoader) {
            synchronized(PropertyUtil.class) {
                propertiesLoader = new PropertiesLoader("config.properties");
            }
        }
        return propertiesLoader;
    }
}
