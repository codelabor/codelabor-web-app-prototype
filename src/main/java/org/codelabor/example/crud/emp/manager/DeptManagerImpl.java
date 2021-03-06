package org.codelabor.example.crud.emp.manager;

import java.util.List;

import org.codelabor.example.crud.emp.dao.DeptDao;
import org.codelabor.example.crud.emp.dto.DeptDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptManagerImpl implements DeptManager {

	@Autowired
	private DeptDao deptDao;
	private final Logger logger = LoggerFactory
			.getLogger(DeptManagerImpl.class);

	@Override
	public DeptDto selectDept(Integer deptNo) {
		logger.debug("selectDept");
		return deptDao.selectDept(deptNo);
	}

	@Override
	public List<DeptDto> selectDeptList() {
		logger.debug("selectDeptList");
		return deptDao.selectDeptList();
	}

}
