package cn.edu.nchu.gbss.service;

import cn.edu.nchu.gbss.model.UserBase;
import cn.edu.nchu.gbss.model.UserBase;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhouxiang
 * @since 2019-05-03
 */
public interface UserBaseService extends IService<UserBase> {
    boolean isExitUserBase(UserBase userBase);

    boolean addUserBase(UserBase userBase);

    boolean deleteUserBase(UserBase userBase);

    boolean updateUserBase(UserBase userBase);

    UserBase findUserBaseByName(String name);

    List<UserBase> selectAllUserBase();

    List<UserBase> selectByCondition(UserBase userBase);

}
