package org.tai.todolist.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tai.todolist.service.FileService;

import java.io.File;
import java.io.IOException;

/**
 * @author Karigen B
 * @create 2022-08-30 16:12
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.path}")
    private String path;

    @Value("${file.prefix}")
    private String prefix;

    @Value("${server.domain}")
    private String domain;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String[] split = multipartFile.getOriginalFilename().split("\\.");
        String extendName = split[split.length - 1];
        String imageName = IdUtil.simpleUUID() + "." + extendName;
        multipartFile.transferTo(new File(path + imageName));
        return domain + prefix + imageName;
    }

}
