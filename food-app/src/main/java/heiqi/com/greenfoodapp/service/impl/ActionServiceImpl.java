package heiqi.com.greenfoodapp.service.impl;

import heiqi.com.greenfoodapp.common.Constant;
import heiqi.com.greenfoodapp.dao.ActionDao;
import heiqi.com.greenfoodapp.model.DateAction;
import heiqi.com.greenfoodapp.service.ActionSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActionServiceImpl implements ActionSerivce {
    private static final Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);

    @Autowired
    private ActionDao actionDao;

    @Override
    public Map<String, Object> add(DateAction action) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            Date now = new Date();
            action.setCrateTime(now);
            actionDao.add(action);
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_SUCCESS);
            results.put(Constant.RESULT_DATA_MSG, action);
        } catch (Exception e) {
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_FAILED);
            results.put(Constant.RESULT_DATA_MSG, "添加失败");
            logger.info(e.getMessage());
        }

        return results;
    }

    @Override
    public Map<String, Object> list() {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_SUCCESS);
            results.put(Constant.RESULT_DATA_MSG, actionDao.getList());
        } catch (Exception e) {
            results.put(Constant.RESULT_STATUS_MSG, Constant.RESULT_STATUS_FAILED);
            results.put(Constant.RESULT_DATA_MSG, "查询失败");
            logger.info(e.getMessage());
        }

        return results;
    }
}
