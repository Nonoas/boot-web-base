package org.nonoas.bootweb.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Nonoas
 * @datetime 2022/4/10 2:17
 */
@Component
public class UpLoadUtil {

    public String saveFile(MultipartFile file, String filePath) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("file must not be empty");
        }
        //获取上传文件原来的名称
        String filename = Optional.ofNullable(file.getOriginalFilename()).orElse("");
        String newName = SequenceUtil.getRandomFileName(filename);
        File temp = new File(filePath);
        if (!temp.exists()) {
            boolean f = temp.mkdirs();
            if (!f) {
                throw new IOException("file " + temp + " create fail");
            }
        }
        filePath = dirPath(filePath);
        File localFile = new File(filePath + newName);
        //把上传的文件保存至本地
        file.transferTo(localFile);
        return localFile.getAbsolutePath();
    }

    private String dirPath(String filePath) {
        boolean b = filePath.endsWith(File.separator);
        return b ? filePath : filePath + File.separator;
    }
}
