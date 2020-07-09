package com.gem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 登陆控制层
 * @Date: 2020-07-09
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String index()
    {
        return "login";
    }

}
