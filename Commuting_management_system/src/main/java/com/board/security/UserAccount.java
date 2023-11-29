//package com.board.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
////유저정보객체
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@Entity
//@Table(name = "useraccount")
//@Getter
//public class UserAccount implements UserDetails {
//
//  @Id
//  @Column(name = "nickname")
//  private String nickname;
//
//  @Column(name = "pw")
//  private String pw;
//
//  @Column(name = "auth")
//  private String auth;
//  
//  @Column(name = "employee_no")
//  private Long employee_no;
//
//  @Builder
//  public UserAccount(String nickname, String pw, String auth,Long no) {
//    this.nickname = nickname;
//    this.pw = pw;
//    this.auth = auth;
//    this.employee_no=no;
//  }
//  
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//      Set<GrantedAuthority> authorities = new HashSet<>();
//      for (String role : auth.split(",")) {
//          authorities.add(new SimpleGrantedAuthority(role));
//      }
//      return authorities;
//  }
//
//  
//  @Override
//  public String getUsername() {
//    return nickname;
//  }
//
//  @Override
//  public String getPassword() {
//    return pw;
//  }
//
//  @Override
//  public boolean isAccountNonExpired() { //계정이 만료되었는지 여부를 리턴합니다.
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() { //계정이 잠겼는지 여부를 리턴합니다.
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {//계정의 비밀번호가 오래 사용했는지에 대한 여부를 리턴합니다.
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() { //계정의 활성화 여부를 리턴합니다.
//    return true;
//  }
//
//
//}