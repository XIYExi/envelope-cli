package com.envelope.back.controller;


import com.envelope.back.common.utils.R;
import com.envelope.back.enhance.aop.Log;
import com.envelope.back.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "user")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "查询user全表", notes = "输出user表中所有数据", produces = "application/json")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @Log("查询list")
    public R list(){
        return R.ok().data("ok", userService.select());
    }


    @GetMapping("/test1")
    public R test1(@NotBlank(message = "用户名不能为空") @RequestParam("name") String name,
                   @NotBlank(message = "密码不能为空") @RequestParam("password") String password){
        log.info("参数为: {}, {}", name, password);
        return R.ok();
    }

}
