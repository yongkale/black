package heiqi.com.greenfoodapp.service.impl;

import heiqi.com.greenfoodapp.service.UserService;
import heiqi.com.greenfoodapp.common.Constant;
import heiqi.com.greenfoodapp.dao.UserDao;
import heiqi.com.greenfoodapp.model.User;
import heiqi.com.greenfoodapp.util.MD5Util;
import heiqi.com.greenfoodapp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> login(User user) {
        Map<String, Object> results = new HashMap<String, Object>();
        User userFromDB = userDao.findUserByName(user.getName());
        if (user == null) {
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_FAILED);
            results.put(Constant.RESULT_DATA_MSG, "用户名不存在");
        } else {

            if (userFromDB.getPassword().equals(MD5Util.string2MD5(user.getPassword()))) {
                results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_SUCCESS);
                userFromDB.setPassword(null);
                results.put(Constant.RESULT_DATA_MSG, userFromDB);
            } else {
                results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_FAILED);
                results.put(Constant.RESULT_DATA_MSG, "密码错误");
            }
        }

        return results;
    }

    @Override
    public Map<String, Object> register(User user) {
        Map<String, Object> results = new HashMap<String, Object>();
        boolean flag = true;

        if (StringUtil.isEmpty(user.getPassword())) {
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_FAILED);
            results.put(Constant.RESULT_DATA_MSG, "密码不能为空");
            flag = false;
        }
        if (StringUtil.isEmpty(user.getName())) {
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_FAILED);
            results.put(Constant.RESULT_DATA_MSG, "用户名不能为空");
            flag = false;
        }
        if (StringUtil.isEmpty(user.getEmail())) {
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_FAILED);
            results.put(Constant.RESULT_DATA_MSG, "邮箱不能为空");
            flag = false;
        }

        if (flag) {
            user.setPassword(MD5Util.string2MD5(user.getPassword()));
            userDao.addUser(user);
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_SUCCESS);
            user.setPassword(null);
            results.put(Constant.RESULT_DATA_MSG, user);
        }

        return results;
    }
}
