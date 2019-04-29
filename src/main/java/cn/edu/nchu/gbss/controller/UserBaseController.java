package cn.edu.nchu.gbss.controller;

import cn.edu.nchu.gbss.model.UserBase;
import cn.edu.nchu.gbss.service.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhouxiang
 * @time: 2019/4/28 15:13
 */
@RestController
public class UserBaseController {
    @Autowired
    private UserBaseService userBaseService;

    @GetMapping("/save")
    public void save(){
        UserBase userBase = new UserBase();
        userBase.setUserId("5201314");
        userBase.setUserName("nihao");
        userBaseService.save(userBase);
    }

}
