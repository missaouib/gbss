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
@Table(name = "gbss_user_security_set")
public class UserSecuritySet {
    @Id
    @Column(name = "user_security_set_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自动递增
    private Integer userSecuritySetId;
    @Column(name = "user_id")
    private String userId;

    private String question1;

    private String answer1;

    private String question2;

    private String answer2;

    private String question3;

    private String answer3;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "update_time")
    private String updateTime;

}

