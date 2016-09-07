package dnn.service;

import dnn.dto.BaseDto;
import dnn.common.exception.SerException;
import dnn.dao.IDao;
import dnn.dto.SearchJson;
import dnn.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public class ServiceImpl<Entity extends BaseEntity, Dto extends BaseDto> implements IService<Entity, Dto> {

    @Autowired
    protected IDao<Entity, Dto> dao;

    @Override
    public List<Entity> findAll() throws SerException {
        return dao.findAll();
    }

    @Override
    public List<Entity> findByPage(Dto dto) throws SerException {
        Query query = new Query();
        query = init_sort(query, dto);
        query = init_search(query, dto);
        query.skip(dto.getSkip());
        query.limit(dto.getLimit());
        return dao.findByPage(query);
    }

    @Override
    public Entity findOne(Map<String, Object> conditions) throws SerException {
        return dao.findOne(conditions);
    }

    @Override
    public List<Entity> findByCriteria(Criteria criteria) throws SerException {
        return dao.findByCriteria(criteria);
    }

    @Override
    public Long count(Dto dto) throws SerException {
        Query query = new Query();
        query = init_search(query, dto);
        return dao.count(query);
    }

    @Override
    public Entity findById(String id) throws SerException {
        return dao.findById(id);
    }

    @Override
    public List<Entity> findByIn(String field, List<String> values) throws SerException {
        return dao.findByIn(field, values);
    }

    @Override
    public void save(Entity entity) throws SerException {
        dao.save(entity);
    }

    @Override
    public void save(List<Entity> entities) throws SerException {
        dao.saveAll(entities);
    }

    @Override
    public void remove(String id) throws SerException {
        dao.remove(id);
    }

    @Override
    public void remove(Entity entity) throws SerException {
        dao.remove(entity);
    }

    @Override
    public void update(Entity entity) throws SerException {
        dao.update(entity);
    }

    @Override
    public List<Entity> findByCis(Map<String, Object> conditions) throws SerException {
        return dao.findByCis(conditions);
    }

    @Override
    public List<Entity> findByFuzzy(Map<String, Object> conditions) throws SerException {
        return dao.findByFuzzy(conditions);
    }

    @Override
    public long countByCis(Map<String, Object> conditions) throws SerException {
        return dao.countByCis(conditions);
    }

    @Override
    public void UpdateByCis(Entity entity, Map<String, Object> conditions) throws SerException {
        dao.UpdateByCis(entity, conditions);
    }

    @Override
    public void removeByCis(Map<String, Object> conditions) throws SerException {
        dao.removeByCis(conditions);
    }

    private Query init_search(Query query, Dto dto) {

        List<SearchJson> searchJsons = dto.getSearchJsons();
        Stream<SearchJson> stream = searchJsons.stream();
        stream.forEach(model -> {
            switch (model.getSearchName()) {
                case EQ:
                    break;
                case BETWEEN:
                    break;
                case LIKE:
                    break;
                case IN:
                    break;
                case GT:
                    break;
                case LT:
                    break;
                case OR:
                    break;
                case NE:
                    break;
            }

        });

        if (StringUtils.isNotBlank(dto.getSearch())) {
            String searchName = "";
            String searchField = "";
            String searchValue = "";
            switch (searchName) {
                case "LIKES":
                    query.addCriteria(Criteria.where(searchField).regex(searchValue));
                    break;
                case "BETWEEN":
                    //field:value,value
                    Criteria criteria = new Criteria();
                    criteria.where(searchField).gt(searchValue).lt(searchValue);
                    query.addCriteria(criteria);
                    break;
                default:
                    break;
            }
        }
        return query;
    }

    private Query init_sort(Query query, Dto dto) {
        if (null != dto.getSort() && dto.getSort().size() > 0) {
            Sort.Direction direction = Sort.Direction.DESC;
            if (dto.getOrder().equals("asc")) {
                direction = Sort.Direction.ASC;
            }
            query.with(new Sort(direction, dto.getSort()));
        }
        return query;
    }
}
