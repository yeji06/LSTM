package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.AndroidWorkDTO;
import com.board.domain.WorkDTO;

@Mapper
public interface WorkMapper {
	public WorkDTO selectWork(WorkDTO params);
	public int insertWork(WorkDTO params);
	public int updateWork(WorkDTO params);
	
	public List<WorkDTO> selectWorkList(WorkDTO params);
	public int selectWorkTotalCount(WorkDTO params);
	
	public List<WorkDTO> selectWorkList2(WorkDTO params);
	public int selectWorkTotalCount2(WorkDTO params);
	
	public WorkDTO selectEmp(WorkDTO params);
	public AndroidWorkDTO getWorkInfoByDate(AndroidWorkDTO user);
}