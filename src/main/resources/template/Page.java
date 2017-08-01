package com.${companyName}.${projectName}.page;

import com.base.core.page.Page;
import com.${companyName}.${projectName}.domain.dto.${Entity}Dto;

import java.util.List;

/**
 * @description
 * @author yidadi
 * @version 1.0
 * @date
 */
public class ${Entity}Page extends Page<List<${Entity}Dto>> {
    private List<${Entity}Dto> rows;

    public ${Entity}Page(List<${Entity}Dto> rows) {
        this.rows = rows;
    }

    @Override
    public List<${Entity}Dto> getRows() {
        return rows;
    }

    @Override
    public void setRows(List<${Entity}Dto> rows) {
        this.rows = rows;
    }
}
