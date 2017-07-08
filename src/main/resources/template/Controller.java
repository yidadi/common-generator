package com.${companyName}.${projectName}.controller;

import com.${companyName}.${projectName}.service.${Entity}Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description ${entityComment}控制器
 * @author  yidadi
 * @version 1.0
 * @date 
 */
@Controller
@RequestMapping("/${entity}")
public class ${Entity}Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(${Entity}Controller.class);

	@Autowired
	private ${Entity}Service ${entity}Service;
}
