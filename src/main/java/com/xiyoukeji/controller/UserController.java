package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.UserBean;
import com.xiyoukeji.entity.Role;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.UserService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/22.
 */
@Controller
public class UserController {
    @Resource
    UserService userService;
    @Resource
    HttpSession session;
    @Resource
    HttpServletRequest request;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*创建或编辑用户 管理员权限*/
    @RequestMapping(value = "/saveorupdateUser")
    @ResponseBody
    public Map saveorupdateUser(String strUser) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            User user = new Gson().fromJson(strUser, User.class);
            return MapTool.Mapok().put("data", userService.saveorupdateUser(user));
        }

    }

    /*获取用户列表*/
    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Map getUserList(int type) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<UserBean> list = new ArrayList<>();
            List<User> users = userService.getUserList(type);
            for (int i = 0; i < users.size(); i++) {
                UserBean user = new UserBean();
                try {
                    Core.assignDest(user, users.get(i));
                    list.add(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }

    }

    /*获取用户信息*/
    @RequestMapping(value = "/getUser")
    @ResponseBody
    public Map getUser() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            UserBean user = new UserBean();
            try {
                Core.assignDest(user, userService.getUser(user1.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return MapTool.Mapok().put("data", MapTool.Map().put("user", user));
        }
    }

    /*删除用户信息 管理员权限*/
    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public Map deleteUser(Integer id) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("data", userService.deleteUser(id));
        }
    }

    /*登录*/
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map login(User user) {
        return userService.login(user);
    }

    /*注销*/
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Map logout() {
        userService.logout();
        return MapTool.Mapok();
    }

}
