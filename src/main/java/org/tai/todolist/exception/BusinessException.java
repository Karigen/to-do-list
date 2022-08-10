package org.tai.todolist.exception;

/**
 * @author Karigen B
 * @create 2022-07-29 0:51
 */
public class BusinessException extends RuntimeException {
    public final String code;
    public final String message;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.message);
        this.code = errorCode.code;
        this.message = errorCode.message;
    }

}
