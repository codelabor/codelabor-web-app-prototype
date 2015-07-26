package org.codelabor.example.crud.emp.manager;

import java.util.List;

import org.codelabor.example.crud.emp.dto.DeptDto;

public interface DeptManager {

	DeptDto selectDept(Integer deptNo);

	List<DeptDto> selectDeptList();

}
