package cn.edu.nchu.gbss.controller;


import cn.edu.nchu.gbss.model.AdminRole;
import cn.edu.nchu.gbss.model.UserBase;
import cn.edu.nchu.gbss.service.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouxiang
 * @since 2019-05-03
 */
@Controller
@RequestMapping("/user-base")
public class UserBaseController {

    @Autowired
    private UserBaseService userBaseService;
    /**
     * @description: 用户信息初始页面，显示所有状态有效的用户信息
     * @param:
     * @return: List<admin>
     */
    @RequestMapping("/information")
    public String userBase(Model model){
        List<UserBase> list = userBaseService.selectAllUserBase();
        model.addAttribute("userList",list);
        return "user/user";
    }

}

