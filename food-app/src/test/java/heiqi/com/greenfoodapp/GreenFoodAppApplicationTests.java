package heiqi.com.greenfoodapp;

import heiqi.com.greenfoodapp.dao.UserDao;
import heiqi.com.greenfoodapp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreenFoodAppApplicationTests {

    @Autowired
    private UserDao userDao;

	@Test
	public void contextLoads() {
        User user = new User();
        user.setName("kale");
        userDao.addUser(user);
        int a= 1;
	}

}
