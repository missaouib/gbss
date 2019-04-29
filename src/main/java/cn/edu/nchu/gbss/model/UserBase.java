package cn.edu.nchu.gbss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

//使用JPA注解配置映射关系


@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@DynamicInsert(true)  //动态插入
@DynamicUpdate(true) //动态更新
@Data //注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@NoArgsConstructor //注解在类上；为类提供一个无参的构造方法
@AllArgsConstructor //注解在类上；为类提供一个全参的构造方法
@Table(name = "gbss_user_base")  //@Table来指定和哪个数据表对应;如果省略默认表名就是userbase；
public class UserBase {

    @Id //主键标识
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_Telephone")
    private String userTelephone;
    @Column(name = "user_mail")
    private String userMail;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_status")
    private String userStatus;

}
