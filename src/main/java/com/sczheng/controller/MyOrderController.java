package com.sczheng.controller;


import com.sczheng.pojo.*;
import com.sczheng.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/myOrder")
public class MyOrderController {

    @Autowired
    private MyOrderService myOrderService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProductService productService;


    //与前端交互，返回json数据
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
                       ) {
        log.info("分页查询用户-购物车信息:page={},size={}", page, pageSize);

        PageBean pageBean = myOrderService.page(page, pageSize);
        return Result.success(pageBean);
    }


//    @DeleteMapping("/{ids}")
//    public Result delete(@PathVariable List<Integer> ids) {  //pathvariable注解，将路径中的参数绑定到方法的参数上
//        log.info("删除用户-购物车信息:ids={}", ids);
//        myOrderService.delete(ids);
//        return Result.success();
//    }


    @PostMapping
    public Result insert(@RequestBody MyOrder myOrder) {
        log.info("添加用户-购物车信息:{}", myOrder);
        myOrderService.insert(myOrder);
        return Result.success();
    }


    @PutMapping
    public Result update(@RequestBody MyOrder myOrder) {  //requestbody注解，将请求体中的json数据绑定到方法的参数上
        log.info("更新用户-购物车信息:{}", myOrder);
        myOrderService.update(myOrder);
        return Result.success();
    }

    @GetMapping("/{userId}")
    public Result getByUserId(@PathVariable Integer userId) {
        log.info("查询用户-购物车信息:{}", userId);
        List<MyOrder> myOrder = myOrderService.selectByUserId(userId);
        return Result.success(myOrder);
    }

    @PutMapping("/updateFinish")
    public Result updateFinish(@RequestBody MyOrder myOrder) {
        log.info("更新订单信息:{}", myOrder);

        // 获取用户邮箱
        User user = userService.getById(myOrder.getUserId());
        String userEmail = user.getEmail();

        // 获取商品信息
        Product product = productService.getById(myOrder.getProductId());

        // 构建邮件内容
        String orderInfo = String.format(
                "订单号：%d\n商品名称：%s\n订单金额：%.2f",
                myOrder.getId(),
                product.getName(),
                myOrder.getTotalAmount()
        );

        // 发送邮件
        emailService.sendDeliveryNotification(userEmail, orderInfo);

        // 更新订单状态
        myOrderService.update(myOrder);
        return Result.success();
    }
    


}
