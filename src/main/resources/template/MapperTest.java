package com.${companyName}.${projectName}.dao;

import java.util.List;

import com.${companyName}.${projectName}.query.${Entity}Query;
import com.${companyName}.${projectName}.${ProjectName}MicroServiceApplication;
import com.${companyName}.${projectName}.entity.${Entity}Entity;
import com.${companyName}.${projectName}.dao.${Entity}dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description
 * 
 * @author  yidadi
 * @version 1.0
 * @date 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@SpringApplicationConfiguration(classes = ${ProjectName}MicroServiceApplication.class)
public class ${Entity}DaoTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(${Entity}DaoTest.class);

	@Autowired
	private ${Entity}Dao ${entity}Dao;

	@Test
	public void addTest() {
		${Entity}Entity ${entity}Entity = new ${Entity}Entity();
		${entity}Entity.setId(3);
		Assert.assertEquals(1, this.${entity}Dao.add(${entity}Entity));
	}

	@Test
	public void deleteTest() {
		Assert.assertEquals(1, this.${entity}Dao.delete(1L));
	}

	@Test
	public void updateTest() {
		${Entity}Entity ${entity}Entity = new ${Entity}Entity();
		${entity}Entity.setId(1);
		Assert.assertEquals(1, this.${entity}Dao.update(${entity}Entity));
	}

	@Test
	public void selectOneEntityTest() {
		${Entity}Entity ${entity}Entity = this.${entity}Dao.findById(2);
		LOG.info(${entity}Entity);
	}

	@Test
	public void selectCountByQueryTest() {
		${Entity}Query query = new ${Entity}Query();
		int count = this.${entity}Dao.countByQuery(query);
		LOG.info("count= " + count);
	}
	
	@Test
	public void selectListByQueryTest() {
		${Entity}Query query = new ${Entity}Query();
		List<${Entity}Entity> ${entity}Entitys = this.${entity}Dao.findByQuery(query);
		for (${Entity}Entity ${entity}Entity : ${entity}Entitys) {
			LOG.info(${entity}Entity);
		}
	}
}
