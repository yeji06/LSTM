package com.board.service;

import java.util.List;

import com.board.domain.WorkDTO;

public interface WorkService {
  public boolean registerWork(WorkDTO params);
  boolean selectEmp(WorkDTO params);
  public List<WorkDTO> getWorkList(WorkDTO params);
  public List<WorkDTO> getWorkList2(WorkDTO params);
}