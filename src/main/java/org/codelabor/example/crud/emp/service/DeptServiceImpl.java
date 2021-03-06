package org.codelabor.example.crud.emp.service;

import java.util.List;

import org.codelabor.example.crud.emp.dto.DeptDto;
import org.codelabor.example.crud.emp.manager.DeptManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {

	private final Logger logger = LoggerFactory
			.getLogger(DeptServiceImpl.class);

	@Autowired
	private DeptManager deptManager;

	@Override
	public DeptDto selectDept(Integer deptNo) {
		logger.debug("selectDept");
		return deptManager.selectDept(deptNo);
	}

	@Override
	public List<DeptDto> selectDeptList() {
		logger.debug("selectDeptList");
		return deptManager.selectDeptList();
	}

}
