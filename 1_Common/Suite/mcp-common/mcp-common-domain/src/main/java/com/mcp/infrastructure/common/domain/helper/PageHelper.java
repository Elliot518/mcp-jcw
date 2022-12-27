package com.mcp.infrastructure.common.domain.helper;

import com.mcp.infrastructure.common.domain.pagination.PageView;

/**
 * @Author: wwc
 */
public class PageHelper {
    public static PageView getResultPageView(PageView pageView, int totalRecords) {
        PageView resultPageView = new PageView();
        if (pageView != null) {
            resultPageView.setPageIndex(pageView.getPageIndex());
            resultPageView.setPageSize(pageView.getPageSize());
        }
        resultPageView.setTotalRecords(totalRecords);
        return resultPageView;
    }
}
