package com.florence.dev.utils.web;

/**
 * @author wuyanzhen
 */
public class PageUtils {

    private static final int PAGE_SIZE = 10;

    private static int getPage(Integer page) {
        if (page == null) {
            page = 1;
        }

        if (page <= 0) {
            page = 1;
        }
        return page;
    }

    public static int getPageStartIndex(Integer page) {
        page = getPage(page);
        return (page - 1) * PAGE_SIZE;
    }

    public static int getPageEndIndex(Integer page) {
        int pageStartIndex = getPageStartIndex(page);
        return pageStartIndex + PAGE_SIZE;
    }

    public static int getPageStartIndex(Integer page, Integer pageSize) {
        page = getPage(page);
        return (page - 1) * pageSize;
    }

    public static int getPageSize(Integer pageSize, int defaultSize) {
        if (pageSize == null || pageSize < 0) {
            return defaultSize;
        }
        return pageSize;
    }

    public static int getTotalPage(int totalCount, int pageSize) {
        int totalPage = totalCount / pageSize;
        totalPage += totalCount % pageSize == 0 ? 0 : 1;
        return totalPage;
    }
}
