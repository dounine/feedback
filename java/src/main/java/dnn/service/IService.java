package dnn.service;

import dnn.common.exception.SerException;
import dnn.dto.BaseDto;
import dnn.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IService<BE extends BaseEntity, BD extends BaseDto> {

    /**
     * 查询所有数据
     *
     * @return
     */
    List<BE> findAll() throws SerException;

    /**
     * 查询分页数据
     *
     * @param dto
     * @return
     */
    List<BE> findByPage(BD dto) throws SerException;


    /**
     * 查询第一个对象
     *
     * @param conditions
     * @return
     */
    BE findOne(Map<String, Object> conditions) throws SerException;


    /**
     * 查询数据量
     *
     * @param dto
     * @return
     */
    Long count(BD dto) throws SerException;

    /**
     * 查询某个对象
     *
     * @param id
     * @return
     */
    BE findById(String id) throws SerException;


    /**
     * 保存对象
     *
     * @param entity
     */
    BE save(BE entity) throws SerException;

    /**
     * 保存对象列表
     *
     * @param entities
     */
    void save(List<BE> entities) throws SerException;

    /**
     * 通过id删除对象
     *
     * @param id
     */
    void remove(String id) throws SerException;

    /**
     * 删除对象
     *
     * @param entity
     */
    void remove(BE entity) throws SerException;

    /**
     * 删除对象列表
     *
     * @param entities
     */
    void remove(List<BE> entities) throws SerException;


    /**
     * 更新对象
     *
     * @param entity
     */
    void update(BE entity) throws SerException;


    boolean exists(String id) throws SerException;

}
