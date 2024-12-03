package com.sczheng.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        // 标准化路径分隔符
        uploadPath = uploadPath.replace('\\', '/');
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        System.out.println("Image upload path: " + uploadPath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 确保路径以 file:/ 开头，并且使用正斜杠
        String location = "file:" + uploadPath.replace('\\', '/');
        if (!location.endsWith("/")) {
            location += "/";
        }
        registry.addResourceHandler("/images/**")
                .addResourceLocations(location);

        System.out.println("Resource location: " + location);
    }
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")  // 所有接口
                    .allowedOrigins("http://localhost:9000",
                            "http://10.0.0.4",
                            "http://10.0.0.4:80",
                            "http://10.0.0.4:8080",
                            "http://10.0.0.4:9000",
                            "http://20.37.105.170:80",
                            "http://20.37.105.170:8080",
                            "http://20.37.105.170:9000",
                            "http://20.37.105.170")  // 允许前端地址
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的请求方法
                    .allowedHeaders("*")  // 允许的请求头
                    .allowCredentials(true)  // 允许携带凭证
                    .maxAge(3600);  // 预检请求的有效期，单位为秒
        }
    }
}