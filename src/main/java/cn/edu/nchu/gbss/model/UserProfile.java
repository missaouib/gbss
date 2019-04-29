package cn.edu.nchu.gbss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert(true)  //动态插入
@DynamicUpdate(true) //动态更新
@Data //注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@NoArgsConstructor //注解在类上；为类提供一个无参的构造方法
@AllArgsConstructor //注解在类上；为类提供一个全参的构造方法
@Table(name = "gbss_user_profile")
public class UserProfile {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_nickname")
    private String userNickname;
    @Column(name = "realname")
    private String realname;
    @Column(name = "user_sex")
    private String userSex;
    @Column(name = "user_level")
    private Integer userLevel;
    @Column(name = "user_experience")
    private Integer userExperience;
    @Column(name = "user_face_url")
    private String userFaceUrl;
    @Column(name = "user_birthday")
    private String userBirthday;
    @Column(name = "user_eucation")
    private String userEucation;
    @Column(name = "user_regist_ip")
    private String userRegistIp;
    @Column(name = "user_regist_time")
    private String userRegistTime;
    @Column(name = "user_login_ip")
    private String userLoginIp;
    @Column(name = "user_login_time")
    private String userLoginTime;
    @Column(name = "user_login_count")
    private Integer userLoginCount;
    @Column(name = "user_online_status")
    private String userOnlineStatus;
    @Column(name = "user_online_seconds")
    private Integer userOnlineSeconds;
    @Column(name = "user_unreadmsg")
    private Integer userUnreadmsg;

}
