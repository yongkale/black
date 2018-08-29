package heiqi.com.greenfoodapp.service;

import heiqi.com.greenfoodapp.model.User;

import java.util.Map;

public interface UserService {
    public Map<String, Object> login(User user);

    public Map<String, Object> register(User user);
}
