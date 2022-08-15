package org.tai.todolist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
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
@Schema(name = "Task", description = "")
@ApiModel
public class Task extends Model<Task> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    private Integer userid;

    private String taskName;

    @Schema(description = "备注")
    @TableField("`description`")
    private String description;

    @Schema(description = "小时")
    private Integer deadline;

    @TableField("`finish`")
    private Boolean finish;

    @Schema(description = "逻辑删除键")
    @TableLogic
    @TableField(select = false)
    private Boolean flag;

    @Override
    public Serializable pkVal() {
        return this.taskId;
    }
}
