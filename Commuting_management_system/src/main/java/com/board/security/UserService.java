//package com.board.security;
//
//import java.util.Optional;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.board.domain.EmployeeDTO;
//import com.board.domain.UserDTO;
//import com.board.service.EmployeeService;
//
//import lombok.RequiredArgsConstructor;
//@RequiredArgsConstructor
//@Service
//public class UserService implements UserDetailsService {
//
//  private final UserRepository userRepository;
//  private final EmployeeService employeeService;
//  
//
//  /**
//   * 회원정보 저장
//   */
//  public String save(UserDTO infoDTO) {
//    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    infoDTO.setPw(encoder.encode(infoDTO.getPw()));
//    
//    EmployeeDTO employeeDTO = employeeService.getEmployeeDetail(infoDTO.getEmployeeNo());
//    UserAccount useraccount = UserAccount.builder()
//            .nickname(infoDTO.getNickname())
//            .auth(employeeDTO.getAuth())
//            .pw(infoDTO.getPw())
//            .no(infoDTO.getEmployeeNo())
//            .build();
//
//        userRepository.save(useraccount);
//        System.out.println("nickname:"+useraccount.getNickname());
//        System.out.println("pw:"+useraccount.getPw());
//        return useraccount.getNickname(); 
//  }
//  
////loadUserByUsername함수를 Override하여 DB에 접근해 nickname이 DB에 있는지 확인
//@Override 
//public UserAccount loadUserByUsername(String nickname) throws UsernameNotFoundException {
//	System.out.println("nickname : " + nickname);
//	Optional<UserAccount> user = userRepository.findByNickname(nickname);
//  return  userRepository.findByNickname(nickname)
//      .orElseThrow(() -> new UsernameNotFoundException((nickname)));
//}	
//
//}
