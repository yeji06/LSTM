package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.domain.EmployeeDTO;
import com.board.mapper.EmployeeMapper;
import com.board.paging.PaginationInfo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeMapper em;

@Override
public List<EmployeeDTO> getEmployeeList(EmployeeDTO params) {
	List<EmployeeDTO> employeeList = Collections.emptyList();
	int employeeTotalCount = em.selectEmployeeTotalCount(params);
	 
	PaginationInfo paginationInfo = new PaginationInfo(params);
    paginationInfo.setTotalRecordCount(employeeTotalCount);

    params.setPaginationInfo(paginationInfo);

    if (employeeTotalCount > 0) {
    	employeeList = em.selectEmployeeList(params);
      }
	
	return employeeList;
}

@Override
public boolean insertEmployee(EmployeeDTO params) {
	int queryResult = 0;
	queryResult = em.insertEmployee(params);
	return (queryResult == 1)? true: false;
}

@Override
public EmployeeDTO getEmployeeDetail(Long no) {
	return em.selectEmployeeDetail(no);
}

@Override
public boolean registerEmployee(EmployeeDTO employee) {
	int queryResult = 0;
  
  if (employee.getNo() == null) {
    queryResult = em.insertEmployee(employee);
  } else {
    queryResult = em.updateEmployee(employee);
  }
  return (queryResult == 1) ? true : false;
}

@Override
public EmployeeDTO getEmployeeNo(EmployeeDTO params) {
	EmployeeDTO employeeDTO = new EmployeeDTO();
	employeeDTO = em.getEmployeeNo(params);
	return employeeDTO;
}

@Override
public boolean deleteEmployee(Long no) {
	int queryResult=0;
	queryResult=em.deleteEmployee(no);
	return (queryResult == 1)? true: false;
}
@Override
public boolean deleteCancelEmployee(Long no) {
	int queryResult;
	queryResult=em.deleteCancelEmployee(no);
	return (queryResult == 1)? true: false;
}
}