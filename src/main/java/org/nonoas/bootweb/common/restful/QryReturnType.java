package org.nonoas.bootweb.common.restful;

import java.util.List;

/**
 * 查询通用返回类
 *
 * @author : Nonoas
 * @time : 2021-04-19 16:42
 */
public class QryReturnType extends BaseReturnType {

    /**
     * 查询结果列
     */
    private List<?> rows;
    /**
     * 总数
     */
    private int totalCount;

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
