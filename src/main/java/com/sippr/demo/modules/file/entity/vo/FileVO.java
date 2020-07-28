package com.sippr.demo.modules.file.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;

/**
 * @author ChenXiangpeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileVO implements Serializable {
    private String name;
    private Boolean isDir;
    private Long size;

    public static FileVO of(File file){
        return FileVO.builder()
                .name(file.getName())
                .isDir(file.isDirectory())
                .size(file.length()).build();

    }
}
