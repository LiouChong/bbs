package com.bysj.common.utils;

import com.bysj.dao.UserDao;
import com.bysj.entity.User;
import com.bysj.entity.vo.response.UserResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述:
 *
 * @author liuchong
 */
@Component
public class UserHandle {

    @Resource
    private UserDao userDao;

    public Integer getUserId() {
        return this.getUser() == null ? null : this.getUser().getId();
    }

    public String getUserEmail() {
        return getUser().getEmail();
    }

    public User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public UserResponse getCurrentUserInfo() {
        return userDao.userDetailInfo(getUserId());
    }

    public Integer getLoginUserId() {
        Integer userId = getUserId();
        if (userId == null) {
            throw new UnauthenticatedException();
        }
        return userId;
    }
}
