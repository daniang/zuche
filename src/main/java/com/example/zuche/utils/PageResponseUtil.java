package com.example.zuche.utils;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/1/19 11:07
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.zuche.utils.PageResponse.PageInfo;

import java.util.ArrayList;

public class PageResponseUtil {
    public static <T> PageResponse<T> getPageResponse(IPage<T> page) {
        if (page == null) {
            return null;
        } else {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(page.getCurrent());
            pageInfo.setSize(page.getSize());
            pageInfo.setTotal(page.getTotal());
            pageInfo.setTotalPage(page.getPages());
            PageResponse<T> response = new PageResponse();
            response.setPageData(page.getRecords());
            response.setPageInfo(pageInfo);
            return response;
        }
    }

    public static <T> PageResponse<T> getPageResponse(IPage<T> page, String info, Integer status) {
        PageInfo pageInfo;
        PageResponse response;
        if (page == null) {
            pageInfo = new PageInfo();
            response = new PageResponse();
            response.setPageInfo(pageInfo);
            response.setPageData(new ArrayList());
            response.setInfo(info);
            response.setStatus(status);
            return response;
        } else {
            pageInfo = new PageInfo();
            pageInfo.setPage(page.getCurrent());
            pageInfo.setSize(page.getSize());
            pageInfo.setTotal(page.getTotal());
            pageInfo.setTotalPage(page.getPages());
            response = new PageResponse();
            response.setPageData(page.getRecords());
            response.setPageInfo(pageInfo);
            response.setInfo(info);
            response.setStatus(status);
            return response;
        }
    }

    public PageResponseUtil() {
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResponseUtil)) {
            return false;
        } else {
            PageResponseUtil other = (PageResponseUtil) o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageResponseUtil;
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "PageResponseUtil()";
    }
}

