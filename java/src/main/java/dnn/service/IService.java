package dnn.service;

import dnn.entity.BaseEntity;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IService<Entity extends BaseEntity> {

    Entity find(Entity entity);

}
