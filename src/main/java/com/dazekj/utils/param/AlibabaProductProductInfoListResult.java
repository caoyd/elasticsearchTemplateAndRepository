package com.dazekj.utils.param;

public class AlibabaProductProductInfoListResult {

    private AlibabaProductPageResult pageResult;

    /**
     * @return 查询结果
     */
    public AlibabaProductPageResult getPageResult() {
        return pageResult;
    }

    /**
     * 设置查询结果     *
     * 参数示例：<pre></pre>     
     * 此参数必填
     */
    public void setPageResult(AlibabaProductPageResult pageResult) {
        this.pageResult = pageResult;
    }

}
