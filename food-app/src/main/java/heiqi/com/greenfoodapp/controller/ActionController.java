package heiqi.com.greenfoodapp.controller;

import heiqi.com.greenfoodapp.model.DateAction;
import heiqi.com.greenfoodapp.service.ActionSerivce;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/apiaction")
public class ActionController {

    private ActionSerivce actionSerivce;

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody DateAction action){

        return actionSerivce.add(action);
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map<String, Object> list(){

        return actionSerivce.list();
    }
}
