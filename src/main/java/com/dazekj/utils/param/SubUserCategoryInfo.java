package com.dazekj.utils.param;

public class SubUserCategoryInfo {

    private Long id;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID     *
     * 参数示例：<pre>1001</pre>     
     * 此参数必填
     */
    public void setId(Long id) {
        this.id = id;
    }

    private Long pid;

    /**
     * @return 父自定义分类ID
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父自定义分类ID     *
     * 参数示例：<pre>100</pre>     
     * 此参数必填
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    private String name;

    /**
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称     *
     * 参数示例：<pre>自定义1</pre>     
     * 此参数必填
     */
    public void setName(String name) {
        this.name = name;
    }

    private String iconUrl;

    /**
     * @return 图标
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置图标     *
     * 参数示例：<pre>http://***.png</pre>     
     * 此参数必填
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    private Integer sorting;

    /**
     * @return 排序
     */
    public Integer getSorting() {
        return sorting;
    }

    /**
     * 设置排序     *
     * 参数示例：<pre>1</pre>     
     * 此参数必填
     */
    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

}
