package com.sippr.demo.modules.file.service.impl;

import com.google.common.collect.Lists;
import com.sippr.demo.common.exception.CommonException;
import com.sippr.demo.modules.file.entity.vo.FileVO;
import com.sippr.demo.modules.file.process.FileProcess;
import com.sippr.demo.modules.file.service.FileService;
import com.sippr.demo.modules.file.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ChenXiangpeng
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileProcess fileProcess;

    @Override
    public String redirectUrl(String fileName, String directory) {
        //标准化目录，去除两边的/，后面统一加
        directory = FileUtil.normalizeDirectory(directory);

        URL fileUrl = fileProcess.getUrl(fileName,directory);
        return fileUrl.toString();
    }

    @Override
    public Resource downloadFile(String fileName, String directory) {
        //标准化目录，去除两边的/，后面统一加
        directory = FileUtil.normalizeDirectory(directory);

        Path filePath = fileProcess.getFilePath(fileName, directory);
        if (filePath.toFile().exists()){
            try {
                byte[] bytes = FileUtils.readFileToByteArray(filePath.toFile());
                return new ByteArrayResource(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                throw new CommonException("文件下载失败");
            }
            /*FileInputStream fis = null;
            ByteArrayOutputStream bos = null;
            try{
                bos = new ByteArrayOutputStream();
                fis = new FileInputStream(filePath.toFile());
                int readLength;
                byte[] buffer = new byte[1024];
                while ((readLength = fis.read(buffer, 0, 1024)) != -1) {
                    bos.write(buffer, 0, readLength);
                    bos.flush();
                }
                return new ByteArrayResource(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
                throw new CommonException("文件加载失败");
            } finally {
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(bos);
            }*/
        }else{
            throw new CommonException("文件不存在");
        }
    }

    @Override
    public void downloadFile(String fileName, String directory, HttpServletResponse response) {
        //标准化目录，去除两边的/，后面统一加
        directory = FileUtil.normalizeDirectory(directory);

        Path filePath = fileProcess.getFilePath(fileName, directory);
        if (filePath.toFile().exists()){
            OutputStream outputStream = null;
            FileInputStream fis = null;
            try{
                outputStream = response.getOutputStream();
                fis = new FileInputStream(filePath.toFile());
                int readLength;
                byte[] buffer = new byte[1024];
                while ((readLength = fis.read(buffer, 0, 1024)) != -1) {
                    outputStream.write(buffer, 0, readLength);
                }
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                throw new CommonException("文件加载失败");
            } finally {
                IOUtils.closeQuietly(outputStream);
                IOUtils.closeQuietly(fis);
            }
        }else{
            throw new CommonException("文件不存在");
        }

    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String directory) {
        //标准化目录，去除两边的/，后面统一加
        directory = FileUtil.normalizeDirectory(directory);
        if (uploadFile.isEmpty()){
            throw new CommonException("上传文件为空");
        }
        String uploadFileName = uploadFile.getOriginalFilename();
        Path filePath = fileProcess.getFilePath(uploadFileName, directory);
        File targetFile = filePath.toFile();
        if (targetFile.exists()){
            if (targetFile.isDirectory()){
                log.error("文件:{}存在同名文件夹",uploadFileName);
                throw new CommonException("存在同名文件夹");
            }else{
                log.error("文件:{}重复,原文件大小:{}字节",uploadFileName,targetFile.length());
                throw new CommonException("文件名重复");
            }
        }else{
            try{
                uploadFile.transferTo(targetFile);
            }catch (IOException e){
                log.error("文件:{}上传失败",uploadFileName);
                throw new CommonException("文件上传失败");
            }
        }
    }

    @Override
    public List<FileVO> directoryList(String directory) {
        //标准化目录，去除两边的/，后面统一加
        directory = FileUtil.normalizeDirectory(directory);
        Path directoryPath = fileProcess.getDirectoryPath(directory);
        File file = directoryPath.toFile();
        File[] files = file.listFiles();
        if (Objects.isNull(files)){
            return Lists.newArrayListWithCapacity(0);
        }
        List<File> fileList = Lists.newArrayList(files);
        List<FileVO> collect = fileList.stream().map(FileVO::of).collect(Collectors.toList());
        return collect;
    }

    public static void main(String[] args) {
        File file = new File("F:\\images");
        System.out.println(Arrays.asList(file.list()));
        System.out.println(file.getFreeSpace());
        System.out.println(file.getTotalSpace());
        System.out.println(file.getUsableSpace());
        System.out.println(file.length());
        System.out.println(file.lastModified());
    }
}
