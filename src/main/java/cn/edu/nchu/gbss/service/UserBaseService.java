package cn.edu.nchu.gbss.service;

import cn.edu.nchu.gbss.model.UserBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserBaseService {


    void update(String userId, String userName);

    void save(UserBase userBase);

    UserBase findUserBaseByUserId(String userId);

    /**
     * @description: 查询所有userBase信息
     * @return:list
     */
    List<UserBase> findAllUserBase();

    /**
     * @description: 用户登录,用户可以通过邮箱/电话/用户名/id+密码的方式验证登录
     * @return:UserBase
     */ 
    Boolean userLogin(UserBase userBase);

    /**
     * @description: 动态查询user
     * @return: list
     */
    List<UserBase> findUser(UserBase userBase);

    /**
     * @description: 动态插入UserBase信息
     * @return: boolean
     */
    boolean insertUserBase(List<UserBase> userBase);

    /**
     * @description: 动态修改UserBase信息
     * @return: boolean
     */
    boolean updateUserBase(UserBase userBase);

    /**
     * @description: 动态删除UserBase信息
     * @return: boolean
     */
    boolean deleteUserBase(UserBase userBase);

    /**
     * @description: 查询
     * @return: 
     */ 
    Page<UserBase> pageQuery(UserBase model, Pageable pageable);

}
