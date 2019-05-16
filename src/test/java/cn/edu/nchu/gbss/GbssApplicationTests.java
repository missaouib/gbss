package cn.edu.nchu.gbss;

import cn.edu.nchu.gbss.mapper.AdminDao;
import cn.edu.nchu.gbss.model.Admin;
import cn.edu.nchu.gbss.model.UserDetail;
import cn.edu.nchu.gbss.service.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GbssApplicationTests {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRoleInfService adminRoleInfService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsOrderService goodsOrderService;

    @Autowired
    private GoodsDescriptionService goodsDescriptionService;
    @Autowired
    private UserDetailService userDetailService;





    @Autowired
    private GoodsInAndOutService goodsInAndOutService;

    @Autowired
    private GoodsStatisticsService goodsStatisticsService;

    @Test
    public void selectById(){
        System.out.println(adminDao.selectById(1).toString());
    }

    @Test
    public void select(){
        Admin admin =new Admin();
        admin.setAdminId(1);
        admin.setPassword("admin");
        Admin admin1=adminDao.selectOne(admin);
        System.out.println(admin1.toString());
    }

    @Test
    public void testaddAdmin(){
        System.out.println("*****************testaddAdmin****************");
        for (int i=0;i<10;i++){
            Admin admin = new Admin();
            admin.setUserName("nihao"+i);
            admin.setPassword("123456");
            admin.setEmail(i+"qq.com");
            admin.setTelephone("1817086116"+i);
            adminService.addAdmin(admin);
        }
        List<Admin> admins = adminService.selectAllAdmin();
        for (Admin admin:admins){
            System.out.println(admin.toString());
        }

    }

    @Test
    public void testdeleteAdmin(){
        System.out.println("*****************testdeleteAdmin****************");
        Admin nihao2 = adminService.findAdminByUserName("nihao2");
        adminService.deleteAdmin(nihao2);
        Admin test = adminService.findAdminByUserName("nihao2");
        if (test == null){
            System.out.println("目标Admin已成功删除");
        }

    }

    @Test
    public void testupdateAdmin(){
        System.out.println("*****************testupdateAdmin****************");
        Admin adminByTelephone = adminService.findAdminByTelephone("18170861160");
        adminByTelephone.setUserName("就是这么强大");
        adminByTelephone.setPassword("lalalalalala");
        boolean b = adminService.updateAdmin(adminByTelephone);
        if (b){
            System.out.println("目标Admin已成功更新");
        }else {
            System.out.println("目标Admin更新失败");
        }
    }

    @Test
    public void testadminLogin(){
        System.out.println("*****************testadminLogin****************");
        Admin admin = new Admin();
        admin.setUserName("nihao1");
        admin.setTelephone("18170861161");
        admin.setEmail("1qq.com");
        admin.setPassword("123456");
        Admin admin1 = adminService.adminLogin(admin);
        if (admin1 != null){
            System.out.println("登录成功"+admin1.toString());
        }else {
            System.out.println("登录失败");
        }
    }

    @Test
    public void testselectAllAdmin(){
        System.out.println("*****************testselectAllAdmin****************");
        List<Admin> admins = adminService.selectAllAdmin();
        for (Admin admin:admins){
            System.out.println(admin.toString());
        }
    }

    @Test
    public void testAddAdminRole() throws ScriptException {


    }

}
