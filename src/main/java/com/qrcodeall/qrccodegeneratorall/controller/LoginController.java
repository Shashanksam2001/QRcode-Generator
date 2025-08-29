package com.qrcodeall.qrccodegeneratorall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qrcodeall.qrccodegeneratorall.pojo.login.Login;
import com.qrcodeall.qrccodegeneratorall.pojo.login.Register;
import com.qrcodeall.qrccodegeneratorall.service.LoginService;

@Controller
@RequestMapping("/welcome")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@ModelAttribute Login loginrequest){
        String loginMsg=loginService.loginUser(loginrequest);
        if(loginMsg.contains("Login successful!")){
            return "redirect:/qrcode.html";
        }
        return "redirect:/welcome.html?error=Invalid username or password&form=login";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute Register register){
        String registerMsg=loginService.registerUser(register);
         if (registerMsg.contains("Registered Successfully")) {
        // redirect to welcome page, open login form, show success message
        return "redirect:/welcome.html?msg=Registered Successfully, please login.&form=login";
    }
    // failure → open register form with error
    return "redirect:/welcome.html?error=" + registerMsg.replace(" ", "%20") + "&form=register";
        
    }
}
