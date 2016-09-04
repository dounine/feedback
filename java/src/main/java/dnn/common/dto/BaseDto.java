package dnn.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lgq on 16-9-2.
 */
public class BaseDto extends PageDto implements Serializable {
    private static final long serialVersionUID = -3558525794931360478L;

    private Boolean findAll =Boolean.FALSE;
    private List<String> sort ;
    private String search;
    private String order="desc";

    public void findAll() {
        this.findAll =true;
    }

    public void setFindAll(Boolean findAll) {
        this.findAll = findAll;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
