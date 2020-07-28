package com.sippr.demo.modules.file.service;

import com.sippr.demo.modules.file.entity.vo.FileVO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ChenXiangpeng
 */
public interface FileService {
    /**
     * 获取重定向路径
     * @param fileName 文件名
     * @param directory 文件所在目录
     * @return java.lang.String
     */
    String redirectUrl(String fileName, String directory);

    /**
     * 直接返回文件字节流
     * @param fileName 文件名
     * @param directory 文件所在目录
     * @return org.springframework.core.io.Resource
     */
    Resource downloadFile(String fileName, String directory);

    /**
     * 直接响应输出流返回
     * @param fileName 文件名
     * @param directory 文件所在目录
     * @param response response
     */
    void downloadFile(String fileName, String directory, HttpServletResponse response);

    /**
     * 文件本地保存
     * @param uploadFile 源文件
     * @param directory 保存目录
     */
    void uploadFile(MultipartFile uploadFile, String directory);

    /**
     * 查看文件列表
     * @param directory 查看目录
     * @return java.util.List<com.sippr.demo.modules.file.entity.vo.FileVO>
     */
    List<FileVO> directoryList(String directory);
}
