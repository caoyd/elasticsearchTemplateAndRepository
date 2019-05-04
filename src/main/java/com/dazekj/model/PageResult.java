package com.dazekj.model;



import java.util.List;


/**
 * @CliassName: Result
 * @Author: DZ5343
 * @Date: 2019/4/15 11:56
 * @Description: 封装分页结果集
 */
public class PageResult {

    //总条码
    private Integer totalCount;
    //页码
    private Integer pageIndex;
    //分页大小
    private Integer pageSize;
    //总页数
    private Integer totalPages;
    //返回的结果集
    private List list;

    public PageResult() {
    }


    public PageResult(Integer totalCount, Integer pageIndex, Integer pageSize, Integer totalPages, List<Object> list) {
        this.totalCount = totalCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalCount=" + totalCount +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", list=" + list +
                '}';
    }
}
