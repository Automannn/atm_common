package com.automannn.common.web.bean;

import java.util.List;

/**
 * @author automannn@163.com
 * @time 2020/5/3 17:14
 */
public class Page<T> implements IPage<T> {

    private static final long serialVersionUID = 1L;
    protected List<T> list;
    private Integer pageNum;
    private Integer pageSize;
    private Integer currentSize;
    private Long total;
    private Integer pages;
    private List<Integer> pageIndexList;

    public Page(List<T> list) {
        this.list = list;
    }

    public Page() {
    }

    public List<Integer> getPageIndexList() {
        return this.pageIndexList;
    }

    public void setPageIndexList(List<Integer> pageIndexList) {
        this.pageIndexList = pageIndexList;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentSize() {
        return this.currentSize;
    }

    public void setCurrentSize(Integer size) {
        this.currentSize = size;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return this.pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
