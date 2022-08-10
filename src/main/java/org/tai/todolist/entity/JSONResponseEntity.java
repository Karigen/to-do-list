package org.tai.todolist.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.tai.todolist.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karigen B
 * @create 2022-08-02 12:30
 */
@ApiModel("通用JSON响应实体")
public class JSONResponseEntity {

    @ApiModelProperty("业务响应状态")
    public final Boolean state;

    @ApiModelProperty("响应状态码,依据阿里Java开发手册泰山版")
    public final String code;

    @ApiModelProperty("响应信息")
    public final String message;

    @ApiModelProperty("响应的具体数据")
    public final Map<String, Object> data = new HashMap<>();

    private JSONResponseEntity(Boolean state, String code, String message) {
        this.state = state;
        this.code = code;
        this.message = message;
    }

    public static JSONResponseEntity ok() {
        return new JSONResponseEntity(true, "00000", "一切ok");
    }

    public static JSONResponseEntity error(String code, String message) {
        return new JSONResponseEntity(false, code, message);
    }

    public static JSONResponseEntity error(BusinessException businessException) {
        return new JSONResponseEntity(false, businessException.code, businessException.message);
    }

    public JSONResponseEntity newData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
