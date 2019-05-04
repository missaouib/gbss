package cn.edu.nchu.gbss.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhouxiang
 * @since 2019-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor //注解在类上；为类提供一个无参的构造方法
@AllArgsConstructor //注解在类上；为类提供一个全参的构造方法
@TableName("user_point")
public class UserPoint extends Model<UserPoint> {

    private static final long serialVersionUID = 1L;

    @TableId("point_id")
    private Integer pointId;
    @TableField("user_id")
    private Integer userId;
    @TableField("user_point")
    private Integer userPoint;
    @TableField("point_consumed")
    private Integer pointConsumed;
    @TableField("point_frozen")
    private Integer pointFrozen;
    private String status;


    @Override
    protected Serializable pkVal() {
        return this.pointId;
    }

}
