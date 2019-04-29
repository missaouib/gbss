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
@Table(name = "gbss_user_group")
public class UserGroup {
    @Id
    @Column(name = "user_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长主键
    private Integer userGroupId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "status")
    private String status;
    @Column(name = "created_time")
    private String createdTime;

}
