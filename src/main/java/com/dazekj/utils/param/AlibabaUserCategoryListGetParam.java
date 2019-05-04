package com.dazekj.utils.param;

import com.alibaba.ocean.rawsdk.client.APIId;
import com.alibaba.ocean.rawsdk.common.AbstractAPIRequest;

public class AlibabaUserCategoryListGetParam extends AbstractAPIRequest<AlibabaUserCategoryListGetResult> {

    public AlibabaUserCategoryListGetParam() {
        super();
        oceanApiId = new APIId("com.alibaba.product", "alibaba.userCategory.list.get", 1);
    }

}
