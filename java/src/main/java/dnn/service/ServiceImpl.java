package dnn.service;

import dnn.dao.IDao;
import dnn.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@Service
public class ServiceImpl<Entity extends BaseEntity> implements IService<Entity>{

    @Autowired
    protected IDao<Entity> dao;

    @Override
    public Entity find(Entity entity) {
        return dao.find(entity);
    }
}
