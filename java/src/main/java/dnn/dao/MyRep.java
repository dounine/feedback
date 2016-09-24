package dnn.dao;

import dnn.dto.BaseDto;
import dnn.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface MyRep<BE extends BaseEntity,BD extends BaseDto> extends JpaRepository<BE,String>{

    default BE findById(String id){
        return findOne(id);
    }
}
