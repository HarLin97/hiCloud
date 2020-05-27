package com.fuchangling.common.api.vo;

import com.fuchangling.enums.ApiStatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author harlin
 */
@Data
@ApiModel(value = "ResultVO", description = "接口返回统一对象")
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private Boolean success = true;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "描述信息")
    private String message = "success";

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "状态码")
    private Integer code = HttpStatus.OK.value();

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "响应数据")
    private T result;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Long timestamp = System.currentTimeMillis();

    public ResultVO() {

    }

    public ResultVO<T> fail(String message) {
        this.message = message;
        this.code = ApiStatusCode.ERROR.getCode();
        this.success = false;
        return this;
    }

    public ResultVO<T> success(String message) {
        this.message = message;
        this.code = ApiStatusCode.OK.getCode();
        this.success = true;
        return this;
    }

    public ResultVO<T> success(T data) {
        this.message = message;
        this.code = ApiStatusCode.OK.getCode();
        this.result = data;
        this.success = true;
        return this;
    }

    public ResultVO<T> fail(ApiStatusCode apiStatusCode) {
        this.message = apiStatusCode.getMessage();
        this.code = apiStatusCode.getCode();
        this.success = false;
        return this;
    }

    public static ResultVO<Object> ok() {
        ResultVO<Object> r = new ResultVO<>();
        r.setSuccess(true);
        r.setCode(ApiStatusCode.OK.getCode());
        r.setMessage(ApiStatusCode.OK.getMessage());
        return r;
    }

    public static ResultVO<Object> ok(String msg) {
        ResultVO<Object> r = new ResultVO<>();
        r.setSuccess(true);
        r.setCode(ApiStatusCode.OK.getCode());
        r.setMessage(msg);
        return r;
    }

    public static ResultVO<Object> ok(Object data) {
        ResultVO<Object> r = new ResultVO<>();
        r.setSuccess(true);
        r.setCode(ApiStatusCode.OK.getCode());
        r.setResult(data);
        return r;
    }


    public static ResultVO<Object> error(String msg) {
        return error(ApiStatusCode.ERROR.getCode(), msg);
    }

    public static ResultVO<Object> error(int code, String msg) {
        ResultVO<Object> r = new ResultVO<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static ResultVO<Object> error(ApiStatusCode apiStatusCode) {
        return error(apiStatusCode.getCode(), apiStatusCode.getMessage());
    }


}
