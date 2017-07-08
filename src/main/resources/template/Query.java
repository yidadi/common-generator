package com.${companyName}.${projectName}.query;

import com.${companyName}.common.core.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @description
 * @author yidadi
 * @version 1.0
 * @date
 */
@ApiModel("${entityComment}表单查询对象")
public class ${Entity}Query extends BaseQuery{

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

