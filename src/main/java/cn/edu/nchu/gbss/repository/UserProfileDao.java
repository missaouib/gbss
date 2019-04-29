package cn.edu.nchu.gbss.repository;

import cn.edu.nchu.gbss.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//继承 JpaRepository 接口，第一个参数为查询的实体类，第二个为实体类的主键数据类型
//继承JpaSpecificationExecutor接口，实现动态sql
public interface UserProfileDao extends JpaRepository<UserProfile,String>,JpaSpecificationExecutor<UserProfile> {
}
