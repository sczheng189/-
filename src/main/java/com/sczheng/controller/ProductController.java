package com.sczheng.controller;


import com.sczheng.pojo.*;
import com.sczheng.service.EmailService;
import com.sczheng.service.ProductService;
import com.sczheng.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/productManage")
public class ProductController {

    @Autowired
    private ProductService productService;




    //与前端交互，返回json数据
    @GetMapping("/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
                      ) {
        log.info("分页查询商品信息:page={},size={} ,name={}", page, pageSize);

        PageBean pageBean = productService.page(page, pageSize);
        return Result.success(pageBean);
    }


    @GetMapping("/search")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name) {
        log.info("分页查询商品信息:page={},size={} ,name={}", page, pageSize,name);

        PageBean pageBean = productService.page(page, pageSize,name);
        return Result.success(pageBean);
    }



    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {  //pathvariable注解，将路径中的参数绑定到方法的参数上
        log.info("删除商品信息:ids={}", ids);
        productService.delete(ids);
        return Result.success();
    }


    @PostMapping
    public Result save(@RequestBody Product product) {
        log.info("添加商品信息:{}", product);
        productService.insert(product);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询商品信息:id={}", id);
        Product product = productService.getById(id);
        return Result.success(product);
    }


    @PutMapping
    public Result update(@RequestBody Product product) {  //requestbody注解，将请求体中的json数据绑定到方法的参数上
        log.info("更新商品信息:{}", product);
        productService.update(product);
        return Result.success();
    }

    @PutMapping("/updateStock/{id}")
    public Result updateStock(@PathVariable Integer id,@RequestParam Integer decrementValue) {  //requestbody注解，将请求体中的json数据绑定到方法的参数上
        log.info("更新商品库存:id={},decrementValue={}", id,decrementValue);
        productService.updateStock(id,decrementValue);
        return Result.success();
    }




}
