package heiqi.com.greenfoodapp.dao;

import heiqi.com.greenfoodapp.model.DateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActionDao {
    private final static String actionCollectionName = "action";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(DateAction action) {
        mongoTemplate.insert(action, actionCollectionName);
    }

    public List<DateAction> getList() {
        return (List<DateAction>) mongoTemplate.getCollection(actionCollectionName);
    }
}
