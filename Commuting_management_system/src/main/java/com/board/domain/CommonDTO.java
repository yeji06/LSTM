package com.board.domain;

import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {

  /** 페이징 정보 */
  private PaginationInfo paginationInfo;

  private String deleteYn;

}
