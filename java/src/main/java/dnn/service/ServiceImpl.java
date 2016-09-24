package dnn.service;

import dnn.common.constant.FinalCommons;
import dnn.common.exception.SerException;
import dnn.dao.MyRep;
import dnn.dto.BaseDto;
import dnn.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public class ServiceImpl<BE extends BaseEntity, BD extends BaseDto> extends FinalCommons implements IService<BE, BD> {

    private static final Logger CONSOLE = LoggerFactory.getLogger(ServiceImpl.class);

    @Autowired
    protected MyRep<BE, BD> myRepository;

    @Override
    public List<BE> findAll() throws SerException {
        return myRepository.findAll();
    }

    @Override
    public List<BE> findByPage(BD dto) throws SerException {
        Pageable pageable = new PageRequest(dto.getPage(),dto.getLimit());
        return myRepository.findAll(pageable).getContent();
    }

    @Override
    public BE findOne(Map<String, Object> conditions) throws SerException {
        return null;
    }

    @Override
    public Long count(BD dto) throws SerException {
        return myRepository.count();
    }

    @Override
    public BE findById(String id) throws SerException {
        return myRepository.findById(id);
    }

    @Override
    public BE save(BE entity) throws SerException {
        return myRepository.save(entity);
    }

    @Override
    public void save(List<BE> entities) throws SerException {
        myRepository.save(entities);
    }

    @Override
    public void remove(String id) throws SerException {
        myRepository.delete(id);
    }

    @Override
    public void remove(BE entity) throws SerException {
        myRepository.delete(entity);
    }

    @Override
    public void remove(List<BE> entities) {
        myRepository.deleteInBatch(entities);
    }

    @Override
    public void update(BE entity) throws SerException {
        myRepository.saveAndFlush(entity);
    }
}
