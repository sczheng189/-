package com.sczheng.controller;

import com.sczheng.pojo.Product;
import com.sczheng.pojo.Result;
import com.sczheng.pojo.User;
import com.sczheng.service.ProductService;
import com.sczheng.service.UserService;
import com.sczheng.utils.JwtUtils;
import io.jsonwebtoken.Claims;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@Slf4j
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/info")
//    public Result getUserInfo(HttpServletRequest request) {
//        // 从 token 中获取用户信息
//        String token = request.getHeader("Authorization").substring(7);
//        Claims claims = JwtUtils.parseJWT(token);
//
//        String username = claims.getSubject();
//        // 查询用户信息
//        User user = userService.findByUsername(username);
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("username", user.getUsername());
//        data.put("role", user.getRole());
//        data.put("email", user.getEmail());
//        data.put("money", user.getMoney());
//        data.put("address", user.getAddress());
//
//
//        log.info("用户信息:{}", data);
//        // ... 其他需要的用户信息
//
//        return Result.success(data);
//    }
//}
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/info")
    public Result getUserInfo(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            log.info("Authorization header: {}", authHeader);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.warn("Authorization header is invalid");
                return Result.error("未登录或token已过期");
            }


            String token = authHeader.substring(7);
            log.info("Token: {}", token);

            Claims claims = jwtUtils.parseJWT(token);
            log.info("Claims: {}", claims);

            String username = (String) claims.get("username");
            log.info("Username: {}", username);

            User user = userService.findByUsername(username);
            if (user == null) {
                log.warn("User not found: {}", username);
                return Result.error("用户不存在");
            }

            Map<String, Object> data = new HashMap<>();
            data.put("id", user.getId());
            data.put("username", user.getUsername());
            data.put("role", user.getRole());
            data.put("email", user.getEmail());
            data.put("money", user.getMoney());
            data.put("address", user.getAddress());

            return Result.success(data);
        } catch (NullPointerException e) {
            log.error("空指针异常", e);
            return Result.error("系统错误：" + e.getMessage());
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return Result.error("获取用户信息失败");
        }
    }

    @PutMapping("/update")
    public Result updateUserInfo(HttpServletRequest request, @RequestBody User user) {
        try {
            String authHeader = request.getHeader("Authorization");
            log.info("Authorization header: {}", authHeader);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.warn("Authorization header is invalid");
                return Result.error("未登录或token已过期");
            }

            String token = authHeader.substring(7);
            log.info("Token: {}", token);

            Claims claims = jwtUtils.parseJWT(token);
            log.info("Claims: {}", claims);

            String username = (String) claims.get("username");
            log.info("Username: {}", username);
            Integer id = (Integer) claims.get("id");

            User oldUser = userService.findByUsername(username);
            if (oldUser == null) {
                log.warn("User not found: {}", username);
                return Result.error("用户不存在");
            }

            user.setUsername(username);
            user.setId(id);

            userService.update(user);
            return Result.success("更新用户信息成功");
        } catch (NullPointerException e) {
            log.error("空指针异常", e);
            return Result.error("系统错误：" + e.getMessage());
        } catch (Exception e) {
            log.error("更新用户信息失败", e);
            return Result.error("更新用户信息失败");
        }

    }

    @GetMapping("/product/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询商品信息:id={}", id);
        Product product = productService.getById(id);
        return Result.success(product);
    }
}
