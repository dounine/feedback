package dnn.dao;

import dnn.common.exception.SerException;
import dnn.dto.BaseDto;
import dnn.common.utils.GenericsUtils;
import dnn.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public class DaoImpl<Entity extends BaseEntity, Dto extends BaseDto> implements IDao<Entity, Dto> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected Class<Entity> clazz;

    public DaoImpl() {
        clazz = GenericsUtils.getSuperClassGenricType(this.getClass());
    }

    @Override
    public List<Entity> findAll() {
        return mongoTemplate.findAll(clazz);
    }

    @Override
    public List<Entity> findByPage(Query query) {
        return mongoTemplate.find(query, clazz);
    }


    @Override
    public Long count(Query query) {
        return mongoTemplate.count(query, clazz);
    }


    @Override
    public Entity findOne(Map<String, Object> conditions) {
        Query query = new Query();
        if (null != conditions && conditions.size() > 0) {
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }
        return mongoTemplate.findOne(query, clazz);
    }

    @Override
    public List<Entity> findByCriteria(Criteria criteria) {
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.find(query, clazz);
    }


    @Override
    public Entity findById(String id) {
        return mongoTemplate.findById(id, clazz);
    }

    @Override
    public List<Entity> findByIn(String field, List<String> values) {
        return mongoTemplate.find(new Query().addCriteria(new Criteria().where(field).in(values)), clazz);
    }

    @Override
    public void save(Entity entity) {

        mongoTemplate.insert(entity);
    }

    @Override
    public void save(List<Entity> entities) {
        mongoTemplate.insert(entities, clazz);
    }

    @Override
    public void remove(String id) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), clazz);
    }

    @Override
    public void remove(Entity entity) {
        mongoTemplate.remove(entity);
    }

    @Override
    public void remove(List<Entity> entities) {
        Stream<Entity> stream = entities.stream();
        stream.forEach(entity -> {
            mongoTemplate.remove(entity);
        });
    }

    @Override
    public void update(Entity entity) {
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(entity.getId())),
                Update.update(clazz.getSimpleName(), entity), clazz);
    }

    @Override
    public void update(List<Entity> entities) {
        Stream<Entity> stream = entities.stream();
        stream.forEach(entity -> {
            mongoTemplate.updateFirst(new Query(Criteria.where("id").is(entity.getId())),
                    Update.update(clazz.getSimpleName(), entity), clazz);
        });

    }

    @Override
    public List<Entity> findByCis(Map<String, Object> conditions) {
        Query query = new Query();
        if (null != conditions && conditions.size() > 0) {
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }
        return mongoTemplate.find(query, clazz);
    }

    @Override
    public long countByCis(Map<String, Object> conditions) {
        Query query = new Query();
        if (null != conditions && conditions.size() > 0) {
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }
        return mongoTemplate.count(query, clazz);
    }

    @Override
    public List<Entity> findByFuzzy(Map<String, Object> conditions) {
        Query query = new Query();
        if (null != conditions && conditions.size() > 0) {
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).regex(entry.getValue().toString()));
            }
        }
        return mongoTemplate.find(query, clazz);
    }

    @Override
    public void UpdateByCis(Entity entity, Map<String, Object> conditions) {
        Query query = new Query();
        if (null != conditions && conditions.size() > 0) {
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }
        mongoTemplate.updateMulti(query,
                Update.update(clazz.getSimpleName(), entity), clazz);
    }

    @Override
    public void removeByCis(Map<String, Object> conditions) {

        Query query = new Query();
        if (null != conditions && conditions.size() > 0) {
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }
        mongoTemplate.remove(query, clazz);
    }

}
