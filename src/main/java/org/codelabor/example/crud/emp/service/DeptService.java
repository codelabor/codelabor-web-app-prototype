package org.codelabor.example.crud.emp.service;

import java.util.List;

import org.codelabor.example.crud.emp.dto.DeptDto;

public interface DeptService {

	DeptDto selectDept(Integer deptNo);

	List<DeptDto> selectDeptList();

}
