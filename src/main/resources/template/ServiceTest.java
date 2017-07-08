package com.${companyName}.${projectName}.service;

import com.${companyName}.${projectName}.service.${Entity}Service;

import com.${companyName}.${projectName}.${Entity}MicroServiceApplication;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@SpringApplicationConfiguration(classes = ${Entity}MicroServiceApplication.class)
public class ${Entity}ServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(${Entity}ServiceTest.class);
	
	@Autowired
	private ${Entity}Service ${entity}Service;
}
