//package com.board;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.board.domain.BoardDTO;
//import com.board.domain.CommentDTO;
//import com.board.domain.DeptDTO;
//import com.board.domain.EmployeeDTO;
//import com.board.domain.UserDTO;
//import com.board.domain.WorkDTO;
//import com.board.mapper.BoardMapper;
//import com.board.mapper.CommentMapper;
//import com.board.mapper.EmployeeMapper;
//import com.board.mapper.UserMapper;
//import com.board.mapper.WorkMapper;
//
//@SpringBootTest
//class MapperTests {
//	@Autowired
//	private CommentMapper cm;
//	@Test
//	public void testComment() {
//		CommentDTO params = new CommentDTO();
//		params.setBoardIdx((long)42);
//		params.setCommentNickname("1111");
//		params.setContent("test");
//		cm.insertComment(params);
//	}
//	@Test
//	public void deleteComment() {
//		cm.deleteComment(41);
//	}
//	
//  @Autowired
//  private EmployeeMapper em;
//  
//  @Test
//  public void testRole() {
//	  int params = 139;
//	  em.getRole(params);
//  }
//
//  @Test
//  public void testDept() {
//	  DeptDTO params = new DeptDTO();
//	  params.setDepNo(3);
//	  params.setDepName("테스트");
//	  params.setJikup("테스트");
//	  em.insertDept(params);
//  }
//  
//  @Test
//  public void selectEmployeeDetail() {
//	  int params = 99;
//	  em.selectEmployeeDetail(params);
//  }
//  
//  @Test
//  public void selectEmployee() {
//	  EmployeeDTO params = new EmployeeDTO();
//	  em.selectEmployeeList(params);
//  }
//  
//  @Test
//  public void selectEmployee2() {
//	  EmployeeDTO params = new EmployeeDTO();
//	  List<EmployeeDTO> dto = em.selectEmployeeList(params);
//  }
//  
//  @Test
//  public void testEmployee() {
//	  EmployeeDTO params = new EmployeeDTO();
//	  params.setDepName("test");
//	  params.setName("test");
//	  params.setJuminNum("000000000000");
//	  params.setTelNum("0000000000000");
//	  params.setEmail("abc@korea.com");
//	  em.insertEmployee(params);
//  }
//  
//  @Test
//  public void testUserNo() {
//	  EmployeeDTO params = new EmployeeDTO();
//	  params.setNo(99);
//	  em.selectEmployeeNo(params);
//  }
//  
//  @Autowired
//  private UserMapper um;
//  @Test
//  public void testNickname() {
//	  UserDTO params = new UserDTO();
//	  params.setNickname("관리자");
//	  um.selectNickname(params);
//  }
//  
////--------------------------------------------------------------------------
//  @Autowired
//  private WorkMapper wm;
//  
//  @Test
//  public void testSelectEmp() {
//	  WorkDTO params = new WorkDTO();
//	  params.setName("홍길동");
//	  params.setJumin("1234567890123");
//	  wm.selectEmp(params);
//  }
//  
//  @Test
//  public void testWorkof() {
//	  for( int i=0 ; i < 50 ; i++ ) {
//	  WorkDTO params = new WorkDTO();
//	  params.setName("홍길동");
//	  params.setJumin("1234567890123");
//	  wm.insertWork(params);
//	  wm.updateWork(params);
//  }}
//  @Test
//  public void testWorkOn() {
//	  WorkDTO params = new WorkDTO();
//	  params.setName("홍길동");
//	  params.setJumin("1234567890123");
//	  wm.insertWork(params);
//  }
//  @Test
//  public void testWorkOff() {
//	  WorkDTO params = new WorkDTO();
//	  params.setName("test");
//	  params.setJumin("000000000000 ");
//	  wm.updateWork(params);
//  }
//  
//  @Test
//  public void testWorkList() {
//	  WorkDTO params = new WorkDTO();
//	  List<WorkDTO> workList = wm.selectWorkList(params);
//	  
//      for (WorkDTO work : workList) {
//        System.out.println("=========================");
//        System.out.println(work.getNum());
//        System.out.println(work.getNo());
//        System.out.println(work.getName());
//        System.out.println(work.getWorkDate());
//        System.out.println(work.getWorkOn());
//        System.out.println(work.getWorkOff());
//        System.out.println(work.getWorkTime());
//        System.out.println("=========================");
//      }
//  }
//  @Test
//  public void testWorkList2() {
//	  WorkDTO params = new WorkDTO();
//	  params.setNo((long)15);
//	  List<WorkDTO> workList = wm.selectWorkList2(params);
//	  
//	  for (WorkDTO work : workList) {
//		  System.out.println("=========================");
//		  System.out.println(work.getNum());
//		  System.out.println(work.getNo());
//		  System.out.println(work.getName());
//		  System.out.println(work.getWorkDate());
//		  System.out.println(work.getWorkOn());
//		  System.out.println(work.getWorkOff());
//		  System.out.println(work.getWorkTime());
//		  System.out.println("=========================");
//	  }
//  }
//  @Test
//  public void testWork() {
//	  WorkDTO params = new WorkDTO();
//	  WorkDTO result = wm.selectWork(params);
//	  
//		  System.out.println("=========================");
//		  System.out.println(result.getNo());
//		  System.out.println(result.getWorkOn());
//		  System.out.println(result.getWorkOff());
//		  System.out.println("=========================");
//	  }
// 
////--------------------------------------------------------------------------
//  @Autowired
//  private BoardMapper bm;
//  
//  @Test
//  public void detail() {
//	  bm.selectBoardDetail(74);
//  }
//  @Test
//  public void testbd() {
//	  BoardDTO params = new BoardDTO();
//	  bm.selectBoardList(params);
//  }
//  @Test
//  public void testBoard() {
//	  BoardDTO params = new BoardDTO();
//	  params.setNickname("홍길동");
//	  params.setContent("내용");
//	  params.setFilename("파일");
//	  params.setFilesize(1);
//	  params.setTitle("제목");
//	  bm.insertBoard(params);
//  }
//  @Test
//  public void testBoardlist() {
//	  for( int i=1 ; i < 201 ; i++ ) {
//	  BoardDTO params = new BoardDTO();
//	  params.setNickname("1111");
//	  params.setTitle("게시글테스트"+i);
//	  params.setContent("내용");
////	  params.setFilename("파일");
//	  bm.insertBoard(params);
//  }}
//  @Test
//  public void testBoard2() {
//	  for( int i=0 ; i < 50 ; i++ ) {
//	  BoardDTO params = new BoardDTO();
//	  params.setNickname("게시판 테스트");
//	  params.setContent("내용");
//	  params.setFilename(""+i);
//	  params.setTitle("제목");
//	  bm.insertBoard(params);
//	  }
//  }
//  
//}
