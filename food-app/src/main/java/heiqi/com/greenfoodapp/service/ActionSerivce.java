package heiqi.com.greenfoodapp.service;

import heiqi.com.greenfoodapp.model.DateAction;

import java.util.Map;

public interface ActionSerivce {
    public Map<String, Object> add(DateAction action);

    public Map<String,Object> list();
}
