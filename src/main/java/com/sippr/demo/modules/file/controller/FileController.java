package com.sippr.demo.modules.file.controller;

import com.sippr.demo.common.result.ApiResult;
import com.sippr.demo.modules.file.entity.vo.FileVO;
import com.sippr.demo.modules.file.service.FileService;
import com.sippr.demo.modules.file.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Pattern;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ChenXiangpeng
 */
@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件")
@Validated
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/download/url")
    @ApiOperation("获取下载重定向路径")
    public ResponseEntity<Resource> getDownloadUrl(@RequestParam("fileName")
                                                   @Pattern(regexp = "^((?![{} /\\:*?\"<>|]).)*$",message = "文件名不能包含{ } 空格 / \\ : * ? \" < > |")
                                                   String fileName,
                                                   @RequestParam("directory") String directory) {
        String redirectPath = fileService.redirectUrl(fileName,directory);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + FileUtil.parseName(fileName))
                .header("X-Accel-Redirect", FileUtil.parseName(redirectPath)).build();
    }

    @GetMapping("/download/resource")
    @ApiOperation("Resource直接下载")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName")
                                                 @Pattern(regexp = "^((?![{} /\\:*?\"<>|]).)*$",message = "文件名不能包含{ } 空格 / \\ : * ? \" < > |")
                                                 String fileName,
                                                 @RequestParam("directory") String directory) {
        Resource resource = fileService.downloadFile(fileName,directory);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + FileUtil.parseName(fileName))
                .body(resource);
    }

    @GetMapping("/download")
    @ApiOperation("直接下载")
    public void downloadFile(@RequestParam("fileName")
                             @Pattern(regexp = "^((?![{} /\\:*?\"<>|]).)*$",message = "文件名不能包含{ } 空格 / \\ : * ? \" < > |")
                                     String fileName,
                             @RequestParam("directory") String directory,
                             HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
        fileService.downloadFile(fileName,directory,response);
    }

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public ApiResult<Object> upload(@RequestParam("uploadFile") MultipartFile uploadFile,
                                    @RequestParam("directory") String directory){
        fileService.uploadFile(uploadFile,directory);
        return ApiResult.success();
    }

    @GetMapping("/list")
    @ApiOperation("文件列表")
    public ApiResult<List<FileVO>> list(@RequestParam("directory") String directory){
        List<FileVO> fileVOList = fileService.directoryList(directory);
        return ApiResult.success(fileVOList);
    }
}
