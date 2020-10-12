package com.darin.blog.controller.admin;

import com.darin.blog.common.CommonResult;
import com.darin.blog.dto.AdminLoginParam;
import com.darin.blog.entity.User;
import com.darin.blog.service.UserService;
import com.darin.blog.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Api(value = "loginController")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping()
    @ApiOperation(value = "登录请求")
    @ResponseBody
    public CommonResult<Object> loginPage(){
        String data = "请求成功";
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult<Object> login(@RequestBody AdminLoginParam adminLoginParam){
        User user = userService.checkUser(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if (user == null){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        String token = TokenUtils.sign(adminLoginParam.getUsername());
        tokenMap.put("token",token);
//        user.setPassword(null);
//        session.setAttribute("user",user);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    @ResponseBody
    public CommonResult<Object> logout(HttpSession session){
//        session.removeAttribute("user");
        return CommonResult.success(null,"注销成功");
    }
}
