package com.envelope.back.common.utils;

public interface RestCode {

    public static Integer SUCCESS_CODE = 2000;

    public static Integer ERROR_JSON_CODE = 5550;

    public static Integer ERROR_CONSTRAINT_CODE = 5551;

    public static Integer ERROR_BIND_CODE = 5552;

    public static Integer ERROR_VALIDATE_CODE = 5553;

    /*自定义异常*/
    public static Integer ERROR_CUSTOM_CODE = 5554;

    /*其余异常通用返回码*/
    public static Integer ERROR_CODE = 5555;

    /*验证码错误，前后端分离时返回给前端显示用*/
    public static Integer ERROR_QRCODE = 5556;

    /*用户名或密码错误，前后端分离时返回给前端显示用*/
    public static Integer ERROR_LOGIN_FAIL = 5557;

    /*用户登出，返回给前端显示用，注意：这个异常用于多设备登录时被挤号的时候抛出*/
    public static Integer ERROR_LOGOUT = 5558;


    /**
     * 匿名用户访问无权限资源异常
     */
    public static Integer ERROR_Anonymous = 5050;
}

