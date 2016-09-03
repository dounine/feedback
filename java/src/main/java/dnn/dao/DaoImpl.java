package dnn.dao;

import dnn.common.utils.GenericsUtils;
import dnn.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public class DaoImpl<Entity extends BaseEntity> implements IDao<Entity> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected Class<Entity> clazz;

    public DaoImpl() {
        clazz = GenericsUtils.getSuperClassGenricType(this.getClass());
    }

    public Entity find(Entity entity){
        System.out.println(entity.getId());
        return entity;
    }
}
