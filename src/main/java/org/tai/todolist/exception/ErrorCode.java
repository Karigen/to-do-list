package org.tai.todolist.exception;

/**
 * @author Karigen B
 * @create 2022-07-29 0:41
 */
public enum ErrorCode {

    USERNAME_ALREADY_EXIST("A0111", "用户名已存在"),
    INCORRECT_PASSWORD("A0120", "密码校验失败"),

    USERNAME_NOT_EXIST("A0201 ", "用户账户不存在"),
    ;

    public final String code;
    public final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
