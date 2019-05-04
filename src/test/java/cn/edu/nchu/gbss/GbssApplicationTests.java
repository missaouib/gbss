package cn.edu.nchu.gbss;

import cn.edu.nchu.gbss.mapper.AdminDao;
import cn.edu.nchu.gbss.model.Admin;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GbssApplicationTests {

    @Autowired
    private AdminDao adminDao;


    @Test
    public void selectById(){
        System.out.println(adminDao.selectById(1).toString());
    }

    /**
     * <p>
     * 根据根据 entity 条件，删除记录,QueryWrapper实体对象封装操作类（可以为 null）
     * 下方获取到queryWrapper后删除的查询条件为name字段为null的and年龄大于等于12的and email字段不为null的
     * 同理写法条件添加的方式就不做过多介绍了。
     * </p>
     */
    @Test
    public void delete() {
        /*QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNull("name")
                .ge("age", 12)
                .isNotNull("email");
        int delete = adminDao.delete(queryWrapper);
        System.out.println("delete return count = " + delete);*/
    }

}
