package com.sippr.demo.modules.file.process.impl;

import com.sippr.demo.common.exception.CommonException;
import com.sippr.demo.modules.file.config.FileProperties;
import com.sippr.demo.modules.file.constants.FileConstants;
import com.sippr.demo.modules.file.process.FileProcess;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ChenXiangpeng
 */
@Service
@Slf4j
public class FileProcessImpl implements FileProcess {
    @SneakyThrows
    @Override
    public URL getUrl(String fileName, String directory) {
        Path filePath = getFilePath(fileName, directory);
        if (filePath.toFile().exists()){
            UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8000).path(FileConstants.SLASH).build();
            URI fileUrl = uriComponents.toUri().resolve(directory+FileConstants.SLASH).resolve(fileName);
            return fileUrl.toURL();
        }else{
            throw new CommonException("文件不存在");
        }
    }

    @Override
    public Path getFilePath(String fileName, String directory){
        Path path = Paths.get(FileProperties.ROOT_DIR).resolve(directory).normalize();
        //不存在则创建
        if (!path.toFile().exists()){
            if (path.toFile().mkdirs()){
                log.debug("目录不存在，创建目录:{}",path.toString());
            }else{
                log.error("目录:{}创建失败",path.toString());
            }
        }
        return path.resolve(fileName).normalize();
    }

    @Override
    public Path getDirectoryPath(String directory){
        return this.getFilePath("",directory);
    }

    public static void main(String[] args) {
        Path path = Paths.get(FileProperties.ROOT_DIR).resolve("/directory").normalize();
        //不存在则创建
        if (!path.toFile().exists()){
            if (path.toFile().mkdirs()){
                log.debug("目录不存在，创建目录:{}",path.toString());
            }else{
                log.error("目录:{}创建失败",path.toString());
            }
        }
        System.out.println(path.normalize().toString());
        System.out.println(path.resolve("").normalize().toString());
    }


}
