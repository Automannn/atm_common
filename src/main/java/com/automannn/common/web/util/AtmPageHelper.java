package com.automannn.common.web.util;

import com.automannn.common.web.bean.Page;
import com.automannn.common.web.bean.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author automannn@163.com
 * @time 2020/5/4 21:19
 */
public class AtmPageHelper {
    public static void beginPage(PageParam pageParam){
        if (pageParam.getPageNumber() > 1) {
            PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize(), false);
        } else {
            PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize(), true);
        }
    }

    public static Page endPage(List list){
        Page page = new Page();
        PageInfo pageInfo = new PageInfo(list);
        page.setList(pageInfo.getList());
        page.setPageNum(pageInfo.getPageNum());
        page.setPages(pageInfo.getPages());
        page.setPageSize(pageInfo.getPageSize());
        page.setCurrentSize(pageInfo.getSize());
        page.setTotal(pageInfo.getTotal());
        return page;
    }
}
