package heiqi.com.greenfoodapp.dao;

import heiqi.com.greenfoodapp.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private MongoTemplate mongoTemplate;

    public User findUserByName(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), User.class);
    }

    public void addUser(User user) {
        mongoTemplate.insert(user);
    }
}
