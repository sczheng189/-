package com.sczheng.controller;



import com.sczheng.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
public class UploadController {

//    @Autowired      //注入工具类
//    AliOSSUtils aliOSSUtils;
    /**
     * 本地
     */

    @Value("${file.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile image) throws IOException {
        log.info("接收到上传文件请求:image={}", image);
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String newFileName = UUID.randomUUID() + image.getOriginalFilename();
        File destinationFile = new File(directory, newFileName);
        image.transferTo(destinationFile);

        log.info("文件保存路径: {}", destinationFile.getAbsolutePath());
        return Result.success("/images/" + newFileName);
    }
//    @PostMapping("/upload")
//    public Result upload(MultipartFile image) throws IOException {
//        log.info("接收到上传文件请求:image={}",image);
//        //阿里云OSS上传
//
//        String url = aliOSSUtils.upload(image);
//
//        log.info("上传成功，url={}",url);
//
//        return Result.success(url);
//    }
}
