package heiqi.com.greenfoodapp.controller;

import heiqi.com.greenfoodapp.dao.UserDao;
import heiqi.com.greenfoodapp.service.UserService;
import heiqi.com.greenfoodapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/apiuser")
public class UserController {
    @Autowired
    private UserService serService;
    @Autowired
    private UserDao userDao;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        userDao.addUser(user);
        //serService.login(user);
        return "hello";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void register(User user){
        serService.register(user);
    }
}
