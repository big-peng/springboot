package com.sippr.demo.modules.file.process;

import java.net.URL;
import java.nio.file.Path;

/**
 * @author ChenXiangpeng
 */
public interface FileProcess {

    URL getUrl(String fileName, String directory);

    Path getFilePath(String fileName, String directory);

    Path getDirectoryPath(String directory);
}
