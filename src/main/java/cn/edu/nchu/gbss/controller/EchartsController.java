package cn.edu.nchu.gbss.controller;

import cn.edu.nchu.gbss.mapper.UserDetailDao;
import cn.edu.nchu.gbss.model.UserDetail;
import cn.edu.nchu.gbss.model.echartsModel.SumOfConsum;
import cn.edu.nchu.gbss.model.echartsModel.SumOfSex;
import cn.edu.nchu.gbss.service.UserDetailService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @description:
 * @author: zhouxiang
 * @time: 2019/5/10 20:02
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserDetailDao userDetailDao;


    @RequestMapping("/commodity")
    public String commodityStatistics(Model model){

        return "echarts/commodityStatistics";
    }

    @RequestMapping("/user")
    public String userStatistics(Model model){
        List<SumOfConsum> moneyBroupBySex = userDetailDao.getMoneyBroupBySex();
        List<SumOfSex> numGroupBySex = userDetailDao.getNumGroupBySex();
        for(SumOfConsum sumOfConsum:moneyBroupBySex){
            if ("男".equals(sumOfConsum.getSex())){
                model.addAttribute("manConsum",sumOfConsum.getMoney());
                //Double manConsum = sumOfConsum.getMoney();
            }else {
                model.addAttribute("womanConsum",sumOfConsum.getMoney());
                //Double womanConsum = sumOfConsum.getMoney();
            }
        }
        for(SumOfSex sumOfSex : numGroupBySex){
            if ("男".equals(sumOfSex.getSex())){
                model.addAttribute("manNum",sumOfSex.getNum());
                //int manNum = sumOfSex.getNum();
            }else {
                model.addAttribute("womanNum",sumOfSex.getNum());
                //int womanNum = sumOfSex.getNum();
            }
        }

        return "echarts/userStatistics";
    }

    @RequestMapping("/sales")
    public String salesStatistics(Model model){
        return "echarts/salesStatistics";
    }

    @RequestMapping("/order")
    public String orderStatistics(Model model){
        return "echarts/orderStatistics";
    }
}
