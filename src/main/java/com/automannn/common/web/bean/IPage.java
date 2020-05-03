package com.automannn.common.web.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:13
 */
public interface IPage<T> extends Serializable {
    Integer getPageNum();

    void setPageNum(Integer pageNum);

    Integer getPageSize();

    void setPageSize(Integer pageSize);

    Integer getCurrentSize();

    void setCurrentSize(Integer size);

    Long getTotal();

    void setTotal(Long total);

    Integer getPages();

    void setPages(Integer pages);

    List<T> getList();

    void setList(List<T> list);

    List<Integer> getPageIndexList();

    void setPageIndexList(List<Integer> pageIndexList);
}
