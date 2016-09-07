package dnn.dao;

import dnn.dto.BaseDto;
import dnn.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IDao<Entity extends BaseEntity, Dto extends BaseDto> {

    /**
     * 查询所有数据
     *
     * @return
     */
    List<Entity> findAll();

    /**
     * 查询分页数据
     *
     * @param dto
     * @return
     */
    List<Entity> findByPage(Dto dto);

    /**
     * 查询数据量
     *
     * @return
     */
    Long count(Dto dto);

    /**
     * 查询某个对象
     *
     * @param id
     * @return
     */
    Entity findById(String id);

    /**
     * 保存对象
     *
     * @param entity
     */
    void save(Entity entity);

    /**
     * 保存对象列表
     * @param entities
     */
    void save(List<Entity> entities);

    /**
     * 通过id删除对象
     *
     * @param id
     */
    void remove(String id);

    /**
     * 删除对象
     *
     * @param entity
     */
    void remove(Entity entity);
    /**
     * 删除对象列表
     *
     * @param entities
     */
    void remove(List<Entity> entities);

    /**
     * 更新对象
     *
     * @param entity
     */
    void update(Entity entity);

    /**
     * 更新对象列表
     *
     * @param entities
     */
    void update(List<Entity> entities);

    /**
     * 根据字段条件查询对象列表
     *
     * @param conditions
     */
    public List<Entity> findByCis(Map<String, Object> conditions);

    /**
     * 根据字段条件查询对象列表数量
     *
     * @param conditions
     */
    long countByCis(Map<String, Object> conditions);


    /**
     * 模糊条件查询对象列表(只支持字段属性是字符串的查询)
     *
     * @param conditions
     */
    List<Entity> findByFuzzy(Map<String, Object> conditions);

    /**
     * 更新符合条件对象列表
     * @param entity
     * @param conditions
     */
    void UpdateByCis(Entity entity,Map<String, Object> conditions);

    /**
     * 删除符合条件对象列表
     * @param conditions
     */
    void removeByCis(Map<String, Object> conditions);

}
