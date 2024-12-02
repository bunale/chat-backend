package com.chat.backend.common;

import lombok.Data;

/**
 * 分页查询参数
 *
 * @author liujie
 * @since 2024/11/29
 */
@Data
public class PageParam {
    /**
     * 页码。
     */
    private long pageNum;

    /**
     * 每页数据数量。
     */
    private long pageSize;
}
