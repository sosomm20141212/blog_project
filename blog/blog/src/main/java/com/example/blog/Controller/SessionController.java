package com.example.blog.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.model.User;

import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

//로그인
    @GetMapping("/login")
    public String login(){
        return "html/login";
    }
    @PostMapping("/login")
    public String loginPost(
        @RequestParam("userId") String userId,
        @RequestParam("password") String password,
        HttpSession session
    ){
        User user;
        user = userRepository.findByUserIdAndPassword(userId, password);
        int count = userRepository.findByPasswordAndUserId(password, userId).size();
        if(count < 1) {
            return "/html/loginfail";
        }
        session.setAttribute("user", user);
        return "redirect:/main";
    }
  
//회원가입
    @GetMapping("/register")
    public String register(){
        return "html/register";
    }
    @PostMapping("/register")
    public String registerPost(
        @RequestParam("userId") String userId,
        @RequestParam("userName") String userName,
        @RequestParam("password") String password,
        HttpSession session
    ) {

        if(userRepository.findByUserId(userId).size() > 0){
            return "html/register1";
        }
        User user = new User();      
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        userRepository.save(user);
        session.setAttribute("user", user);
        return "html/registerSuccess";
    }


}
