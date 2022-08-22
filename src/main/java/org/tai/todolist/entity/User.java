package org.tai.todolist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Karigen
 * @since 2022-08-10
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "User", description = "")
@ApiModel
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid", type = IdType.AUTO)
    @ApiModelProperty
    private Integer userid;

    @ApiModelProperty
    private String username;

    @TableField("`password`")
    @ApiModelProperty
    private String password;

    @ApiModelProperty
    private String email;

    @Schema(description = "逻辑删除键")
    @TableLogic
    @TableField(select = false)
    private Boolean flag;

    @Override
    public Serializable pkVal() {
        return this.userid;
    }
}
