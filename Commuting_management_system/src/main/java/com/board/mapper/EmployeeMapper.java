package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.DeptDTO;
import com.board.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
  public List<EmployeeDTO> selectEmployeeList(EmployeeDTO params);

  public int insertDept(DeptDTO params);
  public int insertEmployee(EmployeeDTO params);

  public int updateEmployee(EmployeeDTO employee);
  public int deleteEmployee(Long no);
  public int deleteCancelEmployee(Long no);

  public int selectEmployeeTotalCount(EmployeeDTO params);
  public EmployeeDTO selectEmployeeDetail(Long no);
  
  public EmployeeDTO selectEmployeeNo(EmployeeDTO params);

  public EmployeeDTO getEmployeeNo(EmployeeDTO params);

  public String getRole(Long no);
//  public String getRole(int employeeNo);

}