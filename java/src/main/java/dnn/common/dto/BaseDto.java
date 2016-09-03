package dnn.common.dto;

import java.io.Serializable;

/**
 * Created by lgq on 16-9-2.
 */
public class BaseDto extends PageDto implements Serializable {
    private static final long serialVersionUID = -3558525794931360478L;

    private Boolean findAll =Boolean.FALSE;
    private String sort ;
    private String search;

    public void findAll() {
        this.findAll =true;
    }

    public void setFindAll(Boolean findAll) {
        this.findAll = findAll;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
