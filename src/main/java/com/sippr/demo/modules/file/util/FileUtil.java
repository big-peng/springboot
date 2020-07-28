package com.sippr.demo.modules.file.util;

import com.sippr.demo.modules.file.constants.FileConstants;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * @author ChenXiangpeng
 */
public class FileUtil {
    /**
     * 标准化目录
     * @param directory 目录
     * @return java.lang.String
     */
    public static String normalizeDirectory(String directory){
        directory = StringUtils.cleanPath(Paths.get(directory).normalize().toString());
        while(true){
            if (directory.startsWith(FileConstants.SLASH)){
                directory = directory.substring(1);
            }else{
                break;
            }
        }
        return directory;
    }

    /**
     * 将文件名格式由{@code UTF-8} 转换为 {@code ISO_8859_1}
     *
     * @param name 文件名
     * @return 转换后的文件名
     */
    public static String parseName(String name) {
        return new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }

    public static void main(String[] args) {
        String s = "^((?![/\\:*?\"<>|]).)*$";
        System.out.println(".jpg".matches(s));
        System.out.println(normalizeDirectory("\\test\\test/"));
    }
}
