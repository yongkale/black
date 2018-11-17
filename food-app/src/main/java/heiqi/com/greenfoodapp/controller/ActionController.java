package heiqi.com.greenfoodapp.controller;

import heiqi.com.greenfoodapp.model.DateAction;
import heiqi.com.greenfoodapp.service.ActionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/apiaction")
public class ActionController {
    @Autowired
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

    @ResponseBody
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Map<String, Object> find(@RequestParam  String id){
        return actionSerivce.find(id);
    }
}
