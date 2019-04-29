package cn.edu.nchu.gbss.service;

import cn.edu.nchu.gbss.model.UserBase;
import cn.edu.nchu.gbss.repository.UserBaseDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhouxiang
 * @time: 2019/4/28 16:16
 */

@Service
@Transactional
public class UserBaseServiceImp implements UserBaseService{
    @Autowired
    private UserBaseDao userBaseDao;

    @Override
    public void update(String userId, String userName){
        userBaseDao.updateName(userId,userName);
    }

    @Override
    public void save(UserBase userBase){
        userBaseDao.save(userBase);
    }

    @Override
    public UserBase findUserBaseByUserId(String userId) {
        return userBaseDao.findUserBaseByUserId(userId);
    }

    /**
     * @description: 查询所有userBase信息
     * @return:list
     */
    @Override
    public List<UserBase> findAllUserBase() {
        return userBaseDao.findAllUserBase();
    }

    /**
     * @description: 用户登录,用户可以通过邮箱/电话/用户名/id+密码的方式验证登录
     * @return:UserBase
     */
    @Override
    public Boolean userLogin(UserBase userBase) {
        Boolean bl = true;
        UserBase userBase1 = userBaseDao.userLogin(userBase);
        if (userBase1 == null){
            bl = false;
        }
        return bl;
    }

    /**
     * @description: 动态查询user
     * @return: list
     */
    @Override
    public List<UserBase> findUser(UserBase userBase) {
        return userBaseDao.findUserBase(userBase);
    }

    /**
     * @description: 动态插入UserBase信息
     * @return: boolean
     */
    @Override
    public boolean insertUserBase(List<UserBase> userBase) {
        Boolean bl = false;
        for (UserBase userBase1:userBase){
            if (userBase1 != null){
                userBaseDao.save(userBase1);
                bl=true;
            }
        }
        return bl;
    }

    /**
     * @description: 动态修改UserBase信息
     * @return: boolean
     */
    @Override
    public boolean updateUserBase(UserBase userBase) {
        Boolean bl = userBaseDao.updateUserBase(userBase);
        return bl;
    }

    /**
     * @description: 动态删除UserBase信息
     * @return: boolean
     */
    @Override
    public boolean deleteUserBase(UserBase userBase) {
        Boolean bl = userBaseDao.deleteUserBase(userBase);
        return bl;
    }



    @Override
    public Page<UserBase> pageQuery(UserBase model, Pageable pageable) {
        //封装查询对象Specification
        Specification<UserBase> example = new Specification<UserBase>() {

            @Override
            public Predicate toPredicate(Root<UserBase> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //获取客户端查询条件
                String userName = model.getUserName();
                String userMail = model.getUserMail();
                String userTelephone = model.getUserTelephone();

                //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
                List<Predicate> predicates = new ArrayList<>();

                //对客户端查询条件进行判断,并封装Predicate断言对象
                if (StringUtils.isNotBlank(userName)) {
                    //root.get("userName")获取字段名
                    //userName客户端请求的字段值
                    //as(String.class)指定该字段的类型
                    Predicate predicate = cb.equal(root.get("userName").as(String.class), userName);
                    predicates.add(predicate);
                }
                if (StringUtils.isNotBlank(userMail)) {
                    Predicate predicate = cb.equal(root.get("userMail").as(String.class), userMail);
                    predicates.add(predicate);
                }
                if (StringUtils.isNotBlank(userTelephone)) {
                    Predicate predicate = cb.equal(root.get("userTelephone").as(String.class), userTelephone);
                    predicates.add(predicate);
                }

                //判断结合中是否有数据
                if (predicates.size() == 0) {
                    return null;
                }

                //将集合转化为CriteriaBuilder所需要的Predicate[]
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);

                // 返回所有获取的条件： 条件 or 条件 or 条件 or 条件
                return cb.or(predicateArr);
            }
        };

        //调用Dao方法进行条件查询
        Page<UserBase> page = userBaseDao.findAll(example, pageable);
        return page;
    }
}
