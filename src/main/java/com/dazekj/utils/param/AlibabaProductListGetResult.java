package com.dazekj.utils.param;

public class AlibabaProductListGetResult {

    private AlibabaProductProductInfoListResult result;

    /**
     * @return 查询结果
     */
    public AlibabaProductProductInfoListResult getResult() {
        return result;
    }

    /**
     * 设置查询结果     *
          
     * 此参数必填
     */
    public void setResult(AlibabaProductProductInfoListResult result) {
        this.result = result;
    }

    private String message;

    /**
     * @return 错误信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置错误信息     *
          
     * 此参数必填
     */
    public void setMessage(String message) {
        this.message = message;
    }

    private Boolean success;

    /**
     * @return 是否成功
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 设置是否成功     *
          
     * 此参数必填
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private String code;

    /**
     * @return 错误码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置错误码     *
          
     * 此参数必填
     */
    public void setCode(String code) {
        this.code = code;
    }

}
