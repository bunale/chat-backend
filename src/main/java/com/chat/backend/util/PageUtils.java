package com.chat.backend.util;

import com.mybatisflex.core.paginate.Page;

import java.util.List;

/**
 * 分页工具类
 *
 * @author liujie
 * @since 2024/12/2
 */
public class PageUtils {

    public static <T> Page<T> of(Page<?> rawPage, List<T> records) {
        Page<T> result = new Page<>();
        result.setRecords(records);
        result.setTotalPage(rawPage.getTotalPage());
        result.setTotalRow(rawPage.getTotalRow());
        result.setPageNumber(rawPage.getPageNumber());
        result.setPageSize(rawPage.getPageSize());
        return result;
    }
}
