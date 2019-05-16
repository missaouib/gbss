package cn.edu.nchu.gbss.controller;

import cn.edu.nchu.gbss.model.Admin;
import cn.edu.nchu.gbss.service.AdminService;
import cn.edu.nchu.gbss.util.ConstantUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: zhouxiang
 * @time: 2019/5/6 18:27
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    /**
     * @description: 管理员登录
     * @param:
     * @return:
     */
    @GetMapping("/login")
    public String loginGet(Model model){
        return "admin/login";
    }


    /**
     * @description: 管理员登录
     * @param:
     * @return:
     */
    @PostMapping ("/login")
    public String loginPost(String account, String password, Model model, HttpServletRequest request) {
        Admin admin = adminService.judgeLogin(account, password);
        if (admin != null) {
            request.getSession().setAttribute(ConstantUtils.USER_SESSION_KEY,admin);
            model.addAttribute("adminU",admin.getUserName());
            return "redirect:dashboard";
        } else {
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return "admin/login";
        }
    }

    /**
     * @description: 管理员登出控制
     * @param:
     * @return:
     */
    @RequestMapping ("/logout")
    public String logoutGet(HttpSession httpSession){
        httpSession.removeAttribute(ConstantUtils.USER_SESSION_KEY);
        return "admin/login";
    }

    @GetMapping("/register")
    public String registerGet(Model model){
        return "admin/register";
    }

    /**
     * @description: 管理员注册信息
     * @param:
     * @return:
     */
    @PostMapping ("/register")
    public String registerPost(Admin admin, Model model, HttpSession httpSession){
        Wrapper<Admin> wrapper=new EntityWrapper<>();
        //wrapper.eq("user_name",admin.getUserName()).or().eq("email",admin.getEmail()).or().eq("telephone",admin.getTelephone()).ne("status","1");
        if(adminService.selectOne(wrapper.eq("user_name",admin.getUserName())) != null){
            model.addAttribute("msg","该用户名已存在");
            return "admin/register";
        }
        if (adminService.selectOne(wrapper.eq("email",admin.getEmail())) != null){
            model.addAttribute("msg","该邮箱已被注册");
            return "admin/register";
        }
        if (adminService.selectOne(wrapper.eq("telephone",admin.getTelephone())) != null){
            model.addAttribute("msg","该电话号码已被注册");
            return "admin/register";
        }
        System.out.println(admin.toString());
        admin.setStatus("1");
        if(adminService.insert(admin)) {
            model.addAttribute("msg","账号注册成功");
            return "admin/register";
        }else {
            model.addAttribute("msg","账号注册失败");
            return "admin/register";
        }
    }
}
