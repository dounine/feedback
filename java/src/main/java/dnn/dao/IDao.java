package dnn.dao;

import dnn.entity.BaseEntity;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IDao<Entity extends BaseEntity> {

    Entity find(Entity entity);

}
