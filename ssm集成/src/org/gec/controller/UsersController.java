package org.gec.controller;

import org.gec.pojo.Users;
import org.gec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;


    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {

        Users user = usersService.login(username, password);
        if(user!=null){
            System.out.println(user.toString());
            session.setAttribute("user_session",user);
            return "redirect:/toCinema";
        }
        else{
            return "login";
        }


    }


}
