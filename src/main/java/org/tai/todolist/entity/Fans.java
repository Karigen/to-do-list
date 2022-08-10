package org.tai.todolist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
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
 * @since 2022-08-09
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "Fans", description = "")
public class Fans extends Model<Fans> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "relation_id", type = IdType.AUTO)
    private Integer relationId;

    private Integer userid;

    private Integer fanId;

    @Schema(description = "逻辑删除键")
    @TableLogic
    private Boolean flag;

    @Override
    public Serializable pkVal() {
        return this.relationId;
    }
}
