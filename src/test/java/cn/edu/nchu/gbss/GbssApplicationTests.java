package cn.edu.nchu.gbss;

import cn.edu.nchu.gbss.model.UserBase;
import cn.edu.nchu.gbss.repository.UserBaseDao;
import cn.edu.nchu.gbss.service.UserBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GbssApplicationTests {

    @Autowired
    UserBaseDao userBaseDao;
    @Autowired
    UserBaseService userBaseService;

    /*@Test
    public void contextLoads() {
    }*/

    @Test
    public void testAdd(){
        for (int i = 0; i < 100; i++) {
            UserBase userBase = new UserBase();
            userBase.setUserId("10000"+i);
            userBase.setUserName("test" + i);
            userBaseService.save(userBase);
        }
    }

    @Test
    public void update(){
        userBaseService.update("5201234","你好");
    }

    @Test
    public void findAllUserBase(){
        List<UserBase> list=userBaseService.findAllUserBase();
        for (UserBase userBase:list){
            System.out.println(userBase.toString());
        }
    }

    @Test
    public void deleteUserBase(){
        UserBase userBase =new UserBase();
        userBase.setUserId("5201234");
        userBase.setUserName("test10");
        userBase.setUserMail("1260556906@qq.com");
        userBase.setUserTelephone("15179161691");
        System.out.println(userBaseService.deleteUserBase(userBase));

    }

    @Test
    public void delete(){
        UserBase userBase = new UserBase();
        userBase = userBaseService.findUserBaseByUserId("5201314");
        System.out.println(userBase.toString());
        userBaseService.deleteUserBase(userBase);
    }



}
