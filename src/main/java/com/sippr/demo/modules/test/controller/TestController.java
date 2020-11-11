package com.sippr.demo.modules.test.controller;

import com.google.code.kaptcha.Producer;
import com.sippr.demo.config.DynamicDataSourceInit;
import com.sippr.demo.modules.test.mapper.TestMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: ChenXiangpeng
 * @description:
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private Producer producer;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private DynamicDataSourceInit dynamicDataSourceInit;

    @GetMapping("/test/message")
    @ApiOperation("测试")
    public Object getTest(@RequestParam("name") String name){
        dynamicDataSourceInit.initDataSource();
        return testMapper.test("");
    }

    @GetMapping("/image/captcha")
    public ResponseEntity<Resource> createCaptchaImage(@RequestParam("content") String content){
        BufferedImage image = producer.createImage(content);
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            ImageIO.write(image, "png", bos);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=captcha.png")
                    .body(new ByteArrayResource(bos.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
