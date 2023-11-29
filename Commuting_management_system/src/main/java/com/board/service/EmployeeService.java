package com.board.service;

import java.util.List;

import com.board.domain.EmployeeDTO;

public interface EmployeeService {
	public List<EmployeeDTO> getEmployeeList(EmployeeDTO params);
	public boolean insertEmployee(EmployeeDTO params);
	public boolean registerEmployee(EmployeeDTO params);
	
	public boolean deleteEmployee(Long no);
	public boolean deleteCancelEmployee(Long no);
	public EmployeeDTO getEmployeeDetail(Long no);
	public EmployeeDTO getEmployeeNo(EmployeeDTO params);
}