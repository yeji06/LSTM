package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.AndroidWorkDTO;
import com.board.domain.BoardDTO;
import com.board.domain.UserDTO;
import com.board.domain.WorkDTO;
import com.board.mapper.UserMapper;
import com.board.mapper.WorkMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommuteController {
    @Autowired
    WorkMapper wm;

    @PostMapping("/send-selected-date")
    @ResponseBody
    public AndroidWorkDTO save(@RequestBody AndroidWorkDTO user) {
        log.info("send={}",user.getWorkOn());
        log.info("employee={}",user.getNo());
        
        AndroidWorkDTO awd = wm.getWorkInfoByDate(user);
       
        return awd;
    }
    
 
    
//    @PostMapping("/CreatePost")
//    @ResponseBody
//    public void save(@RequestBody BoardDTO bd) {
//        log.info("nickname={}",bd.getNickname());
//        log.info("title={}",bd.getContent());
//        log.info("content={}",bd.getContent());
//        
//    }
}
