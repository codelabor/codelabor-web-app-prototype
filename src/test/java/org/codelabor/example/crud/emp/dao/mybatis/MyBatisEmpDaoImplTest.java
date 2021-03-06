/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codelabor.example.crud.emp.dao.mybatis;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.codelabor.example.crud.emp.dao.mybatis.MyBatisEmpDaoImpl;
import org.codelabor.example.crud.emp.dto.EmpDto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author SHIN Sang-jae
 *
 */
@ContextConfiguration(locations = { "file:src/main/resources/spring/applicationContext-root.xml",
		"file:src/main/resources/spring/applicationContext-property.xml", "file:src/main/resources/spring/applicationContext-myBatis.xml",
		"file:src/test/resources/spring/applicationContext-dataSource.xml", "file:src/main/resources/spring/applicationContext-security.xml", })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyBatisEmpDaoImplTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.debug("setUpBeforeClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass");
	}

	@Autowired
	private MyBatisEmpDaoImpl dao;

	private final static Logger logger = LoggerFactory.getLogger(MyBatisEmpDaoImplTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger.debug("setUp");
		Assert.assertNotNull(dao);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown");
	}

	/**
	 * Test method for
	 * {@link org.codelabor.example.crud.emp.dao.mybatis.MyBatisEmpDaoImpl#insertEmp(org.codelabor.example.crud.emp.dto.EmpDto)}
	 * .
	 */
	@Test
	public final void test1InsertEmp() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();

		EmpDto empDto = new EmpDto();
		empDto.setEmpNo(1000);
		empDto.setEname("Bomber");
		empDto.setJob("SA");
		empDto.setMgr(7839);
		calendar.set(2001, 2, 1, 12, 00, 00);
		empDto.setHireDate(Calendar.getInstance().getTime());
		empDto.setSal(new BigDecimal("5000.0"));
		empDto.setComm(new BigDecimal("100.0"));
		empDto.setDeptNo(20);
		int affectedRowCount = dao.insertEmp(empDto);
		Assert.assertEquals(1, affectedRowCount);
	}

	/**
	 * Test method for
	 * {@link org.codelabor.example.crud.emp.dao.mybatis.MyBatisEmpDaoImpl#updateEmp(org.codelabor.example.crud.emp.dto.EmpDto)}
	 * .
	 */
	@Test
	public final void test2UpdateEmp() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();

		EmpDto empDto = new EmpDto();
		empDto.setEmpNo(1000);
		empDto.setEname("Bomber");
		empDto.setJob("SA");
		empDto.setMgr(7839);
		calendar.set(2001, 2, 1, 12, 00, 00);
		empDto.setHireDate(Calendar.getInstance().getTime());
		empDto.setSal(new BigDecimal("6000.0"));
		empDto.setComm(new BigDecimal("200.0"));
		empDto.setDeptNo(20);
		int affectedRowCount = dao.updateEmp(empDto);
		Assert.assertEquals(1, affectedRowCount);
	}

	/**
	 * Test method for
	 * {@link org.codelabor.example.crud.emp.dao.mybatis.MyBatisEmpDaoImpl#deleteEmp(java.lang.String)}
	 * .
	 */
	@Test
	public final void test3DeleteEmp() {
		Integer empNo = 1000;
		int affectedRowCount = dao.deleteEmp(empNo);
		Assert.assertEquals(1, affectedRowCount);
	}

	/**
	 * Test method for
	 * {@link org.codelabor.example.crud.emp.dao.mybatis.MyBatisEmpDaoImpl#selectEmp(java.lang.String)}
	 * .
	 */
	@Test
	public final void testSelectEmp() {
		EmpDto emp = dao.selectEmp(7369);
		Assert.assertNotNull(emp);
		Assert.assertEquals("SMITH", emp.getEname());
	}

	/**
	 * Test method for
	 * {@link org.codelabor.example.crud.emp.dao.mybatis.MyBatisEmpDaoImpl#selectEmpList()}
	 * .
	 */
	@Test
	public final void testSelectEmpList() {
		logger.debug("testSelectEmpList");
		List<EmpDto> empList = dao.selectEmpList();
		logger.debug("empList.size(): {}", empList.size());
		Assert.assertNotNull(empList);
		Assert.assertTrue(!empList.isEmpty());
	}

	/**
	 * Test method for
	 * {@link org.codelabor.example.crud.emp.dao.mybatis.MyBatisEmpDaoImpl#selectEmpListByDeptNo(java.lang.Integer)}
	 * .
	 */
	@Test
	public final void testSelectEmpListByDeptNo() {
		List<EmpDto> empList = dao.selectEmpListByDeptNo(10);
		logger.debug("empList.size(): {}", empList.size());
		Assert.assertNotNull(empList);
		Assert.assertTrue(!empList.isEmpty());
	}

}
