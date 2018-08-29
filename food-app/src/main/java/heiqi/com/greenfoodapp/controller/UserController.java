package heiqi.com.greenfoodapp.controller;

import heiqi.com.greenfoodapp.service.UserService;
import heiqi.com.greenfoodapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService serService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(User user){
        serService.login(user);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void register(User user){
        serService.register(user);
    }
}
