package com.${companyName}.${projectName}.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
#if(${parser.includeBigDecimal})
import java.math.BigDecimal;
#end

/**
 * ${entityComment}
 *
 * @author yidadi
 * @date
 */
@ApiModel(value = "${entityComment}")
public class ${Entity}Dto implements Serializable {

#foreach($element in $propertyList)
    /**
     * ${element.columnComment}
     */
    @ApiModelProperty(value = "${element.columnComment}")
#if(${element.dataType} == 'LocalDate' || ${element.dataType} == 'LocalDateTime' || ${element.dataType} == 'Date')
    private Long ${element.property};
#else
    private ${element.dataType} ${element.property};
    #end

#end

#foreach($element in $propertyList)
#if(${element.dataType} == 'LocalDate' || ${element.dataType} == 'LocalDateTime' || ${element.dataType} == 'Date')
    public Long get${element.capitalizeProperty}() {
        return ${element.property};
    }

    public void set${element.capitalizeProperty}(Long ${element.property}) {
        this.${element.property} = ${element.property};
    }

#else
    public ${element.dataType} get${element.capitalizeProperty}() {
        return ${element.property};
    }

    public void set${element.capitalizeProperty}(${element.dataType} ${element.property}) {
        this.${element.property} = ${element.property};
    }

#end
#end

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
