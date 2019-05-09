package cn.edu.nchu.gbss.controller;


import cn.edu.nchu.gbss.model.*;
import cn.edu.nchu.gbss.service.UserBaseService;
import cn.edu.nchu.gbss.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserDetailService userDetailService;
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

    /**
     * @description: 查询用户信息，可以根据用户名、电话号、邮箱地址进行查询，实现了组合查询与模糊查询
     * @param:
     * @return:
     */
    @RequestMapping("/findUserBase")
    public String findUserBase(UserBase userBase,Model model){
        List<UserBase> list = userBaseService.selectByCondition(userBase);
        model.addAttribute("userList",list);
        return "user/user";
    }

    /**
     * @description: 添加用户基础信息
     * @param:
     * @return:
     */
    @RequestMapping("/addUser")
    public String addUser(UserBase userBase, String ip,String cname,Model model){
        if (userBaseService.isExitUserBase(userBase)){
            userBaseService.addUserBase(userBase);
            UserBase userBaseByName = userBaseService.findUserBaseByName(userBase.getUserName());
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(userBaseByName.getUserId());
            userDetail.setRegistIp(ip);
            userDetail.setRegistCity(cname);
            userDetail.setLevel(1);
            userDetail.setExperience(0);
            if(userDetailService.addUserDetail(userDetail)){
                model.addAttribute("msg","用户添加成功");
            }else {
                model.addAttribute("msg","用户添加失败");
            }
        }else {
            model.addAttribute("msg","该用户信息已存在");
        }
        return userBase(model);
    }

    /**
     * @description: 修改用户基础信息
     * @param:
     * @return:
     */
    @RequestMapping("/updateUser")
    public String updateUser(UserBase userBase, Model model){
        if (userBaseService.updateUserBase(userBase)){
            model.addAttribute("msg", "用户基础信息修改成功");
        }else {
            model.addAttribute("msg", "用户基础信息修改失败");
        }
        return userBase(model);
    }

    /**
     * @description: 删除用户基础信息，注意非常重要的操作，会级联删除所有该用户的信息，注意为逻辑删除
     * @param:
     * @return:
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(UserBase userBase,Model model){
        UserDetail userDetailByUserId = userDetailService.findUserDetailByUserId(userBase.getUserId());
        if (userDetailService.deleteUserDetail(userDetailByUserId)){
            if (userBaseService.deleteUserBase(userBase)){
                model.addAttribute("msg","用户信息删除成功");
            }else {
                model.addAttribute("msg","用户信息删除失败");
            }
        }else {
            model.addAttribute("msg","用户详情删除失败");
        }
        return userBase(model);
    }
}

