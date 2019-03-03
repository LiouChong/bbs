package com.bysj.controller;


import com.bysj.common.response.ActionResponse;
import com.bysj.entity.vo.query.UserQuery;
import com.bysj.entity.vo.query.UserRequestForLogin;
import com.bysj.entity.vo.query.UserRequestForRegist;
import com.bysj.entity.vo.request.UserRequest;
import com.bysj.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author lc
 * @since 2019-02-28
 */
@Api(value = "User", description = "用户表")
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    public IUserService iUserService;

    /**
     * 发送邮件
     * @param map
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/sendMail")
    public String sendEmail(@RequestBody Map map, HttpServletRequest request) throws IOException {
        return iUserService.sendVerificationCode(map, request);
    }


    /**
     * 检查邮箱是否重名
     */
    @PostMapping(value = "/checkName")
    public String checkIfRepeatName(String email) {
        if (iUserService.ifRepeatEmail(email)) {
            return "邮箱已被注册！";
        } else {
            return "邮箱可以使用！";
        }
    }


    /**
     * 保存用户信息，用于用户注册界面
     * @param userRequest
     * @return actionResponse
     */
    @ApiOperation(value = "保存接口", notes = "传入实体对象信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActionResponse.class, responseContainer = "actionResponse"),
    })
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ActionResponse saveSingle(@ApiParam(value = "user") @RequestBody UserRequestForRegist userRequest, HttpServletRequest request)throws Exception{
        // @todo: 处理异常类
        return iUserService.saveUser(userRequest, request);
    }

    /**
     * 用户登录操作
     * @param userRequest
     * @return actionResponse
     */
    @ApiOperation(value = "保存接口", notes = "传入实体对象信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActionResponse.class, responseContainer = "actionResponse"),
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionResponse doLogin(@ApiParam(value = "user") @RequestBody UserRequestForLogin userRequest, HttpServletRequest request)throws Exception{
        return iUserService.doLogin(userRequest, request);
    }


    /**
     * 修改
     * @param userRequest
     * @return actionResponse
     */
    @ApiOperation(value = "修改接口", notes = "传入实体对象信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActionResponse.class, responseContainer = "actionResponse"),
    })
    @RequestMapping(value = "/update/single", method = RequestMethod.POST)
    public ActionResponse updateSingle(@ApiParam(value = "user")UserRequest userRequest)throws Exception{
        iUserService.updateUser(userRequest);
        return ActionResponse.success();
    }

    /**
     * 批量查询
     * @param userQuery
     * @return actionResponse
     */
    @ApiOperation(value = "批量查询接口", notes = "传入查询条件")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActionResponse.class, responseContainer = "actionResponse"),
    })
    @RequestMapping(value = "/query/list", method = RequestMethod.GET)
    public ActionResponse queryList(@ApiParam(value = "user") UserQuery userQuery)throws Exception{
        return ActionResponse.success(iUserService.findPageUser(userQuery));
    }

    /**
     * 通过ID查询
     * @param id
     * @return actionResponse
     */
    @ApiOperation(value = "通过ID查询", notes = "主键封装对象")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActionResponse.class, responseContainer = "actionResponse"),
    })
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public ActionResponse queryById(@ApiParam(value = "user") @PathVariable("id") Integer id)throws Exception{

        return ActionResponse.success(iUserService.getById(id));
    }

    /**
     * 通过ID删除
     * @param id
     * @return actionResponse
     */
    @ApiOperation(value = "通过ID删除接口", notes = "主键封装对象")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActionResponse.class, responseContainer = "actionResponse"),
    })
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ActionResponse deleteById(@ApiParam(value = "id") @PathVariable("id") Integer id)throws Exception{

        return ActionResponse.success(iUserService.deleteById(id));
    }
}

