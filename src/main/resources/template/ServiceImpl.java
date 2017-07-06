package com.${companyName}.${projectName}.microservice.service.impl;

import com.${companyName}.common.core.dao.BaseDao;
import com.${companyName}.common.core.service.BaseServiceImpl;
import com.${companyName}.${projectName}.microservice.dao.${Entity}Dao;
import com.${companyName}.${projectName}.microservice.entity.${Entity}Entity;
import com.${companyName}.${projectName}.microservice.service.${Entity}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * ${Entity} Service层实现
 *
 * @author yidadi
 * @date
 */
@Service("${entity}Service")
public class ${Entity}ServiceImpl extends BaseServiceImpl<${Entity}Entity> implements ${Entity}Service {

    @Autowired
    private ${Entity}Dao ${entity}Dao;

    @Override
    protected BaseDao<${Entity}Entity> getBaseDao() {
        return this.${entity}Dao;
    }

}
