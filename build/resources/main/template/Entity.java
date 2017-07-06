package com.${companyName}.${projectName}.microservice.entity;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
#if(${parser.includeBigDecimal})
import java.math.BigDecimal;
#end
#if(${parser.includeLocalDate})
import java.time.LocalDate;
#end
#if(${parser.includeLocalDateTime})
import java.time.LocalDateTime;
#end

/**
 * ${entityComment}
 *
 * @author yidadi
 * @date
 */
public class ${Entity}Entity implements Serializable {

#foreach($element in $propertyList)
    /**
     * ${element.columnComment}
     */
    private ${element.dataType} ${element.property};

#end


#foreach($element in $propertyList)
    public ${element.dataType} get${element.capitalizeProperty}() {
        return ${element.property};
    }

    public void set${element.capitalizeProperty}(${element.dataType} ${element.property}) {
        this.${element.property} = ${element.property};
    }

#end

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
