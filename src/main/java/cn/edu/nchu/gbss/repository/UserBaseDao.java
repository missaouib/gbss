package cn.edu.nchu.gbss.repository;

import cn.edu.nchu.gbss.model.UserBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//继承 JpaRepository 接口，第一个参数为查询的实体类，第二个为实体类的主键数据类型
//继承JpaSpecificationExecutor接口，实现动态sql
@Repository
@Transactional
public interface UserBaseDao extends JpaRepository<UserBase,String>,JpaSpecificationExecutor<UserBase> {

    /**
     * @description: 根据用户id查询UserBase，通过占位符获取参数值
     * @return: UserBase
     * 这里写的不是sql ,而是hql
     * sql是关系数据库查询语言,面对的数据库;
     * 而hql是Hibernate这样的数据库持久化框架提供的内置查询语言,
     * 所以from后面是实体对象名，而不是数据库表名
     */
    @Query("select ub from UserBase ub where ub.userId = ?1")
    UserBase findUserBaseByUserId(String userId);

    /**
     * @description: 用户登录,用户可以通过邮箱/电话/用户名/id+密码的方式验证登录
     * @return: UserBase
     */
    @Query("select ub from UserBase ub where ub.userId = #{#userBase.userId} or ub.userMail = #{#userBase.userMail} " +
            "or ub.userName = #{#userBase.userName} or ub.userTelephone =#{#userBase.userTelephone} " +
            "and ub.userPassword = #{#userBase.userPassword}" )
    UserBase userLogin(UserBase userBase);

    /**
     * @description: 动态查询userBase对象
     * @return:
     */
    @Query("select ub from UserBase ub where ub.userId = #{#userBase.userId} or ub.userMail = #{#userBase.userMail} " +
            "or ub.userName = #{#userBase.userName} or ub.userStatus = #{#userBase.userStatus} " +
            "or ub.userTelephone =#{#userBase.userTelephone}" )
    List<UserBase> findUserBase(UserBase userBase);

    /**
     * @description: 查询所有userbase表信息
     * @return: List<UserBase>
     */
    @Query("select ub from UserBase ub where 1=1")
    List<UserBase> findAllUserBase();


    /**
     * @description: 查询UserBase信息
     * @return:
     */
    @Override
    Page<UserBase> findAll(Specification<UserBase> specification, Pageable pageable);


    /**
     * @description: 根据用户id更新用户姓名
     * @return:
     */
    @Modifying
    @Query("update UserBase ub set ub.userName = ?2 where ub.userId = ?1")
    void updateName(String userId, String userName);

    /**
     * @description: 动态更新UserBase
     * @return: boolena
     */
    @Modifying
    @Query("update UserBase ub set ub.userName = #{#userBase.userName}, ub.userMail = #{userBase.userMail},ub.userStatus= #{#userBase.userStatus}" +
            ",ub.userPassword =#{#userBase.userPassword},ub.userTelephone = #{#userBase.userTelephone} where ub.userId =#{#userBase.userTelephone}")
    boolean updateUserBase(UserBase userBase);

    /**
     * @description: 删除指定userbase对象
     * @return:
     */
    @Override
    void delete(UserBase userBase);

    /**
     * @description: 根据id删除UserBase对象
     * @return:
     */
    @Override
    void deleteById(String userId);

    /**
     * @description: 动态删除userbase信息
     * @return:
     */
    @Modifying
    @Query("delete from  UserBase ub where ub.userId = #{#userBase.userId} or ub.userName =#{#userBase.userName} " +
            "or ub.userStatus =#{#userBase.userStatus} ")
    boolean deleteUserBase(UserBase userBase);





    //List<UserBase> findUserBaseByUser_nameoOrderByUser_idDesc()

}
