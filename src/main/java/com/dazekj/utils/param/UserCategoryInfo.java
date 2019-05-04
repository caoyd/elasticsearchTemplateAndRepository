package com.dazekj.utils.param;

public class UserCategoryInfo {

    private SubUserCategoryInfo[] subCategoryList;

    /**
     * @return 111
     */
    public SubUserCategoryInfo[] getSubCategoryList() {
        return subCategoryList;
    }

    /**
     * 设置111     *
     * 参数示例：<pre>1</pre>     
     * 此参数必填
     */
    public void setSubCategoryList(SubUserCategoryInfo[] subCategoryList) {
        this.subCategoryList = subCategoryList;
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
     * 参数示例：<pre>http://</pre>     
     * 此参数必填
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    private Long id;

    /**
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置Id     *
     * 参数示例：<pre>100</pre>     
     * 此参数必填
     */
    public void setId(Long id) {
        this.id = id;
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
     * 参数示例：<pre>自定义1	</pre>     
     * 此参数必填
     */
    public void setName(String name) {
        this.name = name;
    }

    private Integer ordering;

    /**
     * @return 排序Id
     */
    public Integer getOrdering() {
        return ordering;
    }

    /**
     * 设置排序Id     *
     * 参数示例：<pre>1</pre>     
     * 此参数必填
     */
    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

}
